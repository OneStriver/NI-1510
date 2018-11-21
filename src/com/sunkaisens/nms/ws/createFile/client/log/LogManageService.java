package com.sunkaisens.nms.ws.createFile.client.log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * LogManage_Service service = new LogManage_Service();
 * LogManageWebService portType = service.getLogManageEndPoint();
 * portType.rptLog(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "LogManage_Service", targetNamespace = "http://LogManage.webservice.com", wsdlLocation = "file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/LogManage_Service.xml")
public class LogManageService extends Service {

	private final static URL LOGMANAGESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.sunkaisens.nms.ws.createFile.client.log.LogManageService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.sunkaisens.nms.ws.createFile.client.log.LogManageService.class
					.getResource(".");
			url = new URL(
					baseUrl,
					"file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/LogManage_Service.xml");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/LogManage_Service.xml', retrying as a local file");
			logger.warning(e.getMessage());
		}
		LOGMANAGESERVICE_WSDL_LOCATION = url;
	}

	public LogManageService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public LogManageService() {
		super(LOGMANAGESERVICE_WSDL_LOCATION, new QName(
				"http://LogManage.webservice.com", "LogManage_Service"));
	}

	/**
	 * 
	 * @return returns LogManageWebService
	 */
	@WebEndpoint(name = "LogManage-EndPoint")
	public LogManageWebService getLogManageEndPoint() {
		return super.getPort(new QName("http://LogManage.webservice.com",
				"LogManage-EndPoint"), LogManageWebService.class);
	}

}