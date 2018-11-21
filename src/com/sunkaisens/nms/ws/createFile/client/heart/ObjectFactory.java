package com.sunkaisens.nms.ws.createFile.client.heart;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the HeartBeat.webservice.com package.
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

	private final static QName _RptHeartBeat_QNAME = new QName(
			"http://HeartBeat.webservice.com", "rptHeartBeat");
	private final static QName _RptHeartBeatResponse_QNAME = new QName(
			"http://HeartBeat.webservice.com", "rptHeartBeatResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: HeartBeat.webservice.com
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptHeartBeat }
	 * 
	 */
	public RptHeartBeat createRptHeartBeat() {
		return new RptHeartBeat();
	}

	/**
	 * Create an instance of {@link RptHeartBeatResponse }
	 * 
	 */
	public RptHeartBeatResponse createRptHeartBeatResponse() {
		return new RptHeartBeatResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptHeartBeat }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://HeartBeat.webservice.com", name = "rptHeartBeat")
	public JAXBElement<RptHeartBeat> createRptHeartBeat(RptHeartBeat value) {
		return new JAXBElement<RptHeartBeat>(_RptHeartBeat_QNAME,
				RptHeartBeat.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptHeartBeatResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://HeartBeat.webservice.com", name = "rptHeartBeatResponse")
	public JAXBElement<RptHeartBeatResponse> createRptHeartBeatResponse(
			RptHeartBeatResponse value) {
		return new JAXBElement<RptHeartBeatResponse>(
				_RptHeartBeatResponse_QNAME, RptHeartBeatResponse.class, null,
				value);
	}

}
