package com.sunkaisens.nms.ws.dbUtils.dbResponsePerfInfoEntity;

public class PackageDbResponsePerfInfo {

	private int btsId;
	private int countUseChannelNumber;
	private int countNoUseChannelNumber;

	public PackageDbResponsePerfInfo() {
		super();
	}

	public PackageDbResponsePerfInfo(int btsId, int countUseChannelNumber, int countNoUseChannelNumber) {
		super();
		this.btsId = btsId;
		this.countUseChannelNumber = countUseChannelNumber;
		this.countNoUseChannelNumber = countNoUseChannelNumber;
	}

	public int getBtsId() {
		return btsId;
	}

	public void setBtsId(int btsId) {
		this.btsId = btsId;
	}

	public int getCountUseChannelNumber() {
		return countUseChannelNumber;
	}

	public void setCountUseChannelNumber(int countUseChannelNumber) {
		this.countUseChannelNumber = countUseChannelNumber;
	}

	public int getCountNoUseChannelNumber() {
		return countNoUseChannelNumber;
	}

	public void setCountNoUseChannelNumber(int countNoUseChannelNumber) {
		this.countNoUseChannelNumber = countNoUseChannelNumber;
	}

}
