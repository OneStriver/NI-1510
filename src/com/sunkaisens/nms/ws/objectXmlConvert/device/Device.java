package com.sunkaisens.nms.ws.objectXmlConvert.device;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
public class Device {

	private String type;
	private String index;
	private String status;

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	@XmlAttribute
	public void setIndex(String index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	@XmlAttribute
	public void setStatus(String status) {
		this.status = status;
	}

}
