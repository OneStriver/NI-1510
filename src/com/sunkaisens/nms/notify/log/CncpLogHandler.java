package com.sunkaisens.nms.notify.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.comm.ByteUtil;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.util.TimeUtil;
import com.sunkaisens.nms.ws.createFile.client.log.LogManageWebService;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.log.LogManage;
import com.sunkaisens.nms.ws.objectXmlConvert.log.RptLog;

/**
 * 日志上报服务
 */
public class CncpLogHandler {
	
	private static Logger logger = Logger.getLogger(CncpLogHandler.class);
	private static Session session = new Session();
	private static Cmd cmd = new Cmd();
	private static LogManage logManage = new LogManage();
	private static List<LogManage> logManageList = new ArrayList<LogManage>();
	private RptLog rptLog = new RptLog();
	private static List<RptLog> rptLogList = new ArrayList<RptLog>();

	public void processCncpMsg(Object msg) {
		rptLogList.clear();
		Packet packet = (Packet) msg;
		byte[] bytes = packet.getMsg();
		int messageType = CodecUtil.getUnsignedShort(bytes, 4);
		if(messageType != CncpProtoType.CNCP_LOGINFO_MSG){
			return;
		}
		logger.info("Dispatch On LogThread:[ 日志记录上报 ]");
		String logContent = ByteUtil.bytesToString(bytes, 20);
		System.err.println("日志记录上报消息的内容:\n"+logContent);
		//封装数据信息
		rptLog.setOperUser("");	//消息不能携带
		rptLog.setType("");	//消息不能携带
		rptLog.setContent(logContent);
		rptLog.setTime(TimeUtil.getNowTime());
		rptLogList.add(rptLog);
		Session session = generateLogInfoXmlStr(rptLogList);
		String xmlStr = XMLUtil.convertToXml(session);
		//通知消息
		getLogConnectAndSend(xmlStr);
	}
	
	public static boolean getLogConnectAndSend(String xmlStr){
		// 链路连接
		boolean result = LogServiceConnect.getLogServiceConnect();
		LogManageWebService logManageWebService = LogServiceConnect.logManageWebService;
		// 调用北向接口的通知方法
		if(result==true){
			logManageWebService.rptLog(xmlStr);
			logger.info(">>>>>>>>日志记录信息上报成功>>>>>>>>");
		}else{
			logger.info(">>>>>>>>日志记录信息上报失败！！！>>>>>>>>");
		}
		return result;
	}
	
	public static Session generateLogInfoXmlStr(List<RptLog> list){
		logManageList.clear();
		//
		logManage.setSystemName("wuxianzhihui");
		logManage.setRptLogs(list);
		logManageList.add(logManage);
		//
		cmd.setLogManages(logManageList);
		//
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		//
		return session;
	}
	
}
