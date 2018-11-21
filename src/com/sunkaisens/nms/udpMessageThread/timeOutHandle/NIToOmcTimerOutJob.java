package com.sunkaisens.nms.udpMessageThread.timeOutHandle;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunkaisens.nms.comm.HexDump;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.UDPNetComm;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.CncpBaseResponseMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcResponseMsg;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.util.ConfigurationManager;

/**
 * 定义TimerHandlerProxy类，指定需要执行的调度任务
 */
public class NIToOmcTimerOutJob implements Job {

	private static Logger logger = Logger.getLogger(NIToOmcTimerOutJob.class);
	public final static String NITOOMCUSERDATA = "NI_TASKUSERDATA";

	public NIToOmcTimerOutJob() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		CncpBaseResponseMsg reqCncp = (CncpBaseResponseMsg)context.getJobDetail().getJobDataMap().remove(NITOOMCUSERDATA);
		int ackType = 0;
		if(reqCncp.getMessageType() == CncpProtoType.CNCP_NI_OMC_MSG){
			ackType = CncpProtoType.CNCP_NI_OMC_ACK_MSG;
		}
		//封装超时回复消息
		NIToOmcResponseMsg timeOutCncp = new NIToOmcResponseMsg(reqCncp.getDestType(), reqCncp.getSrcType(), 
				reqCncp.getMessageLength(), ackType, reqCncp.getTransId(), reqCncp.getNeType(), reqCncp.getInstId(),
				reqCncp.getSubType(), reqCncp.getHoldBit(), (char) CncpProtoType.TIMEOUT, (char)0);
		byte[] timeOutBytes = new byte[20];
		timeOutBytes = writeToNIToOmcResponseMsg(timeOutBytes,timeOutCncp);
		logger.info("打印超时消息:\n" + HexDump.dump(timeOutBytes, 0, 0));
		Packet pack = new Packet();
		pack.setLen(20);
		pack.setMsg(timeOutBytes);
		pack.setIpAddress(ConfigurationManager.getInstance().getOmcIp());
		pack.setPort(Integer.valueOf(ConfigurationManager.getInstance().getOmcPort()));
		//添加超时消息到队列中
		UDPNetComm.responseMsgQueue.add(pack);
	}
	
	/**
	 * 设置消息
	 */
	public byte[] writeToNIToOmcResponseMsg(byte[] bytes, NIToOmcResponseMsg niToOmcResponseMsg) {
		// 设置消息的源地址
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getSrcType(), 0);
		// 设置消息的目的地址
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getDestType(), 1);
		// 消息总长度
		CodecUtil.putUnsignedShort(bytes, niToOmcResponseMsg.getMessageLength(), 2);
		// 消息类型
		CodecUtil.putUnsignedShort(bytes, niToOmcResponseMsg.getMessageType(), 4);
		// 事务ID
		CodecUtil.putUnsignedShort(bytes, niToOmcResponseMsg.getTransId(), 6);
		// 网元类型
		CodecUtil.putUnsignedInt(bytes, niToOmcResponseMsg.getNeType(), 8);
		// 实例ID
		CodecUtil.putUnsignedInt(bytes, niToOmcResponseMsg.getInstId(), 12);
		// 子类型
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getSubType(), 16);
		//保留位
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getHoldBit(), 17);
		//数据长度
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getResult(), 18);
		// data
		CodecUtil.putChar(bytes, niToOmcResponseMsg.getResultHoldBit(), 19);
		
		return bytes;
	}
	

}
