package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * 统计消息(0x7006)
 */
public class StatisticalMsg extends CncpBaseMsg {

	private char subType;
	private char holeBit;
	private int dataLength;
	private String data;

	public char getSubType() {
		return subType;
	}

	public void setSubType(char subType) {
		this.subType = subType;
	}

	public char getHoleBit() {
		return holeBit;
	}

	public void setHoleBit(char holeBit) {
		this.holeBit = holeBit;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getLength() {
		return (CNCP_HEAD_LEN + CNCP_META_LEN + getDataLength());
	}

	/**
	 * 设置消息
	 */
	public byte[] writeToQueryMsg(byte[] bytes, StatisticalMsg statisticalMsg) {
		// 设置消息的源地址
		CodecUtil.putChar(bytes, statisticalMsg.getSrcType(), 0);
		// 设置消息的目的地址
		CodecUtil.putChar(bytes, statisticalMsg.getDestType(), 1);
		// 消息总长度
		CodecUtil.putUnsignedShort(bytes, statisticalMsg.getMessageLength(), 2);
		// 消息类型
		CodecUtil.putUnsignedShort(bytes, statisticalMsg.getMessageType(), 4);
		// 事务ID
		CodecUtil.putUnsignedShort(bytes, statisticalMsg.getTransId(), 6);
		// 网元类型
		CodecUtil.putUnsignedInt(bytes, statisticalMsg.getNeType(), 8);
		// 实例ID
		CodecUtil.putUnsignedInt(bytes, statisticalMsg.getInstId(), 12);
		// 子类型
		CodecUtil.putChar(bytes, statisticalMsg.getSubType(), 16);
		//保留位
		CodecUtil.putChar(bytes, statisticalMsg.getHoleBit(), 17);
		//数据长度
		CodecUtil.putUnsignedShort(bytes, statisticalMsg.getDataLength(), 18);
		// data
		CodecUtil.putStringToChars(bytes, statisticalMsg.getData(), 20);
		
		return bytes;
	}

}
