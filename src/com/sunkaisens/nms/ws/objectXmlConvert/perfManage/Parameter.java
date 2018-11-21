package com.sunkaisens.nms.ws.objectXmlConvert.perfManage;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parameter")
public class Parameter {

	private String type;
	private String index;
	private String name;
	private String ename;
	private String value;

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

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	@XmlAttribute
	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getValue() {
		return value;
	}

	@XmlAttribute
	public void setValue(String value) {
		this.value = value;
	}

}
