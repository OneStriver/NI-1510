package com.sunkaisens.nms.udpMessageThread.messageEncapsulation;

import com.sunkaisens.nms.util.CodecUtil;

/**
 * 信令跟踪上报(0x7000)
 */
public class SignalTrackReportMsg extends CncpBaseMsg {

	private int traceId;
	private String data;

	public int getTraceId() {
		return traceId;
	}

	public void setTraceId(int traceId) {
		this.traceId = traceId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	/**
     * 信令跟踪上报
     */
    public SignalTrackReportMsg readFromSignalTrackReport(byte[] bytes,SignalTrackReportMsg signalTrackReportMsg){
    	//上报消息的源地址
    	signalTrackReportMsg.setSrcType(CodecUtil.getChar(bytes, 0));
    	//上报消息的目的地址
    	signalTrackReportMsg.setDestType(CodecUtil.getChar(bytes, 1));
    	//消息总长度
    	int dataLength = CodecUtil.getUnsignedShort(bytes, 2);
    	signalTrackReportMsg.setMessageLength(dataLength);
    	//消息类型
    	signalTrackReportMsg.setMessageType(CodecUtil.getUnsignedShort(bytes, 4));
    	//事务ID
    	signalTrackReportMsg.setTransId(CodecUtil.getUnsignedShort(bytes, 6));
    	//网元类型
    	signalTrackReportMsg.setNeType(CodecUtil.getInt(bytes, 8));
    	//实例ID
    	signalTrackReportMsg.setInstId(CodecUtil.getInt(bytes, 12));
    	//TraceId
    	signalTrackReportMsg.setTraceId(CodecUtil.getInt(bytes, 16));
    	//data
    	signalTrackReportMsg.setData(CodecUtil.getString(bytes, 20));
    	
    	return signalTrackReportMsg;
    }
	
}
