package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * 告警清除上报 (0x7010)
 */
public class AlarmClearReportMsg extends CncpBaseMsg {

	private int alarmCode;
	private char alarmSeverity;
	private String eventTime;
	private String alarmDesc;

	public int getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(int alarmCode) {
		this.alarmCode = alarmCode;
	}

	public char getAlarmSeverity() {
		return alarmSeverity;
	}

	public void setAlarmSeverity(char alarmSeverity) {
		this.alarmSeverity = alarmSeverity;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	/**
	 * 告警清除上报
	 */
	public AlarmClearReportMsg readFromAlarmReport(byte[] bytes, AlarmClearReportMsg alarmReportMsg) {
		// 上报消息的源地址
		alarmReportMsg.setSrcType(CodecUtil.getChar(bytes, 0));
		// 上报消息的目的地址
		alarmReportMsg.setDestType(CodecUtil.getChar(bytes, 1));
		// 消息总长度
		int dataLength = CodecUtil.getUnsignedShort(bytes, 2);
		alarmReportMsg.setMessageLength(dataLength);
		// 消息类型
		alarmReportMsg.setMessageType(CodecUtil.getUnsignedShort(bytes, 4));
		// 事务ID
		alarmReportMsg.setTransId(CodecUtil.getUnsignedShort(bytes, 6));
		// 网元类型
		alarmReportMsg.setNeType(CodecUtil.getInt(bytes, 8));
		// 实例ID
		alarmReportMsg.setInstId(CodecUtil.getInt(bytes, 12));
		// AlarmCode
		alarmReportMsg.setAlarmCode(CodecUtil.getInt(bytes, 16));
		// AlarmSeverity
		alarmReportMsg.setAlarmSeverity(CodecUtil.getChar(bytes, 20));
		// EventTime
		alarmReportMsg.setEventTime(CodecUtil.getString(bytes, 21, 31));
		// AlarmDesc
		alarmReportMsg.setAlarmDesc(CodecUtil.getString(bytes, 52));

		return alarmReportMsg;
	}
}
