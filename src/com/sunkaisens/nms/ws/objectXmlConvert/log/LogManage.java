package com.sunkaisens.nms.ws.objectXmlConvert.log;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "logManage")
public class LogManage {

	private String systemName;
	// 日志上报
	private List<RptLog> rptLogs;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<RptLog> getRptLogs() {
		return rptLogs;
	}

	@XmlElement(name="rptLog")
	public void setRptLogs(List<RptLog> rptLogs) {
		this.rptLogs = rptLogs;
	}

}
