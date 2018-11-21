package com.sunkaisens.nms.ws.createFile.client.log;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sunkaisens.ni.ws.log package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _RptLog_QNAME = new QName(
			"http://LogManage.webservice.com", "rptLog");
	private final static QName _RptLogResponse_QNAME = new QName(
			"http://LogManage.webservice.com", "rptLogResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sunkaisens.ni.ws.log
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptLogResponse }
	 * 
	 */
	public RptLogResponse createRptLogResponse() {
		return new RptLogResponse();
	}

	/**
	 * Create an instance of {@link RptLog }
	 * 
	 */
	public RptLog createRptLog() {
		return new RptLog();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptLog }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://LogManage.webservice.com", name = "rptLog")
	public JAXBElement<RptLog> createRptLog(RptLog value) {
		return new JAXBElement<RptLog>(_RptLog_QNAME, RptLog.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptLogResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://LogManage.webservice.com", name = "rptLogResponse")
	public JAXBElement<RptLogResponse> createRptLogResponse(RptLogResponse value) {
		return new JAXBElement<RptLogResponse>(_RptLogResponse_QNAME,
				RptLogResponse.class, null, value);
	}

}
