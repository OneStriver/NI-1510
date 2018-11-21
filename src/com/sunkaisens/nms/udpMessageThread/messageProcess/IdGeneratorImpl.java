package com.sunkaisens.nms.udpMessageThread.messageProcess;

import org.apache.commons.lang.RandomStringUtils;

public class IdGeneratorImpl implements IdGenerator {

	public String genId() {
		return RandomStringUtils.randomAlphanumeric(8);
	}
}
