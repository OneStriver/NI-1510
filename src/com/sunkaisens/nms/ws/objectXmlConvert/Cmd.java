package com.sunkaisens.nms.ws.objectXmlConvert;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunkaisens.nms.ws.objectXmlConvert.alarm.AlarmManage;
import com.sunkaisens.nms.ws.objectXmlConvert.device.DevManage;
import com.sunkaisens.nms.ws.objectXmlConvert.heartBeat.HeartBeat;
import com.sunkaisens.nms.ws.objectXmlConvert.log.LogManage;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.PerfManage;
import com.sunkaisens.nms.ws.objectXmlConvert.route.Routing;


@XmlRootElement(name = "cmd")
public class Cmd {

	// 设备心跳上报
	private List<HeartBeat> heartBeats;
	// 性能查询 性能信息上报
	private List<PerfManage> perfManages;
	// 设备状态查询 设备状态上报
	private List<DevManage> devManages;
	// 故障上报
	private List<AlarmManage> alarmManages;
	// 日志上报
	private List<LogManage> logManages;
	// 查询终端信息配置 终端信息更新通知  终端信息更新结果
	private List<Routing> routings;

	public List<HeartBeat> getHeartBeats() {
		return heartBeats;
	}

	@XmlElement(name = "heartBeat")
	public void setHeartBeats(List<HeartBeat> heartBeats) {
		this.heartBeats = heartBeats;
	}

	public List<PerfManage> getPerfManages() {
		return perfManages;
	}

	@XmlElement(name = "perfManage")
	public void setPerfManages(List<PerfManage> perfManages) {
		this.perfManages = perfManages;
	}

	public List<DevManage> getDevManages() {
		return devManages;
	}

	@XmlElement(name = "devManage")
	public void setDevManages(List<DevManage> devManages) {
		this.devManages = devManages;
	}

	public List<AlarmManage> getAlarmManages() {
		return alarmManages;
	}

	@XmlElement(name = "alarmManage")
	public void setAlarmManages(List<AlarmManage> alarmManages) {
		this.alarmManages = alarmManages;
	}

	public List<LogManage> getLogManages() {
		return logManages;
	}

	@XmlElement(name = "logManage")
	public void setLogManages(List<LogManage> logManages) {
		this.logManages = logManages;
	}

	public List<Routing> getRoutings() {
		return routings;
	}

	@XmlElement(name = "Routing")
	public void setRoutings(List<Routing> routings) {
		this.routings = routings;
	}

}
