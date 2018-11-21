package com.sunkaisens.nms.udpMessageThread.cncpMsg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.comm.HexDump;
import com.sunkaisens.nms.udpMessageThread.MessageProcessThread;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.CncpBaseMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcResponseMsg;
import com.sunkaisens.nms.udpMessageThread.messageProcess.GlobalHashMap;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.util.StackUtil;

/**
 * 接收消息的内容 
 */
public class UDPNetComm implements Runnable {
	
	private static Logger logger = Logger.getLogger(UDPNetComm.class);
	public static final int MAX_UDP_PACKET_LEN = 4096;
	private byte[] buf = new byte[MAX_UDP_PACKET_LEN];
	public static DatagramSocket socket;
	private String socketName;
	private String localIP;
	private int localPort;
	private Thread thread;
	private volatile static UDPNetComm udpNetComm;
	//存储收到的消息
	public static BlockingQueue<Packet> receivedMsgQueue = new LinkedBlockingQueue<Packet>();
	//消息类型缓存
	private List<Integer> receivedMsgTypeList = new ArrayList<Integer>();
	//北向接口发送之后有响应的消息
	public static BlockingQueue<Packet> responseMsgQueue = new LinkedBlockingQueue<Packet>();
	//北向接口发送之后有响应的消息
	private List<Integer> responseMsgTypeList = new ArrayList<Integer>();
	private static final int dbThreadCount = 8;
	private static final int maxRevMsgCount = 65535;
	private int dbMsgCount=0;
	//接收目的地址
	private int descTypeInt = Integer.valueOf(Integer.toHexString(CncpProtoType.NI_HEADER),16);
	
	
	//单例模式
	private UDPNetComm(){
		
	}
	public static UDPNetComm getInstance(){
		if(udpNetComm != null){
		}else{
			synchronized(UDPNetComm.class){
				if(udpNetComm==null){
					udpNetComm = new UDPNetComm();
				}
			}
		}
		return udpNetComm;
	}
	
	public UDPNetComm(String name) {
		this.socketName = name;
		//北向接口接收到到的响应消息
		responseMsgTypeList.add(CncpProtoType.CNCP_NI_OMC_ACK_MSG);
		
		//OMC添加,修改,删除用户发送的消息类型
		receivedMsgTypeList.add(CncpProtoType.CNCP_OMC_NI_MSG);
		receivedMsgTypeList.add(CncpProtoType.CNCP_SET_MSG);
		receivedMsgTypeList.add(CncpProtoType.CNCP_GET_MSG);
		//告警消息
		receivedMsgTypeList.add(CncpProtoType.CNCP_ALARM_MSG);
		//设备状态
		receivedMsgTypeList.add(CncpProtoType.CNCP_STATUS_MSG);
		//日志上报
		receivedMsgTypeList.add(CncpProtoType.CNCP_LOGINFO_MSG);
		//性能上报
		receivedMsgTypeList.add(CncpProtoType.CNCP_PERFORMENCE_MSG);
		
	}

	public void initNetComm(String localIP, int localPort) {
		this.localIP = localIP;
		this.localPort = localPort;
		try {
			socket = new DatagramSocket(localPort);
		} catch (Exception exception) {
			logger.error(StackUtil.getTrace(exception));
		}
		initUDPNetComms();
	}
	
	@Override
	public void run() {
		while(true){
			try {
				DatagramPacket revPacket = new DatagramPacket(this.buf, this.buf.length);
				socket.receive(revPacket);
				this.buf = revPacket.getData();
				Packet pack = new Packet();
				pack.setLen(revPacket.getLength());
				byte[] msgBuf = new byte[pack.getLen()];
				System.arraycopy(this.buf, 0, msgBuf, 0, revPacket.getLength());
				pack.setMsg(msgBuf);
				pack.setIpAddress(revPacket.getAddress().getHostAddress());
				pack.setPort(revPacket.getPort());
				int getDescTypeInt = Integer.parseInt(CodecUtil.getBCDString(pack.getMsg(), 1, 2), 16);
				System.err.println("接收UDP包的目的地址:"+getDescTypeInt);
				if(getDescTypeInt==descTypeInt){
					System.err.println(">>>>>>>>接收UDP包目的地址正确>>>>>>>>");
					int msgType = Integer.parseInt(CodecUtil.getBCDString(pack.getMsg(), 4, 6), 16);
					if(responseMsgTypeList.contains(msgType)){
						pack.setMsgType(msgType);
						logger.info("北向接口发送消息接收响应的消息的IP和Port:" + pack.getIpAddress() + ":" + pack.getPort() + "\n"+HexDump.dump(pack.getMsg(), pack.getLen(), 0));
						responseMsgQueue.add(pack);
						System.err.println(">>>responseMsgQueue的大小:"+responseMsgQueue.size());
					}
					
					if(receivedMsgTypeList.contains(msgType)){
						dbMsgCount++;
						if (dbMsgCount > maxRevMsgCount) {
							dbMsgCount = 0;
						}
						int i = dbMsgCount % dbThreadCount;
						MessageProcessThread[] messageProcessThreads = GlobalHashMap.messageProcessThreadMap.get("processThreadArray");
						messageProcessThreads[i].postThreadMessage(msgType, pack);
					}
				}else{
					System.err.println(">>>>>>>>接收UDP包目的地址不正确！！！>>>>>>>>");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public static void main(String[] args) {
		char niHeader = 0xE4;
		String hexString = Integer.toHexString(niHeader);
		int parseInt =  Integer.valueOf(hexString,16);
		System.err.println(parseInt);
	}
	
	private void initUDPNetComms() {
		createThread();
	}

	private void createThread() {
		thread = new Thread(this, "UDPNetCommThread");
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * 发送UDP消息 
	 */
	public synchronized void send(Packet pack, String remoteIP, int remotePort) {
		try {
			socket.send(new DatagramPacket(pack.getMsg(), pack.getLen(), InetAddress.getByName(remoteIP), remotePort));
			logger.info("北向接口发送UDP消息,转换成码流>>>Send Packet To HexDump\n" + HexDump.dump(pack.getMsg(), pack.getLen(), 0));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getSocketName() {
		return socketName;
	}

	public void setSocketName(String socketName) {
		this.socketName = socketName;
	}

	public String getLocalIP() {
		return localIP;
	}

	public void setLocalIP(String localIP) {
		this.localIP = localIP;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	/**
	 * 从字节数组里面提取数据并封装成需要的对象
	 */
	public CncpBaseMsg convertToProtoObj(Packet takePacket) {
		byte[] takePackMsgBytes = takePacket.getMsg();
		int type = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes, 4, 6), 16);
		CncpBaseMsg resp = null;
		switch (type) {
		case CncpProtoType.CNCP_NI_OMC_ACK_MSG:
			//SrcType
			char niSrcType = CodecUtil.getChar(takePackMsgBytes,0);
			//destType
			char niDestType = CodecUtil.getChar(takePackMsgBytes,1);
			//messageLength
			int niMessageLength = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes,2,4),16);
			//messageType
			int niMessageType = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes,4,6),16);
			//transId
			int niTransId = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes,6,8),16);
			//neType
			int niNeType = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes,8,12),16);
			//instId
			int niInstId = Integer.parseInt(CodecUtil.getBCDString(takePackMsgBytes,12,16),16);
			//subType
			char niSubType = CodecUtil.getChar(takePackMsgBytes,16);
			//holdBit
			char niHoldBit = CodecUtil.getChar(takePackMsgBytes,17);
			//result
			char niResult = CodecUtil.getChar(takePackMsgBytes,18);
			//resultHoldBit
			char niResultHoldBit = CodecUtil.getChar(takePackMsgBytes,19);
			resp = new NIToOmcResponseMsg(niSrcType, niDestType, niMessageLength, niMessageType, niTransId, niNeType, niInstId, niSubType, niHoldBit, niResult, niResultHoldBit);
			break;
		}
		return resp;
	}

}
