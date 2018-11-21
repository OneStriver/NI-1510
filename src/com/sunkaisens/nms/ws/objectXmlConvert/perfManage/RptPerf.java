package com.sunkaisens.nms.ws.objectXmlConvert.perfManage;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rptPerf")
public class RptPerf {

	private String time;
	private List<Parameter> parameters;

	public String getTime() {
		return time;
	}

	@XmlAttribute
	public void setTime(String time) {
		this.time = time;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	@XmlElement(name="parameter")
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}
