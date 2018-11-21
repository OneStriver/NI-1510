package com.sunkaisens.nms.ws.objectXmlConvert.device;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "devManage")
public class DevManage {

	private String systemName;
	// 设备查询
	private QryDevInfo qryDevInfo;
	// 设备状态上报
	private RptDevInfo rptDevInfo;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public QryDevInfo getQryDevInfo() {
		return qryDevInfo;
	}

	@XmlElement(name = "qryDevInfo")
	public void setQryDevInfo(QryDevInfo qryDevInfo) {
		this.qryDevInfo = qryDevInfo;
	}

	public RptDevInfo getRptDevInfo() {
		return rptDevInfo;
	}

	@XmlElement(name="rptDevInfo")
	public void setRptDevInfo(RptDevInfo rptDevInfo) {
		this.rptDevInfo = rptDevInfo;
	}

}
