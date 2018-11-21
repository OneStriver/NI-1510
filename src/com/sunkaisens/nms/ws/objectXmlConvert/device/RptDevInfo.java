package com.sunkaisens.nms.ws.objectXmlConvert.device;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rptDevInfo")
public class RptDevInfo {

	private List<Device> devices;

	public List<Device> getDevices() {
		return devices;
	}

	@XmlElement(name="device")
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

}
