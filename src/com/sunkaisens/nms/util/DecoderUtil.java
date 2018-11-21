package com.sunkaisens.nms.util;

public class DecoderUtil {
	
	//:未知类型
	public static final int UNKNOW = 0x00;
	//:有线用户
	public static final int SIP = 0x01;
	//:CDMA
	public static final int CDMA = 0x02;
	//:GSM
	public static final int GSM = 0x03;
	//:WCDMA
	public static final int WCDMA = 0x04;
	//:TD专网
	public static final int TD = 0x05;
	//:野战
	public static final int YD = 0x06;
	//:集群用户
	public static final int TR = 0x07;
	//:调度台用户
	public static final int DISP = 0x08;
	//:Tetra集群用户
	public static final int TETRA = 0x09;
	//:模拟用户
	public static final int ANALOG = 0x0A;
	//:电台用户
	public static final int RADIO = 0x0B;
	
	public String deviceTypeTrans(Integer t){
		String res;
		switch(t){
			case UNKNOW: {res = "UNKNOW";break;}  	//:未知类型
			case SIP: {res = "SIP"; break;}  		//:有线用户
			case CDMA: {res = "CDMA"; break;}   	//:CDMA
			case GSM: {res = "GSM"; break;}			//:GSM
			case WCDMA: {res = "WCDMA"; break;}		//:WCDMA
			case TD: {res = "TD"; break;}    		//:TD
			case YD: {res = "YD"; break;}  			//:野战
			case TR: {res = "TR"; break;}    		//:集群
			case DISP: {res = "DISP"; break;}		//:调度台
			case TETRA:{res = "TETRA"; break;}		//:Tetra
			case ANALOG:{res = "ANALOG"; break;}	//:模拟用户
			case RADIO:{res = "RADIO"; break;}		//:电台用户
			default:{
				return "No_Match_Type";
			}
		}
		return res;
	}
	
	/* 语音编码 */
	public static final int PCMU = 0x00;
	
	public static final int PCMA = 0x08;
	
	public static final int G_729 = 0x12;
	
	public static final int EVRC = 0x23;
	
	public static final int AHELP = 0x26;
	
	public static final int ACELP = 0x27;
	
	public static final int AMR = 0x28;
	
	public static String voCodeTrans(Integer c){
		String res;
		switch(c){
			case PCMU: {res = "PCMU"; break;}  		
			case PCMA: {res = "PCMA"; break;}  	
			case G_729: {res = "G.729"; break;}	
			case EVRC: {res = "EVRC"; break;}   	
			case AHELP: {res = "AHELP"; break;}		
			case ACELP: {res = "ACELP"; break;}		
			case AMR: {res = "AMR"; break;}	
			default:{
				return "No_Match_Type";
			}
		}
		return res;
	}
	
	/* msProfile */
	public static int countMsProfile(int[] a){
		int count = 0;
		for(int i=0,j=7 ; i<32 ; i+=4 , j--) {
			count += (a[i]*Math.pow(2, 3) + a[i+1]*Math.pow(2, 2)
					+ a[i+2]*Math.pow(2, 1) + a[i+3]) * Math.pow(16, j);
			// 左移28 24 20 16 12 8 4 0位
		}
		return count;
	}
	
	public static Integer TSMap_TransforSignalling(String hex){
		if(!hex.startsWith("0x") || hex.length() != 10){
			return 0;
		}
		//:16进制字符过大 需切割后转译
		String h1 = hex.substring(2, 6);
		String h2 = hex.substring(6);
		//:转换为10进制
		int h1_val = Integer.valueOf(h1,16);
		int h2_val = Integer.valueOf(h2,16);
		//:转换为2进制
		String binary_h1 = Integer.toBinaryString(h1_val);
		String binary_h2 = Integer.toBinaryString(h2_val);
		//:补位
		if(binary_h1.length() < 16)
			binary_h1 = binaryPatch(h1,16);
		if(binary_h2.length() < 16)
			binary_h2 = binaryPatch(h2,16);
		//:拼装
		String fixed = binary_h1 + binary_h2;
		//:定位
		char[] c = fixed.toCharArray();
		int i=0;
		for(;i<c.length;i++){
			if(c[i] == '0') break;
		}
		return 31 - i;
	}
	
	private static String binaryPatch(String b , int len){
		if(b.length() >= len || len < 0)  return b;
		else{
			int fix_num = len - b.length();
			String fix = "";
			for(int i=0;i<fix_num;i++){
				fix += "0";
			}
			b = fix + b;
		}
		return b;
	}
	
}
