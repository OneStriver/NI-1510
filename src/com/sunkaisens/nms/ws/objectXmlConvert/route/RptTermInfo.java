package com.sunkaisens.nms.ws.objectXmlConvert.route;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rptTermInfo")
public class RptTermInfo {

	private String filename;

	public String getFilename() {
		return filename;
	}

	@XmlAttribute
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
