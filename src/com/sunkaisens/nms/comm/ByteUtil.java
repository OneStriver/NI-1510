package com.sunkaisens.nms.comm;

import java.nio.ByteBuffer;

public class ByteUtil {

	public static final int UNSIGNED_CHAR_LEN = 1;
	public static final int UNSIGNED_SHORT_LEN = 2;
	public static final int UNSIGNED_INT_LEN = 4;
	
	/**
	 * byte转换String
	 */
	public static String byteToString(byte[] str, int offset, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = offset; i < offset + length; i++) {
			sb.append(str[i]);
		}
		return sb.toString();
	}
	
	public static byte unsignedCharToByte(short uchar) {
		return (byte) (uchar & 0xFF);
	}

	public static byte[] unsignedShortToBytes(int ushort) {
		byte[] bytes = new byte[2];
		bytes[0] = (byte) ((ushort >> 8) & 0xFF);
		bytes[1] = (byte) ((ushort >> 0) & 0xFF);
		return bytes;
	}

	public static byte[] unsignedIntToBytes(long uint) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) ((uint >> 24) & 0xFF);
		bytes[1] = (byte) ((uint >> 16) & 0xFF);
		bytes[2] = (byte) ((uint >> 8) & 0xFF);
		bytes[3] = (byte) ((uint >> 0) & 0xFF);
		return bytes;
	}

	public static short byteToUnsignedChar(byte bt) {
		return (short) (bt & 0xFF);
	}

	public static int bytesToUnsignedShort(byte[] bytes, int offset) {
		return ((bytes[offset]) & 0xFF) << 8 | (bytes[offset + 1] & 0xFF);
	}

	private static final long modulo_int = ((long) Integer.MAX_VALUE + 1) * 2;

	public static long bytesToUnsignedInt(byte[] bytes, int offset) {
		int x = ((bytes[offset] & 0xFF) << 24)
				| ((bytes[offset + 1] & 0xFF) << 16)
				| ((bytes[offset + 2] & 0xFF) << 8)
				| (bytes[offset + 3] & 0xFF);
		return (x + modulo_int) % modulo_int;
	}

	public static String bytesToString(byte[] bytes, int offset) {
		int i;
		for (i = offset; i < bytes.length; ++i)
			if (0x00 == bytes[i])
				break;
		byte[] arg = new byte[i - offset];
		System.arraycopy(bytes, offset, arg, 0, i - offset);
		return new String(arg);
	}
	
	public static long bytesToLong(byte[] bytes,int offset) {
		ByteBuffer buf = ByteBuffer.allocate(8);
		buf.put(bytes, offset, 8);
		buf.flip();
		return buf.getLong();
	}
}
