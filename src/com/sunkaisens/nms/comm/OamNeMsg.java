package com.sunkaisens.nms.comm;

public class OamNeMsg {

	public static final int UNKNOWN_TRANS_ID = 0x0000;
	public static int Id = 0;
	
	protected short magicA;
	protected short magicB;
	protected int msgLen;
	protected int msgType;
	protected int transId;
	
	private long neId;
	private long instId;

	private short type;
	private short subType;
	private int paramLen;
	private byte[] param;

	public OamNeMsg() {
		this((short)0xe4,(short)0x04,0x7001,0l,0l,(short) 0x5,(short)0x1,null,0);
	}

	public OamNeMsg(short src, short dst, int msgType, long neId, 
			long instId,short type, short subType, byte[] param, int paramLen) {
		this.magicA = src;
		this.magicB = dst;
		this.msgLen = getMsgLen();
		this.msgType = msgType;
		this.transId = Id++;
		this.neId = neId;
		this.instId = instId;
		this.type = type;
		this.subType = subType;
		this.paramLen = paramLen;
		this.param = param;
	}

//	public OamNeMsg(byte[] bytes) {
//		this.magicA = ByteUtil.byteToUnsignedChar(bytes[0]);
//		this.magicB = ByteUtil.byteToUnsignedChar(bytes[1]);
//		this.msgLen = getMsgLen();
//		this.msgType = ByteUtil.bytesToUnsignedShort(bytes, 4);
//		this.transId = ByteUtil.bytesToUnsignedShort(bytes, 6);
//		this.neId = ByteUtil.bytesToUnsignedInt(bytes, 8);
//		this.instId = ByteUtil.bytesToUnsignedInt(bytes, 12);
//	}



	public byte[] getBytes() {
		byte[] bytes = new byte[getMsgLen()];
		bytes[0] = ByteUtil.unsignedCharToByte(magicA);
		bytes[1] = ByteUtil.unsignedCharToByte(magicB);
		System.arraycopy(ByteUtil.unsignedShortToBytes(msgLen), 0, bytes, 2, 2);
		System.arraycopy(ByteUtil.unsignedShortToBytes(msgType), 0, bytes, 4, 2);
		System.arraycopy(ByteUtil.unsignedShortToBytes(transId), 0, bytes, 6, 2);
		System.arraycopy(ByteUtil.unsignedIntToBytes(neId), 0, bytes,8, 4);
		System.arraycopy(ByteUtil.unsignedIntToBytes(instId), 0, bytes,12, 4);
		bytes[16] = ByteUtil.unsignedCharToByte(type);
		bytes[17] = ByteUtil.unsignedCharToByte(subType);
		System.arraycopy(ByteUtil.unsignedShortToBytes(paramLen), 0, bytes,18, 2);
		System.arraycopy(param, 0, bytes, 20, paramLen);
		return bytes;
	}

	public short getSrc() {
		return magicA;
	}

	public short getDst() {
		return magicB;
	}

	public int getMsgLen() {
		return 20 + paramLen;
	}

	public int getMsgType() {
		return msgType;
	}

	public int getTransId() {
		return transId;
	}

	public void setSrc(short src) {
		this.magicA = src;
	}

	public void setDst(short dst) {
		this.magicB = dst;
	}

	public void setMsgLen(int msgLen) {
		this.msgLen = msgLen;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public static int getMsgType(byte[] bytes) {
		return ByteUtil.bytesToUnsignedShort(bytes, 4);
	}

	public static int getTransId(byte[] bytes) {
		return ByteUtil.bytesToUnsignedShort(bytes, 6);
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

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getSubType() {
		return subType;
	}

	public void setSubType(short subType) {
		this.subType = subType;
	}

	public int getParamLen() {
		return paramLen;
	}

	public void setParamLen(int paramLen) {
		this.paramLen = paramLen;
	}

	public byte[] getParam() {
		return param;
	}

	public void setParam(byte[] param) {
		this.param = param;
	}
}
