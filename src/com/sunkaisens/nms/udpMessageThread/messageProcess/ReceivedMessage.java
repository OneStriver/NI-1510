package com.sunkaisens.nms.udpMessageThread.messageProcess;

public class ReceivedMessage {
	private int type;
	private Object message;

	public ReceivedMessage() {
		
	}

	public ReceivedMessage(int type, Object message) {
		this.type = type;
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

}
