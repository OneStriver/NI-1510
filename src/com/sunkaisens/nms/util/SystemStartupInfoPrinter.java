/**
 * 
 */
package com.sunkaisens.nms.util;

/**
 * 用于在系统启动时向屏幕输出各个部件的启动成功与否信息
 * @author xunzy
 *
 */
public final class SystemStartupInfoPrinter {
	
	private SystemStartupInfoPrinter(){}
	
	private static final String format = "%1$-80s[%2$S]\n";
	
	
	public static void startUpSuccessfully(Class<?> sys){
		System.out.printf(format, sys.getSimpleName(), "OK");
	}
	
	public static void startUpFailed(Class<?> sys){
		System.out.printf(format, sys.getSimpleName(), "Failed");
	}
	
	
	public static void startUpSuccessfully(String sys){
		System.out.printf(format, sys, "OK");
	}
	
	public static void startUpFailed(String sys){
		System.out.printf(format, sys, "Failed");
	}
}
