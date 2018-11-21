package com.sunkaisens.nms.notify.device;

import java.net.URL;

import javax.xml.namespace.QName;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.equip.DevManageService;
import com.sunkaisens.nms.ws.createFile.client.equip.DevManageWebService;

public class DevServiceConnect {
	
	public static DevManageWebService devManageWebService;
	public static boolean DevStatusConnect() {
		try {
			String wsdl = ConfigurationManager.getInstance().getRemoteDevManageWsdl();
			devManageWebService = new DevManageService(new URL(DevManageService.class.getResource("."), wsdl),
					new QName("http://DevManage.webservice.com", "DevManage_Service")).getDevManageEndPoint();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
