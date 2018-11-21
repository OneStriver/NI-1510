package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * NI发送给OMC的消息 (0x8012)
 */
public class NIToOmcMsg extends CncpBaseMsg {

	private char subType;
	private char holdBit;
	private int dataLength;
	private String data;
	
	public NIToOmcMsg() {
		super();
	}
	
	public NIToOmcMsg(char srcType,char destType,int messageLength,int messageType,int transId,int neType,int instId,char subType, char holdBit, int dataLength, String data) {
		super(srcType,destType,messageLength,messageType,transId,neType,instId);
		this.subType = subType;
		this.holdBit = holdBit;
		this.dataLength = dataLength;
		this.data = data;
	}

	public char getSubType() {
		return subType;
	}

	public void setSubType(char subType) {
		this.subType = subType;
	}

	public char getHoldBit() {
		return holdBit;
	}

	public void setHoleBit(char holdBit) {
		this.holdBit = holdBit;
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
	public byte[] writeToNIToOmcMsg(byte[] bytes, NIToOmcMsg setUpMsg) {
		// 设置消息的源地址
		CodecUtil.putChar(bytes, setUpMsg.getSrcType(), 0);
		// 设置消息的目的地址
		CodecUtil.putChar(bytes, setUpMsg.getDestType(), 1);
		// 消息总长度
		CodecUtil.putUnsignedShort(bytes, setUpMsg.getMessageLength(), 2);
		// 消息类型
		CodecUtil.putUnsignedShort(bytes, setUpMsg.getMessageType(), 4);
		// 事务ID
		CodecUtil.putUnsignedShort(bytes, setUpMsg.getTransId(), 6);
		// 网元类型
		CodecUtil.putUnsignedInt(bytes, setUpMsg.getNeType(), 8);
		// 实例ID
		CodecUtil.putUnsignedInt(bytes, setUpMsg.getInstId(), 12);
		// 子类型
		CodecUtil.putChar(bytes, setUpMsg.getSubType(), 16);
		//保留位
		CodecUtil.putChar(bytes, setUpMsg.getHoldBit(), 17);
		//数据长度
		CodecUtil.putUnsignedShort(bytes, setUpMsg.getDataLength(), 18);
		// data
		CodecUtil.putStringToChars(bytes, setUpMsg.getData(), 20);
		
		return bytes;
	}
	
	/**
	 * 设置消息
	 */
	public NIToOmcMsg readFromNIToOmcMsg(byte[] bytes, NIToOmcMsg niToOmcMsg) {
		// 设置消息的源地址
		niToOmcMsg.setSrcType(CodecUtil.getChar(bytes, 0));
		// 设置消息的目的地址
		niToOmcMsg.setDestType(CodecUtil.getChar(bytes, 1));
		// 消息总长度
		niToOmcMsg.setMessageLength(CodecUtil.getUnsignedShort(bytes, 2));
		// 消息类型
		niToOmcMsg.setMessageType(CodecUtil.getUnsignedShort(bytes, 4));
		// 事务ID
		niToOmcMsg.setTransId(CodecUtil.getUnsignedShort(bytes, 6));
		// 网元类型
		niToOmcMsg.setNeType(CodecUtil.getInt(bytes, 8));
		// 实例ID
		niToOmcMsg.setInstId(CodecUtil.getInt(bytes, 12));
		// 子类型
		niToOmcMsg.setSubType(CodecUtil.getChar(bytes, 16));
		//保留位
		niToOmcMsg.setHoleBit(CodecUtil.getChar(bytes, 17));
		//数据长度
		niToOmcMsg.setDataLength(CodecUtil.getUnsignedShort(bytes, 18));
		// data
		niToOmcMsg.setData(CodecUtil.getString(bytes, 20));
		
		return niToOmcMsg;
	}

}