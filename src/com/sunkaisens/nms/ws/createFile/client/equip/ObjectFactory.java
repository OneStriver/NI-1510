package com.sunkaisens.nms.ws.createFile.client.equip;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sunkaisens.ni.ws.equip package.
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

	private final static QName _RptDevInfoResponse_QNAME = new QName(
			"http://DevManage.webservice.com", "rptDevInfoResponse");
	private final static QName _RptDevInfo_QNAME = new QName(
			"http://DevManage.webservice.com", "rptDevInfo");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sunkaisens.ni.ws.equip
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptDevInfo }
	 * 
	 */
	public RptDevInfo createRptDevInfo() {
		return new RptDevInfo();
	}

	/**
	 * Create an instance of {@link RptDevInfoResponse }
	 * 
	 */
	public RptDevInfoResponse createRptDevInfoResponse() {
		return new RptDevInfoResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptDevInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://DevManage.webservice.com", name = "rptDevInfoResponse")
	public JAXBElement<RptDevInfoResponse> createRptDevInfoResponse(
			RptDevInfoResponse value) {
		return new JAXBElement<RptDevInfoResponse>(_RptDevInfoResponse_QNAME,
				RptDevInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptDevInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://DevManage.webservice.com", name = "rptDevInfo")
	public JAXBElement<RptDevInfo> createRptDevInfo(RptDevInfo value) {
		return new JAXBElement<RptDevInfo>(_RptDevInfo_QNAME, RptDevInfo.class,
				null, value);
	}

}
