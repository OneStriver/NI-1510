package com.sunkaisens.nms.notify.route;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.comm.ByteUtil;
import com.sunkaisens.nms.entity.TerminalInfoConfigFileEntity;
import com.sunkaisens.nms.ftp.FtpUtils;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpTaskExecutor;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.SendCncpMsgFactory;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.CncpBaseResponseMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcMsg;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.route.RoutingWebService;
import com.sunkaisens.nms.ws.createFile.server.NmsNorthInterfaceImpl;
import com.sunkaisens.nms.ws.createFile.server.comeInGoOut.TermialInfoConfigUtils;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;

/**
 * 出入局配置 
 */
public class CncpRouteHandler {
	
	private static Logger logger = Logger.getLogger(CncpRouteHandler.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static List<TerminalInfoConfigFileEntity> terminalInfoList = new ArrayList<TerminalInfoConfigFileEntity>();
	private CncpTaskExecutor cncpTaskExecutor = new CncpTaskExecutor();
	private static String reserveBitStr = ConfigurationManager.getInstance().getReserveNumberBit();
    
	/**
	 * OMC修改终端用户信息之后通知北向接口，然后在FTP目录中生成文件，同时通过该线程通知上级网管
	 */
	public void processCncpMsg(Object msg) {
		Packet packet = (Packet) msg;
		byte[] bytes = packet.getMsg();
		NIToOmcMsg niToOmcMsg = new NIToOmcMsg();
		niToOmcMsg = niToOmcMsg.readFromNIToOmcMsg(bytes, niToOmcMsg);
		if(niToOmcMsg.getMessageType() != CncpProtoType.CNCP_OMC_NI_MSG){
			return;
		}
		logger.info("Dispatch On RouteThread:[ 出入局配置 ]");
		String respStr = niToOmcMsg.getData();
		//获取第17位的值
		short chirldType = ByteUtil.byteToUnsignedChar(bytes[17]);
		if(chirldType==4){
			updateResultNotify(respStr,chirldType);
		}else{
			omcOperationTerminalNotify(respStr,chirldType,niToOmcMsg.getTransId());
		}
	}
	
	/**
	 *  终端信息更新结果通知
	 */
	public void updateResultNotify(String respStr,long chirldType){
		logger.info("接收到1510-OMC发送的消息:"+respStr);
		String[] stringArray = respStr.split("\n");
		Map<String, String> map = new HashMap<String, String>();
		for (String splitStr : stringArray) {
			map.put(splitStr.split(":",2)[0], splitStr.split(":",2)[1]);
		}
		String success = map.get("executeResult");
		String reason = map.get("failureReason");
		Map<String, String> templeMap = NmsNorthInterfaceImpl.templeMap;
		String fileName = templeMap.get("fileName");
		String systemName = templeMap.get("systemName");
		Session updateResultSession = TermialInfoConfigUtils.terminalInfoUpdateResultNotify(fileName, systemName, success, reason);
		String updateNotifyStr = XMLUtil.convertToXml(updateResultSession);
		CncpRouteHandler.updateTerminalInfoResultAndSend(updateNotifyStr);
	}
	
	/**
	 * 终端信息更新的结果通知上级网管
	 */
	public static boolean updateTerminalInfoResultAndSend(String xmlStr){
		// 链路连接
		boolean result = RouteServiceConnect.getRouteServiceConnect();
		RoutingWebService routingWebService = RouteServiceConnect.routingWebService;
		// 调用北向接口的通知方法
		if(result==true){
			routingWebService.rstTermInfo(xmlStr);
			System.err.println("发送成功");
		}
		return result; 
	}
	
	/**
	 * OMC添加，修改，删除用户
	 */
	public void omcOperationTerminalNotify(String respStr,long chirldType,int transId){
		// 清空terminalInfoList中的数据
		terminalInfoList.clear();
		logger.info("OMC添加,修改,删除用户>>接收到1510-OMC发送的消息:"+respStr);
		String[] stringArray = respStr.split("\n");
		Map<String, String> map = new HashMap<String, String>();
		for (String splitStr : stringArray) {
			map.put(splitStr.split(":",2)[0], splitStr.split(":",2)[1]);
		}
		TerminalInfoConfigFileEntity terminalInfoConfig = new TerminalInfoConfigFileEntity();
		terminalInfoConfig.setDeviceType(map.get("deviceType"));
		String deviceNumberStr = map.get("deviceNumber");
		Integer terminalNumberReserveBit = Integer.valueOf(reserveBitStr);
		Integer countBit = deviceNumberStr.length()-terminalNumberReserveBit;
		if(countBit>0){
			terminalInfoConfig.setDeviceNumber(deviceNumberStr.substring(countBit));
		}else{
			terminalInfoConfig.setDeviceNumber(deviceNumberStr);
		}
		if(chirldType==1){
			terminalInfoConfig.setType("add");
		}else if(chirldType==2){
			terminalInfoConfig.setType("update");
		}else if(chirldType==3){
			terminalInfoConfig.setType("delete");
		}
		terminalInfoConfig.setImsi(map.get("IMSI"));
		terminalInfoConfig.setPriority(map.get("Priority"));
		terminalInfoConfig.setPermitCallOut(map.get("PermitCallOut"));
		terminalInfoList.add(terminalInfoConfig);
		
		Session queryTerminalInfoSession = TermialInfoConfigUtils.generateTerminalInfoConfigXmlStr(terminalInfoList, "NTNMS");
		//上传文件名字
		String fileName = "TermInfo_wuxianzhihui_full_"+(sdf.format(new Date()))+".xml";
		System.err.println("OMC添加,修改,删除用户>>上传FTP服务器文件名字:"+fileName);
		File convertFile = XMLUtil.convertToXmlFile(queryTerminalInfoSession,"confs/ftp/"+fileName);
		FtpUtils.upload(convertFile,fileName);
		//删除本地的缓存文件
		convertFile.delete();
		//回复成功消息
		CncpBaseResponseMsg createNIToOmcResponseCncpMsg = SendCncpMsgFactory.getInstance().createCncpBaseResponseMsg(CncpProtoType.NI_HEADER, CncpProtoType.OMC_HEADER, CncpProtoType.CNCP_OMC_NI_ACK_MSG,transId, 255, 1, 0, 0, 0, 0);
		cncpTaskExecutor.invokeResponseMsg(createNIToOmcResponseCncpMsg, ConfigurationManager.getInstance().getOmcIp(), Integer.valueOf(ConfigurationManager.getInstance().getOmcPort()));
		System.err.println(">>>>>>>>北向接口处理之后回复1510-OMC成功消息>>>>>>>>");
		//生成XML字符串 通知上级网管
		Session terminalInfoNotifySession = TermialInfoConfigUtils.terminalInfoNotify(fileName, "NTNMS");
		String terminalInfoNotifyStr = XMLUtil.convertToXml(terminalInfoNotifySession);
		CncpRouteHandler.getTerminalInfoAndSend(terminalInfoNotifyStr);
	}
	
	/**
	 * OMC添加，修改，删除用户通知上级网管
	 */
	public static boolean getTerminalInfoAndSend(String xmlStr){
		// 链路连接
		boolean result = RouteServiceConnect.getRouteServiceConnect();
		RoutingWebService routingWebService = RouteServiceConnect.routingWebService;
		// 调用北向接口的通知方法
		if(result==true){
			routingWebService.rptTermInfo(xmlStr);
			logger.info(">>>>>>>>OMC添加,修改,删除用户通知上级网管成功>>>>>>>>");
		}else{
			logger.info(">>>>>>>>OMC添加,修改,删除用户通知上级网管失败！！！>>>>>>>>");
		}
		return result; 
	}
	
}
