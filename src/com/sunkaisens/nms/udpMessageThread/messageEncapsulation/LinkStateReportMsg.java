package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * 链路状态上报  (0x7007)
 */
public class LinkStateReportMsg extends CncpBaseMsg {

	private int linkId;
	private char action;
	private char status;
	private char linkType;
	private char linkSubType;
	private int localPort;
	private int remotePort;
	private String localIp;
	private String remoteIP;

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public char getAction() {
		return action;
	}

	public void setAction(char action) {
		this.action = action;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getLinkType() {
		return linkType;
	}

	public void setLinkType(char linkType) {
		this.linkType = linkType;
	}

	public char getLinkSubType() {
		return linkSubType;
	}

	public void setLinkSubType(char linkSubType) {
		this.linkSubType = linkSubType;
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}
	
	/**
	 * 链路状态上报
	 */
	public LinkStateReportMsg readFromLinkStateReport(byte[] bytes, LinkStateReportMsg linkStateReportMsg) {
		// 上报消息的源地址
		linkStateReportMsg.setSrcType(CodecUtil.getChar(bytes, 0));
		// 上报消息的目的地址
		linkStateReportMsg.setDestType(CodecUtil.getChar(bytes, 1));
		// 消息总长度
		int dataLength = CodecUtil.getUnsignedShort(bytes, 2);
		linkStateReportMsg.setMessageLength(dataLength);
		// 消息类型
		linkStateReportMsg.setMessageType(CodecUtil.getUnsignedShort(bytes, 4));
		// 事务ID
		linkStateReportMsg.setTransId(CodecUtil.getUnsignedShort(bytes, 6));
		// 网元类型
		linkStateReportMsg.setNeType(CodecUtil.getInt(bytes, 8));
		// 实例ID
		linkStateReportMsg.setInstId(CodecUtil.getInt(bytes, 12));
		//linkId
		linkStateReportMsg.setLinkId(CodecUtil.getInt(bytes, 16));
		//action
		linkStateReportMsg.setAction(CodecUtil.getChar(bytes, 20));
		//status
		linkStateReportMsg.setStatus(CodecUtil.getChar(bytes, 21));
		//linkType
		linkStateReportMsg.setLinkType(CodecUtil.getChar(bytes, 22));
		//linkSubType
		linkStateReportMsg.setLinkSubType(CodecUtil.getChar(bytes, 23));
		//LocalPort
		linkStateReportMsg.setLocalPort(CodecUtil.getUnsignedShort(bytes, 24));
		//RemotePort
		linkStateReportMsg.setRemotePort(CodecUtil.getUnsignedShort(bytes, 26));
		//LocalIP
		linkStateReportMsg.setLocalIp(CodecUtil.getString(bytes, 28, 4));
		//RemoteIP
		linkStateReportMsg.setRemoteIP(CodecUtil.getString(bytes, 32, 4));

		return linkStateReportMsg;
	}
}
