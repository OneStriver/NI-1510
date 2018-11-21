package com.sunkaisens.nms.ws.createFile.server.comeInGoOut;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunkaisens.nms.entity.TerminalInfoConfigFileEntity;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.route.Routing;
import com.sunkaisens.nms.ws.objectXmlConvert.route.RptTermInfo;
import com.sunkaisens.nms.ws.objectXmlConvert.route.RstTermInfo;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.Limit;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.Limits;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.Properties;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.Property;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.TermInfo;
import com.sunkaisens.nms.ws.objectXmlConvert.route.configFile.Terminal;

public class TermialInfoConfigUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static List<Limit> limitList = new ArrayList<Limit>();
	private static List<Limits> limitsList = new ArrayList<Limits>();
	private static List<Property> propertyList = new ArrayList<Property>();
	private static List<Properties> propertiesList = new ArrayList<Properties>();
	private static List<Terminal> terminalList = new ArrayList<Terminal>();
	private static List<TermInfo> termInfoList = new ArrayList<TermInfo>();
	private static List<Routing> routingList = new ArrayList<Routing>();
	//终端信息更新通知上级网管
	private static List<RptTermInfo> rptTermInfoList = new ArrayList<RptTermInfo>();
	//上级网管下发的终端信息更新，更新之后上报上级网管
	private static List<RstTermInfo> rstTermInfoList = new ArrayList<RstTermInfo>();
	
	/**
	 * 查询终端信息配置 
	 */
	public static Session generateTerminalInfoConfigXmlStr(List<TerminalInfoConfigFileEntity> list,String systemName){
		//清空list中的数据
		terminalList.clear();
		termInfoList.clear();
		routingList.clear();
		
		for (TerminalInfoConfigFileEntity terminalInfo : list) {
			limitList.clear();
			limitsList.clear();
			propertyList.clear();
			propertiesList.clear();
			
			Limit limit = new Limit();
			limit.setName("PermitCallOut");
			limit.setValue(terminalInfo.getPermitCallOut());
			Limits limits = new Limits();
			limitList.add(limit);
			limits.setLimits(limitList);
			limitsList.add(limits);
			
			Property propertyImsi = new Property();
			propertyImsi.setName("IMSI");
			propertyImsi.setValue(terminalInfo.getImsi());
			Property propertyPriority = new Property();
			propertyPriority.setName("Priority");
			propertyPriority.setValue(terminalInfo.getPriority());
			propertyList.add(propertyImsi);
			propertyList.add(propertyPriority);
			Properties properties = new Properties();
			properties.setPropertys(propertyList);
			propertiesList.add(properties);
			
			Terminal terminal = new Terminal(); 
			terminal.setDeviceType(terminalInfo.getDeviceType());
			terminal.setDeviceNumber(terminalInfo.getDeviceNumber());
			terminal.setType(terminalInfo.getType());
			terminal.setProperties(propertiesList);
			terminal.setLimits(limitsList);
			terminalList.add(terminal);
		}
		
		TermInfo termInfo = new TermInfo();
		termInfo.setType("full");
		termInfo.setVersion(sdf.format(new Date()));
		termInfo.setTerminals(terminalList);
		termInfoList.add(termInfo);
		
		Routing routing = new Routing();
		routing.setTermInfos(termInfoList);
		routing.setSystemName(systemName);
		routingList.add(routing);
		
		Cmd cmd = new Cmd();
		cmd.setRoutings(routingList);
		
		Session session = new Session();
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		
		return session;
	}
	
	/**
	 * 终端信息更新通知 
	 */
	public static Session terminalInfoNotify(String fileName,String systemName){
		//首先是清空数据
		rptTermInfoList.clear();
		routingList.clear();
		
		RptTermInfo rptTermInfo = new RptTermInfo();
		rptTermInfo.setFilename(fileName);
		rptTermInfoList.add(rptTermInfo);
		
		Routing routing = new Routing();
		routing.setSystemName(systemName);
		routing.setRptTermInfos(rptTermInfoList);
		routingList.add(routing);
		
		Cmd cmd = new Cmd();
		cmd.setRoutings(routingList);
		
		Session session = new Session();
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		
		return session;
	}
	
	/**
	 * 终端信息更新结果通知
	 */
	public static Session terminalInfoUpdateResultNotify(String fileName,String systemName,String success,String reason){
		//首先是清空数据
		rstTermInfoList.clear();
		routingList.clear();
		
		RstTermInfo rstTermInfo = new RstTermInfo();
		rstTermInfo.setFilename(fileName);
		rstTermInfo.setSuccess(success);
		if(success.equals("false")){
			rstTermInfo.setReason(reason);
		}
		rstTermInfoList.add(rstTermInfo);
		
		Routing routing = new Routing();
		routing.setSystemName(systemName);
		routing.setRstTermInfos(rstTermInfoList);
		routingList.add(routing);
		
		Cmd cmd = new Cmd();
		cmd.setRoutings(routingList);
		
		Session session = new Session();
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		
		return session;
	}
	
}
