package com.sunkaisens.nms.ws.message;

import com.sunkaisens.nms.util.CodecUtil;

public abstract class CCMsg {
	protected char src;
	protected char dest;
	protected int len;
	protected int type;
	protected int transId;

	public CCMsg(char src, char dest, int type, int transId) {
		this.src = src;
		this.dest = dest;
		this.len = 8;
		this.type = type;
		this.transId = transId;
	}

	public int code(byte[] array) {
		CodecUtil.putUnsignedChar(array, src, 0);
		CodecUtil.putUnsignedChar(array, dest, 1);
		CodecUtil.putUnsignedShort(array, len, 2);
		CodecUtil.putUnsignedShort(array, type, 4);
		CodecUtil.putUnsignedShort(array, transId, 6);
		return 8;
	}

	public char getSrc() {
		return src;
	}

	public void setSrc(char src) {
		this.src = src;
	}

	public char getDest() {
		return dest;
	}

	public void setDest(char dest) {
		this.dest = dest;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}
}
