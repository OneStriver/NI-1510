package com.sunkaisens.nms.udpMessageThread.messageProcess;

import java.util.concurrent.ConcurrentHashMap;

import com.sunkaisens.nms.udpMessageThread.MessageProcessThread;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpTaskExecutor;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.SendCncpMsgFactory;

/**
 * 数据缓存类
 */
public class GlobalHashMap {

	public static ConcurrentHashMap<String, MessageProcessThread[]> messageProcessThreadMap = new ConcurrentHashMap<String, MessageProcessThread[]>();
	
	public static ConcurrentHashMap<String, SendCncpMsgFactory> sendCncpMsgFactoryMap = new ConcurrentHashMap<String, SendCncpMsgFactory>();
	
	public static ConcurrentHashMap<String, CncpTaskExecutor> cncpTaskExecutorMap = new ConcurrentHashMap<String, CncpTaskExecutor>();

}
