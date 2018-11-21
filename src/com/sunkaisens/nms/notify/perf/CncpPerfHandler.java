package com.sunkaisens.nms.notify.perf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.comm.ByteUtil;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.ws.createFile.client.perf.PerfManageWebService;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.Parameter;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.PerfManage;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.RptPerf;

/**
 * 性能参数变更上报 
 */
public class CncpPerfHandler {
	
	private static Logger logger = Logger.getLogger(CncpPerfHandler.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Session session = new Session();
	private static Cmd cmd = new Cmd();
	private static PerfManage perfManage = new PerfManage();
	private static List<PerfManage> perfManageList = new ArrayList<PerfManage>();
	private static RptPerf rptPerf = new RptPerf();
	private static List<Parameter> parameterList = new ArrayList<Parameter>();
	private static Map<Integer,String> deviceTypeMap = new HashMap<Integer,String>();
	private Parameter parameterOccupy = new Parameter();
	private Parameter parameterIdle = new Parameter();
	
	static{
		deviceTypeMap.put(48, "通信基站");
		deviceTypeMap.put(49, "交换机");
		deviceTypeMap.put(50, "数传服务器");
		deviceTypeMap.put(51, "综合服务器");
	}
	
	public void processCncpMsg(Object msg) {
		parameterList.clear();
		Packet packet = (Packet) msg;
		byte[] bytes = packet.getMsg();
		int messageType = CodecUtil.getUnsignedShort(bytes, 4);
		if(messageType != CncpProtoType.CNCP_PERFORMENCE_MSG){
			return;
		}
		logger.info("Dispatch On PerfThread:[ 性能信息上报 ]");
		long neType = ByteUtil.bytesToUnsignedInt(bytes, 8);
		System.err.println("性能信息上报的[neType]:"+neType);
		long instId = ByteUtil.bytesToUnsignedInt(bytes, 12);
		System.err.println("性能信息上报的[instId]:" + instId);
		short action = ByteUtil.byteToUnsignedChar(bytes[16]);
		System.err.println("性能信息上报的[action]:" + action);
		short status = ByteUtil.byteToUnsignedChar(bytes[17]);
		System.err.println("性能信息上报的[status]:" + status);
		String performanceStr = ByteUtil.bytesToString(bytes, 20);
		String[] performanceArray = performanceStr.split("-");
		//信道占用数量
		System.err.println("性能信息上报的[信道占用数量]:"+performanceArray[0]);
		parameterOccupy.setType(deviceTypeMap.get((int)neType));
		parameterOccupy.setIndex(String.valueOf(instId));
		parameterOccupy.setName("");
		parameterOccupy.setEname("信道占用数量");
		parameterOccupy.setValue(performanceArray[0]);
		parameterList.add(parameterOccupy);
		//信道空闲数量
		System.err.println("性能信息上报的[信道空闲数量]:"+performanceArray[1]);
		parameterIdle.setType(deviceTypeMap.get((int)neType));
		parameterIdle.setIndex(String.valueOf(instId));
		parameterIdle.setName("");
		parameterIdle.setEname("信道空闲数量");
		parameterIdle.setValue(performanceArray[1]);
		parameterList.add(parameterIdle);
		//转换数据
		Session session = generatePerfManageXmlStr(parameterList);
		String xmlStr = XMLUtil.convertToXml(session);
		//通知web服务
		getPerfConnectAndSend(xmlStr);
	}
	
	public static boolean getPerfConnectAndSend(String xmlStr){
		// 链路连接
		boolean result = PerfServiceConnect.getPerfServiceConnect();
		PerfManageWebService perfManageWebService = PerfServiceConnect.perfManageWebService;
		// 调用北向接口的通知方法
		if(result==true){
			perfManageWebService.rptPerf(xmlStr);
			logger.info(">>>>>>>>性能信息上报成功>>>>>>>>");
		}else{
			logger.info(">>>>>>>>性能信息上报失败！！！>>>>>>>>");
		}
		return result;
	}
	
	public static Session generatePerfManageXmlStr(List<Parameter> list){
		perfManageList.clear();
		//
		rptPerf.setTime(sdf.format(new Date()));
		rptPerf.setParameters(list);
		//
		perfManage.setSystemName("wuxianzhihui");
		perfManage.setRptPerf(rptPerf);
		perfManageList.add(perfManage);
		//
		cmd.setPerfManages(perfManageList);;
		//
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		//
		return session;
	}

}
