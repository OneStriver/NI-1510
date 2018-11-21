package com.sunkaisens.nms.ws.objectXmlConvert.alarm;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alarmManage")
public class AlarmManage {

	private String systemName;
	private List<RptAlarm> rptAlarms;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<RptAlarm> getRptAlarms() {
		return rptAlarms;
	}

	@XmlElement(name="rptAlarm")
	public void setRptAlarms(List<RptAlarm> rptAlarms) {
		this.rptAlarms = rptAlarms;
	}

}
