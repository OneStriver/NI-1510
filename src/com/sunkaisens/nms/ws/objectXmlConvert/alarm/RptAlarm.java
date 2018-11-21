package com.sunkaisens.nms.ws.objectXmlConvert.alarm;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rptAlarm")
public class RptAlarm {

	private String type;
	private String index;
	private String name;
	private String location;
	private String alarmType;
	private String reason;
	private String description;
	private String time;

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

	public String getLocation() {
		return location;
	}

	@XmlAttribute
	public void setLocation(String location) {
		this.location = location;
	}

	public String getAlarmType() {
		return alarmType;
	}

	@XmlAttribute
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getReason() {
		return reason;
	}

	@XmlAttribute
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	@XmlAttribute
	public void setTime(String time) {
		this.time = time;
	}

}
