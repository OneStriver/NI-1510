package com.sunkaisens.nms.ws.createFile.client.log;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for rptLogResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="rptLogResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rptLogResponse", propOrder = { "_return" })
public class RptLogResponse {

	@XmlElement(name = "return")
	protected boolean _return;

	/**
	 * Gets the value of the return property.
	 * 
	 */
	public boolean isReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 */
	public void setReturn(boolean value) {
		this._return = value;
	}

}
