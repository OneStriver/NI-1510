package com.sunkaisens.nms.udpMessageThread.cncpMsg;

import java.util.concurrent.atomic.AtomicInteger;

import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.CncpBaseResponseMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcMsg;

/**
 * 发送消息封装类
 */
public class SendCncpMsgFactory {

	private static final AtomicInteger transactionIds = new AtomicInteger();
	
	// 创建对象(单例模式)
	private SendCncpMsgFactory() {
		
	}
	public static SendCncpMsgFactory getInstance() {
		return SimpleCncpMsgFactoryInstance.instance;
	}
	private static class SimpleCncpMsgFactoryInstance {
		private static SendCncpMsgFactory instance = new SendCncpMsgFactory();
	}
	
	// 北向接口回复OMC的响应消息
	public CncpBaseResponseMsg createCncpBaseResponseMsg(char srcType,char destType, int messageType,int transId, int neType, int instId, int subType,int holdBit,int result,int resultHoldBit) {
		CncpBaseResponseMsg msg = new CncpBaseResponseMsg(srcType, destType, 20, messageType, transId, neType, instId, (char)subType, (char)holdBit, (char)result, (char)resultHoldBit);
		return msg;
	}
	
	// 北向接口发送给OMC
	public NIToOmcMsg createNIToOmcCncpMsg(char srcType,char destType, int messageType, int neType, int instId, char subType,String data) {
		int dataLength = data.getBytes().length;
		int messageLength = 20 + dataLength;
		NIToOmcMsg msg = new NIToOmcMsg(srcType, destType, messageLength, messageType, getTransactionId(), neType, instId, subType, (char)0, dataLength, data);
		return msg;
	}
	
	protected synchronized int getTransactionId() {
		if (transactionIds.get() > 65535){
			transactionIds.set(0);
		}
		return transactionIds.getAndIncrement();
	}
	
}
