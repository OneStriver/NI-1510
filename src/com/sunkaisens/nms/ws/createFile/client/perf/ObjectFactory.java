package com.sunkaisens.nms.ws.createFile.client.perf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sunkaisens.ni.ws.perf package.
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

	private final static QName _RptPerfResponse_QNAME = new QName(
			"http://PerfManage.webservice.com", "rptPerfResponse");
	private final static QName _RptPerf_QNAME = new QName(
			"http://PerfManage.webservice.com", "rptPerf");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sunkaisens.ni.ws.perf
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptPerf }
	 * 
	 */
	public RptPerf createRptPerf() {
		return new RptPerf();
	}

	/**
	 * Create an instance of {@link RptPerfResponse }
	 * 
	 */
	public RptPerfResponse createRptPerfResponse() {
		return new RptPerfResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptPerfResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://PerfManage.webservice.com", name = "rptPerfResponse")
	public JAXBElement<RptPerfResponse> createRptPerfResponse(
			RptPerfResponse value) {
		return new JAXBElement<RptPerfResponse>(_RptPerfResponse_QNAME,
				RptPerfResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptPerf }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://PerfManage.webservice.com", name = "rptPerf")
	public JAXBElement<RptPerf> createRptPerf(RptPerf value) {
		return new JAXBElement<RptPerf>(_RptPerf_QNAME, RptPerf.class, null,
				value);
	}

}
