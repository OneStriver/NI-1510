package com.sunkaisens.nms.ws.objectXmlConvert.heartBeat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "heartBeat")
public class HeartBeat {

	private String systemName;
	private RptHeartBeat rptHeartBeats;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public RptHeartBeat getRptHeartBeats() {
		return rptHeartBeats;
	}

	@XmlElement(name="rptHeartBeat")
	public void setRptHeartBeats(RptHeartBeat rptHeartBeats) {
		this.rptHeartBeats = rptHeartBeats;
	}
	
}
