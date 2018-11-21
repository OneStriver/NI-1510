package com.sunkaisens.nms.ws.objectXmlConvert.log;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rptLog")
public class RptLog {

	private String operUser;
	private String type;
	private String content;
	private String time;

	public String getOperUser() {
		return operUser;
	}

	@XmlAttribute
	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	@XmlAttribute
	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	@XmlAttribute
	public void setTime(String time) {
		this.time = time;
	}

}
