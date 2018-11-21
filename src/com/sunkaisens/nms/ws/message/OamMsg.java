package com.sunkaisens.nms.ws.message;

import com.sunkaisens.nms.util.CodecUtil;

public class OamMsg extends CCMsg {
	protected long neId;
	protected long instId;

	public OamMsg(char src, char dest, int type, int transId, long neId, long instId) {
		super(src, dest, type, transId);
		this.len += 8;

		this.neId = neId;
		this.instId = instId;
	}

	@Override
	public int code(byte[] array) {
		int offset = super.code(array);
		CodecUtil.putUnsignedInt(array, neId, offset);
		CodecUtil.putUnsignedInt(array, instId, offset + 4);
		return offset + 8;
	}

	public long getNeId() {
		return neId;
	}

	public void setNeId(long neId) {
		this.neId = neId;
	}

	public long getInstId() {
		return instId;
	}

	public void setInstId(long instId) {
		this.instId = instId;
	}
}
