package com.sunkaisens.nms.ws.createFile.client.equip;

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
 * DevManage_Service service = new DevManage_Service();
 * DevManageWebService portType = service.getDevManageEndPoint();
 * portType.rptDevInfo(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "DevManage_Service", targetNamespace = "http://DevManage.webservice.com", wsdlLocation = "file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/DevManage_Service.xml")
public class DevManageService extends Service {

	private final static URL DEVMANAGESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.sunkaisens.nms.ws.createFile.client.equip.DevManageService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.sunkaisens.nms.ws.createFile.client.equip.DevManageService.class
					.getResource(".");
			url = new URL(
					baseUrl,
					"file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/DevManage_Service.xml");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'file:/F:/\u9879\u76ee/1510/\u63a5\u53e3\u6587\u6863/\u5317\u5411\u63a5\u53e3\u5f00\u53d1\u8ddf\u8e2a/wsdl-20171112/DevManage_Service.xml', retrying as a local file");
			logger.warning(e.getMessage());
		}
		DEVMANAGESERVICE_WSDL_LOCATION = url;
	}

	public DevManageService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public DevManageService() {
		super(DEVMANAGESERVICE_WSDL_LOCATION, new QName(
				"http://DevManage.webservice.com", "DevManage_Service"));
	}

	/**
	 * 
	 * @return returns DevManageWebService
	 */
	@WebEndpoint(name = "DevManage-EndPoint")
	public DevManageWebService getDevManageEndPoint() {
		return super.getPort(new QName("http://DevManage.webservice.com",
				"DevManage-EndPoint"), DevManageWebService.class);
	}

}
