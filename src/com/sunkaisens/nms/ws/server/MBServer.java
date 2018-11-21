package com.sunkaisens.nms.ws.server;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.quartz.QuartzManager;
import com.sunkaisens.nms.udpMessageThread.MessageProcessThread;
import com.sunkaisens.nms.udpMessageThread.OMCNetContext;
import com.sunkaisens.nms.udpMessageThread.messageProcess.GlobalHashMap;
import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.server.NmsNorthInterfaceImpl;
import com.sunkaisens.nms.ws.dbUtils.DataBaseUtil;

public class MBServer {
	
	private static Logger logger = Logger.getLogger(MBServer.class);
	private static NmsNorthInterfaceImpl implement = new NmsNorthInterfaceImpl();
	private static final int dbThreadCount = 8;

	public static void main(String[] args) {
		//初始化创建关系表
		DataBaseUtil.createCorrespondTable();
		//初始化启动多个线程处理数据
		initMessageProcessThread();
		//初始化接收UDP消息
		OMCNetContext.getInstance().initialize();
		// 发布webservice服务
		initService();
		//定时删除日志
		QuartzManager.addJob();
		// 首先检测网络问题,上报心跳
		QuartzManager.schedulerJob(10);
	}

	private static void initService() {
		logger.info(">>>>>>>>发布Webservice服务接口>>>>>>>>");
		String address = ConfigurationManager.getInstance().getLocalWsdl();
		Endpoint.publish(address, implement);
	}
	
	private static void initMessageProcessThread(){
		logger.info(">>>>>>>>开始启动线程接收消息>>>>>>>>");
		//启动多个线程处理消息
		MessageProcessThread[] messageProcessThreads = new MessageProcessThread[dbThreadCount];
		for (int i = 0; i < dbThreadCount; i++) {
			messageProcessThreads[i] = new MessageProcessThread("ProcessThread"+i);
			messageProcessThreads[i].startThread();
		}
		GlobalHashMap.messageProcessThreadMap.put("processThreadArray", messageProcessThreads);
	}
	
}
