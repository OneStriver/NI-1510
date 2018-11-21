package com.sunkaisens.nms.ws.createFile.server.performanceInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunkaisens.nms.ws.dbUtils.dbResponsePerfInfoEntity.PackageDbResponsePerfInfo;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.Parameter;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.PerfManage;
import com.sunkaisens.nms.ws.objectXmlConvert.perfManage.RptPerf;

public class PerformanceInfoConfigUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static List<Parameter> parameterList = new ArrayList<Parameter>();
	private static List<PerfManage> perfManageList = new ArrayList<PerfManage>();
	
	/**
	 * 查询性能数据信息
	 */
	public static Session generatePerformanceInfoXmlStr(List<PackageDbResponsePerfInfo> list,String systemName){
		//清空list中的数据
		parameterList.clear();
		perfManageList.clear();
		
		for (PackageDbResponsePerfInfo terminalInfo : list) {
			Parameter parameterOccupancy  = new Parameter();
			parameterOccupancy.setType("通信基站");
			parameterOccupancy.setIndex("");
			parameterOccupancy.setName("");
			parameterOccupancy.setEname("信道占用数量");
			parameterOccupancy.setValue(String.valueOf(terminalInfo.getCountUseChannelNumber()));
			Parameter parameterIdle  = new Parameter();
			parameterIdle.setType("通信基站");
			parameterIdle.setIndex("");
			parameterIdle.setName("");
			parameterIdle.setEname("信道空闲数量");
			parameterIdle.setValue(String.valueOf(terminalInfo.getCountNoUseChannelNumber()));
			parameterList.add(parameterOccupancy);
			parameterList.add(parameterIdle);
		}
		
		RptPerf rptPerf = new RptPerf();
		rptPerf.setTime(sdf.format(new Date()));
		rptPerf.setParameters(parameterList);
		
		PerfManage perfManage = new PerfManage();
		perfManage.setSystemName(systemName);
		perfManage.setRptPerf(rptPerf);
		perfManageList.add(perfManage);
		
		Cmd cmd = new Cmd();
		cmd.setPerfManages(perfManageList);;
		
		Session session = new Session();
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		
		return session;
	}
	
}
