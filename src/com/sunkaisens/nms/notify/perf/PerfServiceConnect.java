package com.sunkaisens.nms.notify.perf;

import java.net.URL;

import javax.xml.namespace.QName;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.perf.PerfManageService;
import com.sunkaisens.nms.ws.createFile.client.perf.PerfManageWebService;

public class PerfServiceConnect {
	
	public static PerfManageWebService perfManageWebService;
	public static boolean getPerfServiceConnect() {
		try {
			String wsdl = ConfigurationManager.getInstance().getRemotePerfManageWsdl();
			perfManageWebService = new PerfManageService(new URL(PerfManageService.class.getResource("."), wsdl),
					new QName("http://PerfManage.webservice.com", "PerfManage_Service")).getPerfManageEndPoint();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
