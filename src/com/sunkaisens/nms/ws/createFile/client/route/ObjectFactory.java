package com.sunkaisens.nms.ws.createFile.client.route;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sunkaisens.ni.ws.route package.
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

	private final static QName _RstTermInfo_QNAME = new QName(
			"http://Routing.webservice.com", "rstTermInfo");
	private final static QName _RptRoutingInfo_QNAME = new QName(
			"http://Routing.webservice.com", "rptRoutingInfo");
	private final static QName _RptRoutingMsgResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rptRoutingMsgResponse");
	private final static QName _RptKeyDef_QNAME = new QName(
			"http://Routing.webservice.com", "rptKeyDef");
	private final static QName _RptKeyDefResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rptKeyDefResponse");
	private final static QName _RptTermInfo_QNAME = new QName(
			"http://Routing.webservice.com", "rptTermInfo");
	private final static QName _RptRoutingInfoResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rptRoutingInfoResponse");
	private final static QName _RstKeyDef_QNAME = new QName(
			"http://Routing.webservice.com", "rstKeyDef");
	private final static QName _RptRoutingMsg_QNAME = new QName(
			"http://Routing.webservice.com", "rptRoutingMsg");
	private final static QName _RstRoutingInfo_QNAME = new QName(
			"http://Routing.webservice.com", "rstRoutingInfo");
	private final static QName _RstKeyDefResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rstKeyDefResponse");
	private final static QName _RptTermInfoResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rptTermInfoResponse");
	private final static QName _RstTermInfoResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rstTermInfoResponse");
	private final static QName _RstRoutingInfoResponse_QNAME = new QName(
			"http://Routing.webservice.com", "rstRoutingInfoResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sunkaisens.ni.ws.route
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RstRoutingInfo }
	 * 
	 */
	public RstRoutingInfo createRstRoutingInfo() {
		return new RstRoutingInfo();
	}

	/**
	 * Create an instance of {@link RstKeyDefResponse }
	 * 
	 */
	public RstKeyDefResponse createRstKeyDefResponse() {
		return new RstKeyDefResponse();
	}

	/**
	 * Create an instance of {@link RptTermInfo }
	 * 
	 */
	public RptTermInfo createRptTermInfo() {
		return new RptTermInfo();
	}

	/**
	 * Create an instance of {@link RptRoutingMsgResponse }
	 * 
	 */
	public RptRoutingMsgResponse createRptRoutingMsgResponse() {
		return new RptRoutingMsgResponse();
	}

	/**
	 * Create an instance of {@link RstTermInfo }
	 * 
	 */
	public RstTermInfo createRstTermInfo() {
		return new RstTermInfo();
	}

	/**
	 * Create an instance of {@link RptKeyDefResponse }
	 * 
	 */
	public RptKeyDefResponse createRptKeyDefResponse() {
		return new RptKeyDefResponse();
	}

	/**
	 * Create an instance of {@link RptKeyDef }
	 * 
	 */
	public RptKeyDef createRptKeyDef() {
		return new RptKeyDef();
	}

	/**
	 * Create an instance of {@link RptTermInfoResponse }
	 * 
	 */
	public RptTermInfoResponse createRptTermInfoResponse() {
		return new RptTermInfoResponse();
	}

	/**
	 * Create an instance of {@link RptRoutingInfo }
	 * 
	 */
	public RptRoutingInfo createRptRoutingInfo() {
		return new RptRoutingInfo();
	}

	/**
	 * Create an instance of {@link RstTermInfoResponse }
	 * 
	 */
	public RstTermInfoResponse createRstTermInfoResponse() {
		return new RstTermInfoResponse();
	}

	/**
	 * Create an instance of {@link RstKeyDef }
	 * 
	 */
	public RstKeyDef createRstKeyDef() {
		return new RstKeyDef();
	}

	/**
	 * Create an instance of {@link RptRoutingInfoResponse }
	 * 
	 */
	public RptRoutingInfoResponse createRptRoutingInfoResponse() {
		return new RptRoutingInfoResponse();
	}

	/**
	 * Create an instance of {@link RptRoutingMsg }
	 * 
	 */
	public RptRoutingMsg createRptRoutingMsg() {
		return new RptRoutingMsg();
	}

	/**
	 * Create an instance of {@link RstRoutingInfoResponse }
	 * 
	 */
	public RstRoutingInfoResponse createRstRoutingInfoResponse() {
		return new RstRoutingInfoResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RstTermInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstTermInfo")
	public JAXBElement<RstTermInfo> createRstTermInfo(RstTermInfo value) {
		return new JAXBElement<RstTermInfo>(_RstTermInfo_QNAME,
				RstTermInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptRoutingInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptRoutingInfo")
	public JAXBElement<RptRoutingInfo> createRptRoutingInfo(RptRoutingInfo value) {
		return new JAXBElement<RptRoutingInfo>(_RptRoutingInfo_QNAME,
				RptRoutingInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptRoutingMsgResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptRoutingMsgResponse")
	public JAXBElement<RptRoutingMsgResponse> createRptRoutingMsgResponse(
			RptRoutingMsgResponse value) {
		return new JAXBElement<RptRoutingMsgResponse>(
				_RptRoutingMsgResponse_QNAME, RptRoutingMsgResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptKeyDef }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptKeyDef")
	public JAXBElement<RptKeyDef> createRptKeyDef(RptKeyDef value) {
		return new JAXBElement<RptKeyDef>(_RptKeyDef_QNAME, RptKeyDef.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptKeyDefResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptKeyDefResponse")
	public JAXBElement<RptKeyDefResponse> createRptKeyDefResponse(
			RptKeyDefResponse value) {
		return new JAXBElement<RptKeyDefResponse>(_RptKeyDefResponse_QNAME,
				RptKeyDefResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptTermInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptTermInfo")
	public JAXBElement<RptTermInfo> createRptTermInfo(RptTermInfo value) {
		return new JAXBElement<RptTermInfo>(_RptTermInfo_QNAME,
				RptTermInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptRoutingInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptRoutingInfoResponse")
	public JAXBElement<RptRoutingInfoResponse> createRptRoutingInfoResponse(
			RptRoutingInfoResponse value) {
		return new JAXBElement<RptRoutingInfoResponse>(
				_RptRoutingInfoResponse_QNAME, RptRoutingInfoResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RstKeyDef }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstKeyDef")
	public JAXBElement<RstKeyDef> createRstKeyDef(RstKeyDef value) {
		return new JAXBElement<RstKeyDef>(_RstKeyDef_QNAME, RstKeyDef.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptRoutingMsg }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptRoutingMsg")
	public JAXBElement<RptRoutingMsg> createRptRoutingMsg(RptRoutingMsg value) {
		return new JAXBElement<RptRoutingMsg>(_RptRoutingMsg_QNAME,
				RptRoutingMsg.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RstRoutingInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstRoutingInfo")
	public JAXBElement<RstRoutingInfo> createRstRoutingInfo(RstRoutingInfo value) {
		return new JAXBElement<RstRoutingInfo>(_RstRoutingInfo_QNAME,
				RstRoutingInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RstKeyDefResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstKeyDefResponse")
	public JAXBElement<RstKeyDefResponse> createRstKeyDefResponse(
			RstKeyDefResponse value) {
		return new JAXBElement<RstKeyDefResponse>(_RstKeyDefResponse_QNAME,
				RstKeyDefResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptTermInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rptTermInfoResponse")
	public JAXBElement<RptTermInfoResponse> createRptTermInfoResponse(
			RptTermInfoResponse value) {
		return new JAXBElement<RptTermInfoResponse>(_RptTermInfoResponse_QNAME,
				RptTermInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RstTermInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstTermInfoResponse")
	public JAXBElement<RstTermInfoResponse> createRstTermInfoResponse(
			RstTermInfoResponse value) {
		return new JAXBElement<RstTermInfoResponse>(_RstTermInfoResponse_QNAME,
				RstTermInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RstRoutingInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://Routing.webservice.com", name = "rstRoutingInfoResponse")
	public JAXBElement<RstRoutingInfoResponse> createRstRoutingInfoResponse(
			RstRoutingInfoResponse value) {
		return new JAXBElement<RstRoutingInfoResponse>(
				_RstRoutingInfoResponse_QNAME, RstRoutingInfoResponse.class,
				null, value);
	}

}
