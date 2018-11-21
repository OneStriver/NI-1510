package com.sunkaisens.nms.ws.createFile.client.alarm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.sunkaisens.ni.ws.alarm package.
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

	private final static QName _RptAlarmResponse_QNAME = new QName(
			"http://AlarmManage.webservice.com", "rptAlarmResponse");
	private final static QName _RptAlarm_QNAME = new QName(
			"http://AlarmManage.webservice.com", "rptAlarm");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.sunkaisens.ni.ws.alarm
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link RptAlarm }
	 * 
	 */
	public RptAlarm createRptAlarm() {
		return new RptAlarm();
	}

	/**
	 * Create an instance of {@link RptAlarmResponse }
	 * 
	 */
	public RptAlarmResponse createRptAlarmResponse() {
		return new RptAlarmResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RptAlarmResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://AlarmManage.webservice.com", name = "rptAlarmResponse")
	public JAXBElement<RptAlarmResponse> createRptAlarmResponse(
			RptAlarmResponse value) {
		return new JAXBElement<RptAlarmResponse>(_RptAlarmResponse_QNAME,
				RptAlarmResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RptAlarm }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://AlarmManage.webservice.com", name = "rptAlarm")
	public JAXBElement<RptAlarm> createRptAlarm(RptAlarm value) {
		return new JAXBElement<RptAlarm>(_RptAlarm_QNAME, RptAlarm.class, null,
				value);
	}

}
