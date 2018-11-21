package com.sunkaisens.nms.ws.objectXmlConvert.route;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.TermInfo;


@XmlRootElement(name = "Routing")
public class Routing {

	private String systemName;
	// 查询终端信息配置
	private QryTermInfo qryTermInfo;
	// 终端信息配置文件
	private List<TermInfo> termInfos;
	// 终端信息更新通知
	private List<RptTermInfo> rptTermInfos;
	// 终端信息更新结果
	private List<RstTermInfo> rstTermInfos;

	public String getSystemName() {
		return systemName;
	}

	@XmlAttribute
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public QryTermInfo getQryTermInfo() {
		return qryTermInfo;
	}

	@XmlElement(name = "qryTermInfo")
	public void setQryTermInfo(QryTermInfo qryTermInfo) {
		this.qryTermInfo = qryTermInfo;
	}

	public List<TermInfo> getTermInfos() {
		return termInfos;
	}

	@XmlElement(name = "termInfo")
	public void setTermInfos(List<TermInfo> termInfos) {
		this.termInfos = termInfos;
	}

	public List<RptTermInfo> getRptTermInfos() {
		return rptTermInfos;
	}

	@XmlElement(name = "rptTermInfo")
	public void setRptTermInfos(List<RptTermInfo> rptTermInfos) {
		this.rptTermInfos = rptTermInfos;
	}

	public List<RstTermInfo> getRstTermInfos() {
		return rstTermInfos;
	}

	@XmlElement(name = "rstTermInfo")
	public void setRstTermInfos(List<RstTermInfo> rstTermInfos) {
		this.rstTermInfos = rstTermInfos;
	}

}
