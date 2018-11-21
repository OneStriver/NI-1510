package com.sunkaisens.nms.notify.route;

import java.net.URL;

import javax.xml.namespace.QName;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.route.RoutingService;
import com.sunkaisens.nms.ws.createFile.client.route.RoutingWebService;

public class RouteServiceConnect {
	
	public static RoutingWebService routingWebService;
	public static boolean getRouteServiceConnect() {
		try {
			String wsdl = ConfigurationManager.getInstance().getRemoteRoutingWsdl();
			routingWebService = new RoutingService(new URL(RoutingService.class.getResource("."), wsdl),
					new QName("http://Routing.webservice.com", "Routing_Service")).getRoutingEndPoint();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
