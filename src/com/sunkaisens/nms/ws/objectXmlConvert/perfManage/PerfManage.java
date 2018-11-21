package com.sunkaisens.nms.ws.objectXmlConvert.perfManage;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "perfManage")
public class PerfManage {

	private String systemName;
	// 性能查询
	private QryPerf qryPerf;
	// 性能信息上报
	private RptPerf rptPerf;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public QryPerf getQryPerf() {
		return qryPerf;
	}

	@XmlElement(name = "qryPerf")
	public void setQryPerf(QryPerf qryPerf) {
		this.qryPerf = qryPerf;
	}

	public RptPerf getRptPerf() {
		return rptPerf;
	}

	@XmlElement(name = "rptPerf")
	public void setRptPerf(RptPerf rptPerf) {
		this.rptPerf = rptPerf;
	}

}
