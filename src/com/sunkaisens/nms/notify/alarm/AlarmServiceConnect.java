package com.sunkaisens.nms.notify.alarm;

import java.net.URL;

import javax.xml.namespace.QName;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.alarm.AlarmManageService;
import com.sunkaisens.nms.ws.createFile.client.alarm.AlarmManageWebService;

public class AlarmServiceConnect {
	
	public static AlarmManageWebService delegateService;
	public static boolean AlarmInfoConnect() {
		try {
			String wsdl = ConfigurationManager.getInstance().getRemoteAlarmManageWsdl();
			delegateService = new AlarmManageService(new URL(AlarmManageService.class.getResource("."), wsdl),
					new QName("http://AlarmManage.webservice.com", "AlarmManage_Service")).getAlarmManageEndPoint();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
