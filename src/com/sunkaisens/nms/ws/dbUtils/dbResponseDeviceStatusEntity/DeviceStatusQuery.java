package com.sunkaisens.nms.ws.dbUtils.dbResponseDeviceStatusEntity;

public class DeviceStatusQuery {

	private String deviceType;
	private String deviceIndex;
	private String deviceStatus;

	public DeviceStatusQuery() {
		super();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceIndex() {
		return deviceIndex;
	}

	public void setDeviceIndex(String deviceIndex) {
		this.deviceIndex = deviceIndex;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

}
