package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * 设备状态上报 (0x700E)
 */
public class DeviceStateReportMsg extends CncpBaseMsg {

	private int reportType;
	private int reportSubType;
	private int paramLen;
	private String params;

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public int getReportSubType() {
		return reportSubType;
	}

	public void setReportSubType(int reportSubType) {
		this.reportSubType = reportSubType;
	}

	public int getParamLen() {
		return paramLen;
	}

	public void setParamLen(int paramLen) {
		this.paramLen = paramLen;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * 设备状态上报
	 */
	public DeviceStateReportMsg readFromAlarmReport(byte[] bytes, DeviceStateReportMsg deviceStateReportMsg) {
		// 上报消息的源地址
		deviceStateReportMsg.setSrcType(CodecUtil.getChar(bytes, 0));
		// 上报消息的目的地址
		deviceStateReportMsg.setDestType(CodecUtil.getChar(bytes, 1));
		// 消息总长度
		int dataLength = CodecUtil.getUnsignedShort(bytes, 2);
		deviceStateReportMsg.setMessageLength(dataLength);
		// 消息类型
		deviceStateReportMsg.setMessageType(CodecUtil.getUnsignedShort(bytes, 4));
		// 事务ID
		deviceStateReportMsg.setTransId(CodecUtil.getUnsignedShort(bytes, 6));
		// 网元类型
		deviceStateReportMsg.setNeType(CodecUtil.getInt(bytes, 8));
		// 实例ID
		deviceStateReportMsg.setInstId(CodecUtil.getInt(bytes, 12));
		//reportType
		deviceStateReportMsg.setReportType(CodecUtil.getUnsignedShort(bytes, 16));
		//reportSubType
		deviceStateReportMsg.setReportSubType(CodecUtil.getUnsignedShort(bytes, 18));
		//paramLen
		int paramLen = CodecUtil.getUnsignedShort(bytes, 20);
		deviceStateReportMsg.setParamLen(paramLen);
		//params
		deviceStateReportMsg.setParams(CodecUtil.getString(bytes, 22, paramLen));
		
		return deviceStateReportMsg;
	}
}
