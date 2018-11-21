package com.sunkaisens.nms.udpMessageThread;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.udpMessageThread.cncpMsg.UDPNetComm;
import com.sunkaisens.nms.util.ConfigurationManager;

//初始化监听接口UDP消息
public class OMCNetContext {
	
	private static Logger logger = Logger.getLogger(OMCNetContext.class);
	private volatile static OMCNetContext omcNetContext;
	
	private UDPNetComm udpNetComm;
	
	//单例模式
	private OMCNetContext(){
		
	}
	public static OMCNetContext getInstance(){
		if(omcNetContext != null){
		}else{
			synchronized(OMCNetContext.class){
				if(omcNetContext==null){
					omcNetContext = new OMCNetContext();
				}
			}
		}
		return omcNetContext;
	}
	
	public void initialize() {
		logger.info(">>>>>>>>监听UDP消息>>>>>>>>");
		//初始化Socket连接
		udpNetComm = new UDPNetComm("UDPNetCommHandler");
		// 初始化socket监听的IP和Port
		udpNetComm.initNetComm(ConfigurationManager.getInstance().getNorthIp(),Integer.valueOf(ConfigurationManager.getInstance().getNorthPort()));
	}

}
