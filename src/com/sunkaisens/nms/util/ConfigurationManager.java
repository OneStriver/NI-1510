package com.sunkaisens.nms.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurationManager {
	private static Logger log = Logger.getLogger(ConfigurationManager.class);
	private static String location = "confs/nmsws.properties";
	private static ConfigurationManager configurationManager;
	private static Properties properties;
	private String localWsdl;
	private String remoteHeartBeatWsdl;
	private String remoteAlarmManageWsdl;
	private String remoteDevManageWsdl;
	private String remoteLogManageWsdl;
	private String remotePerfManageWsdl;
	private String remoteRoutingWsdl;
	private String omcIp;
	private String omcPort;
	private String northIp;
	private String northPort;
	private String reserveNumberBit;
	private int deleteLogTime;

	private ConfigurationManager() {
		getConfiguration(location);
	}

	public static synchronized ConfigurationManager getInstance() {
		if (configurationManager == null) {
			configurationManager = new ConfigurationManager();
		}
		return configurationManager;
	}

	private String getProperty(String propertyName) {
		String value = getProperties(location).getProperty(propertyName);
		if (value == null) {
			log.info(propertyName + " was not found in " + location);
			return null;
		}
		return value.trim();
	}

	private static Properties getProperties(String location) {
		if (properties == null) {
			properties = new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(location);
				properties.load(fis);
				fis.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				log.error(StackUtil.getTrace(e));
			}
		}
		return properties;
	}

	public void getConfiguration(String is) {
		localWsdl = getProperty("localWsdl");
		remoteHeartBeatWsdl = getProperty("remoteHeartBeatWsdl");
		remoteAlarmManageWsdl = getProperty("remoteAlarmManageWsdl");
		remoteDevManageWsdl = getProperty("remoteDevManageWsdl");
		remoteLogManageWsdl = getProperty("remoteLogManageWsdl");
		remotePerfManageWsdl = getProperty("remotePerfManageWsdl");
		remoteRoutingWsdl = getProperty("remoteRoutingWsdl");
		omcIp = getProperty("omc_ip");
		omcPort = getProperty("omc_port");
		northIp = getProperty("north_ip");
		northPort = getProperty("north_port");
		reserveNumberBit = getProperty("reserveNumberBit");
		deleteLogTime = Integer.valueOf(getProperty("deleteLogTime"));
	}

	public String getLocalWsdl() {
		return localWsdl;
	}

	public void setLocalWsdl(String localWsdl) {
		this.localWsdl = localWsdl;
	}

	public String getRemoteHeartBeatWsdl() {
		return remoteHeartBeatWsdl;
	}

	public void setRemoteHeartBeatWsdl(String remoteHeartBeatWsdl) {
		this.remoteHeartBeatWsdl = remoteHeartBeatWsdl;
	}

	public String getRemoteAlarmManageWsdl() {
		return remoteAlarmManageWsdl;
	}

	public void setRemoteAlarmManageWsdl(String remoteAlarmManageWsdl) {
		this.remoteAlarmManageWsdl = remoteAlarmManageWsdl;
	}

	public String getRemoteDevManageWsdl() {
		return remoteDevManageWsdl;
	}

	public void setRemoteDevManageWsdl(String remoteDevManageWsdl) {
		this.remoteDevManageWsdl = remoteDevManageWsdl;
	}

	public String getRemoteLogManageWsdl() {
		return remoteLogManageWsdl;
	}

	public void setRemoteLogManageWsdl(String remoteLogManageWsdl) {
		this.remoteLogManageWsdl = remoteLogManageWsdl;
	}

	public String getRemotePerfManageWsdl() {
		return remotePerfManageWsdl;
	}

	public void setRemotePerfManageWsdl(String remotePerfManageWsdl) {
		this.remotePerfManageWsdl = remotePerfManageWsdl;
	}

	public String getRemoteRoutingWsdl() {
		return remoteRoutingWsdl;
	}

	public void setRemoteRoutingWsdl(String remoteRoutingWsdl) {
		this.remoteRoutingWsdl = remoteRoutingWsdl;
	}

	public String getOmcIp() {
		return omcIp;
	}

	public void setOmcIp(String omcIp) {
		this.omcIp = omcIp;
	}

	public String getOmcPort() {
		return omcPort;
	}

	public void setOmcPort(String omcPort) {
		this.omcPort = omcPort;
	}

	public String getNorthIp() {
		return northIp;
	}

	public void setNorthIp(String northIp) {
		this.northIp = northIp;
	}

	public String getNorthPort() {
		return northPort;
	}

	public void setNorthPort(String northPort) {
		this.northPort = northPort;
	}

	public String getReserveNumberBit() {
		return reserveNumberBit;
	}

	public void setReserveNumberBit(String reserveNumberBit) {
		this.reserveNumberBit = reserveNumberBit;
	}

	public int getDeleteLogTime() {
		return deleteLogTime;
	}

	public void setDeleteLogTime(int deleteLogTime) {
		this.deleteLogTime = deleteLogTime;
	}

}
