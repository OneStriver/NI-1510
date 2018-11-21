package com.sunkaisens.nms.udpMessageThread;

import com.sunkaisens.nms.notify.alarm.CncpAlarmPacketHandler;
import com.sunkaisens.nms.notify.device.CncpDevPacketHandler;
import com.sunkaisens.nms.notify.log.CncpLogHandler;
import com.sunkaisens.nms.notify.perf.CncpPerfHandler;
import com.sunkaisens.nms.notify.route.CncpRouteHandler;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.messageProcess.HandlerThread;
import com.sunkaisens.nms.udpMessageThread.messageProcess.ReceivedMessage;

/**
 * 处理接收MQTT告警消息的线程 
 */
public class MessageProcessThread extends HandlerThread {
	
	public MessageProcessThread(String name) {
		super(name);
	}

	@Override
	public void onThreadMessage(int msgType,Object msg) {
		//TODO 处理逻辑
		if(msgType==CncpProtoType.CNCP_ALARM_MSG){//告警上报
			CncpAlarmPacketHandler cncpAlarmPacketHandler = new CncpAlarmPacketHandler();
			cncpAlarmPacketHandler.processCncpMsg(msg);
		}else if(msgType==CncpProtoType.CNCP_STATUS_MSG){//设备状态上报
			CncpDevPacketHandler cncpDevPacketHandler = new CncpDevPacketHandler();
			cncpDevPacketHandler.processCncpMsg(msg);
		}else if(msgType==CncpProtoType.CNCP_LOGINFO_MSG){//日志记录上报
			CncpLogHandler cncpSipPacketHandler = new CncpLogHandler();
			cncpSipPacketHandler.processCncpMsg(msg);
		}else if(msgType==CncpProtoType.CNCP_PERFORMENCE_MSG){//性能上报
			CncpPerfHandler cncpPerfHandler = new CncpPerfHandler();
			cncpPerfHandler.processCncpMsg(msg);
		}else if(msgType==CncpProtoType.CNCP_OMC_NI_MSG){//出入局配置
			CncpRouteHandler cncpRouteHandler = new CncpRouteHandler();
			cncpRouteHandler.processCncpMsg(msg);
		}
	}
	
	public void postThreadMessage(int type,Object message){
		ReceivedMessage receivedMessage = new ReceivedMessage(type,message);
		synchronized(lock){
			try {
				messageQueue.add(receivedMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lock.notifyAll();
		}
	}
	
}