package com.sunkaisens.nms.util;

import java.io.UnsupportedEncodingException;

public abstract class CodecUtil {
	// private static final String US_ASCII = "US-ASCII";
	private static final String UTF_8 = "UTF-8";

	/**
	 * 从字节流中读取一个字符
	 */
	public static char getChar(byte[] bb, int index) {
		return (char) (bb[index] & 0xff);
	}

	/**
	 * 从字节流中读取一个无符号字符
	 */
	public static short getUnsignedChar(byte[] bb, int index) {
		return (short) (bb[index] & 0xff);
	}

	public static short getUnsignedChar(byte b) {
		return (short) (b & 0xff);
	}

	/**
	 * 从字节流中读取一个短整型
	 */
	public static short getShort(byte[] bb, int index) {
		return (short) (((bb[index] & 0xff) << 8) | bb[index + 1] & 0xff);
	}

	/**
	 * 从字节流中反向读取一个短整型
	 */
	public static short getReverseBytesShort(byte[] bb, int index) {
		return (short) (((bb[index + 1] & 0xff) << 8) | bb[index] & 0xff);
	}

	/**
	 * 从字节流中读取一个无符号短整型
	 */
	public static int getUnsignedShort(byte[] bb, int index) {
		return ((bb[index] & 0xff) << 8) | bb[index + 1] & 0xff;
	}

	/**
	 * 从字节流中反向读取一个无符号短整型
	 */
	public static int getReverseUnsignedShort(byte[] bb, int index) {
		return ((bb[index + 1] & 0xff) << 8) | bb[index] & 0xff;
	}

	/**
	 * 从字节流中读取一个整型
	 */
	public static int getInt(byte[] bytes, int index) {
		return ((bytes[index + 0] & 0xff) << 24) | ((bytes[index + 1] & 0xff) << 16) | ((bytes[index + 2] & 0xff) << 8)
				| ((bytes[index + 3] & 0xff) << 0);
	}
	
	/**
	 * 从字节流中反向读取一个整型
	 */
	public static int getReverseBytesInt(byte[] bb, int index) {
		return ((bb[index + 3] & 0xff) << 24) | ((bb[index + 2] & 0xff) << 16) | ((bb[index + 1] & 0xff) << 8)
				| ((bb[index + 0] & 0xff) << 0);
	}

	/**
	 * 从字节流中读取一个无符号整型
	 */
	public static long getUnsignedInt(byte[] bb, int index) {
		long max = (Integer.MAX_VALUE + 1) * 2;
		return (getInt(bb, index) + max) % max;
	}

	/**
	 * 从字节流中反向读取一个无符号整型
	 */
	public static long getReverseUnsignedInt(byte[] bb, int index) {
		long max = (Integer.MAX_VALUE + 1) * 2;
		return (getReverseBytesInt(bb, index) + max) % max;
	}

	/**
	 * 从字节流中读取一个长整型
	 */
	public static long getLong(byte[] bb, int index) {
		return ((((long) bb[index + 0] & 0xff) << 56) | (((long) bb[index + 1] & 0xff) << 48)
				| (((long) bb[index + 2] & 0xff) << 40) | (((long) bb[index + 3] & 0xff) << 32)
				| (((long) bb[index + 4] & 0xff) << 24) | (((long) bb[index + 5] & 0xff) << 16)
				| (((long) bb[index + 6] & 0xff) << 8) | (((long) bb[index + 7] & 0xff) << 0));
	}

	/**
	 * 从字节流中反向读取一个长整型
	 */
	public static long getReverseBytesLong(byte[] bb, int index) {
		return ((((long) bb[index + 7] & 0xff) << 56) | (((long) bb[index + 6] & 0xff) << 48)
				| (((long) bb[index + 5] & 0xff) << 40) | (((long) bb[index + 4] & 0xff) << 32)
				| (((long) bb[index + 3] & 0xff) << 24) | (((long) bb[index + 2] & 0xff) << 16)
				| (((long) bb[index + 1] & 0xff) << 8) | (((long) bb[index + 0] & 0xff) << 0));
	}

	/**
	 * 从字节流中读取一个字符串
	 */
	public static String getString(byte[] b, int offset) {
		try {
			return new String(b, offset, b.length - offset, UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从字节流中读取一个字符串
	 */
	public static String getString(byte[] b, int offset, int len) {
		try {
			return new String(b, offset, len, UTF_8);
		} catch (UnsupportedEncodingException x) {
			throw new RuntimeException(x);
		}
	}

	/**
	 * 往字节流中放入一个字符串，如果字符串长度小于len，则补'0'
	 */
	public static void putString(byte[] b, String s, int offset, int len) {
		int l = s.length();
		if (l < len) {
			for (int i = 0; i < l; i++) {
				b[offset + i] = (byte) s.charAt(i);
			}
			for (int i = 0; i < len - l; i++) {
				b[offset + l + i] = '0';
			}
		} else {
			for (int i = 0; i < len; i++) {
				b[offset + i] = (byte) s.charAt(i);
			}
		}
	}

	/**
	 * 往字节流中放入一个字符
	 */
	public static void putChar(byte[] b, char c, int offset) {
		b[offset] = (byte) c;
	}

	/**
	 * 往字节流中放入一个int,只取一个字节,和putChar效果一样！
	 */
	public static void putUnsignedChar(byte b[], int s, int index) {
		b[index] = (byte) (s >> 0);
	}

	/**
	 * 往字节流中放入一个short
	 */
	public static void putShort(byte b[], short s, int index) {
		b[index] = (byte) (s >> 8);
		b[index + 1] = (byte) (s >> 0);
	}

	/**
	 * 往字节流中放入一个 unsigned short
	 */
	public static void putUnsignedShort(byte b[], int s, int index) {
		b[index] = (byte) (s >> 8);
		b[index + 1] = (byte) (s >> 0);
	}

	/**
	 * 往字节流中放入一个 unsigned int
	 */
	public static void putUnsignedInt(byte b[], long s, int index) {
		b[index] = (byte) (s >> 24);
		b[index + 1] = (byte) (s >> 16);
		b[index + 2] = (byte) (s >> 8);
		b[index + 3] = (byte) (s >> 0);
	}

	/**
	 * 将网络字节流转化成字符串
	 */
	public static String toHexString(byte[] b) {
		if (b == null) {
			return new String("null data");
		}

		String m = "";
		for (int i = 0; i < b.length; i++) {
			String temp = Integer.toHexString(b[i] & (0xff));
			if (temp.length() == 1) {
				temp = 0 + temp;
			}
			m += temp;
		}
		return m;
	}

	/**
	 * 往字节流中放入一个 int
	 */
	public static void putInt(byte b[], int i, int index) {
		b[index] = (byte) (i >> 24);
		b[index + 1] = (byte) (i >> 16);
		b[index + 2] = (byte) (i >> 8);
		b[index + 3] = (byte) (i >> 0);
	}

	/**
	 * 往字节流中放入一个以BCD编码的字符串
	 */
	public static void putBCDString(byte b[], String s, int index, int length) {
		for (int i = 0; i < length; i++) {
			b[index + i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
		}
	}

	/**
	 * 将指定长度的网络字节流转化成字符串
	 */
	public static String getBCDString(byte[] b, int index, int endInclude) {
		if (b == null) {
			return new String("null data");
		}
		String m = "";
		for (int i = index; i < endInclude; i++) {
			String temp = Integer.toHexString(b[i] & (0xff));
			if (temp.length() == 1) {
				temp = 0 + temp;
			}
			m += temp;
		}
		return m;
	}

	public static void putStringToChars(byte[] bytes, String dataStr, int offset) {
		byte[] data = dataStr.getBytes();
		System.arraycopy(data, 0, bytes, offset, data.length);
	}

}
