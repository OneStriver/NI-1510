package com.sunkaisens.nms.notify.log;

import java.net.URL;

import javax.xml.namespace.QName;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.log.LogManageService;
import com.sunkaisens.nms.ws.createFile.client.log.LogManageWebService;

public class LogServiceConnect {
	
	public static LogManageWebService logManageWebService;
	public static boolean getLogServiceConnect() {
		try {
			String wsdl = ConfigurationManager.getInstance().getRemoteLogManageWsdl();
			logManageWebService = new LogManageService(new URL(LogManageService.class.getResource("."), wsdl),
					new QName("http://LogManage.webservice.com", "LogManage_Service")).getLogManageEndPoint();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
