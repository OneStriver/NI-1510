package com.sunkaisens.nms.ws.objectXmlConvert.route;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rstTermInfo")
public class RstTermInfo {

	private String filename;
	private String success;
	private String reason;

	public String getFilename() {
		return filename;
	}

	@XmlAttribute
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSuccess() {
		return success;
	}

	@XmlAttribute
	public void setSuccess(String success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	@XmlAttribute
	public void setReason(String reason) {
		this.reason = reason;
	}

}
