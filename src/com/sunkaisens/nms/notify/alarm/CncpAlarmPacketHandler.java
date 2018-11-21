package com.sunkaisens.nms.notify.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.ByteUtil;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.ws.createFile.client.alarm.AlarmManageWebService;
import com.sunkaisens.nms.ws.dbUtils.AlarmCorrespond;
import com.sunkaisens.nms.ws.dbUtils.DataBaseUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.alarm.AlarmManage;
import com.sunkaisens.nms.ws.objectXmlConvert.alarm.RptAlarm;

/**
 * 故障告警上报 
 */
public class CncpAlarmPacketHandler {
	
	private static Logger logger = Logger.getLogger(CncpAlarmPacketHandler.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static List<AlarmManage> alarmList = new ArrayList<AlarmManage>();;
	private static List<RptAlarm> rptList = new ArrayList<RptAlarm>();
	private static Map<Integer,String> alarmDeviceTypeMap = new HashMap<Integer,String>();
	
	static{
		alarmDeviceTypeMap.put(48, "通信基站");
		alarmDeviceTypeMap.put(49, "交换机");
		alarmDeviceTypeMap.put(50, "数传服务器");
		alarmDeviceTypeMap.put(51, "综合服务器");
	}
	
	public void processCncpMsg(Object msg) {
		//清空list中的数据
		rptList.clear();
		Packet packet = (Packet) msg;
		byte[] bytes = packet.getMsg();
		int messageType = CodecUtil.getUnsignedShort(bytes, 4);
		if(messageType != CncpProtoType.CNCP_ALARM_MSG){
			return;
		}
		logger.info("Dispatch On AlarmThread:[ 告警信息上报 ]");
		// 设备类型
		long neType = ByteUtil.bytesToUnsignedInt(bytes, 8);
		System.err.println("告警信息上报的[neType]:"+neType);
		// 设备索引号
		long instId = ByteUtil.bytesToUnsignedInt(bytes, 12);
		System.err.println("告警信息上报的[instId]:"+instId);
		// 告警码（alarm_code）
		long m_alarmId = ByteUtil.bytesToUnsignedInt(bytes, 16);
		System.err.println("告警信息上报的[m_alarmId]:"+m_alarmId);
		//
		RptAlarm rptAlarm = new RptAlarm();
		rptAlarm.setType(alarmDeviceTypeMap.get((int)neType));
		rptAlarm.setIndex(String.valueOf(instId));
		//根据告警码获取该告警对应的所有信息
		AlarmCorrespond alarmCorrespond = DataBaseUtil.selectByAlarmCode((int)m_alarmId);
		rptAlarm.setAlarmType(alarmCorrespond.getAlarm_type());
		rptAlarm.setReason(alarmCorrespond.getSpecific_problem());
		rptAlarm.setDescription(alarmCorrespond.getAlarm_des());
		rptAlarm.setTime(sdf.format(new Date()));
		rptList.add(rptAlarm);
		Session session = generateAlarmPacketToXmlStr(rptList);
		String xmlStr = XMLUtil.convertToXml(session);
		
		getAlarmConnectAndSend(xmlStr);
	}
	
	public static boolean getAlarmConnectAndSend(String xmlStr){
		// 链路连接
		boolean result = AlarmServiceConnect.AlarmInfoConnect();
		AlarmManageWebService alarmManageWebService = AlarmServiceConnect.delegateService;
		// 调用北向接口的通知方法
		if(result==true){
			alarmManageWebService.rptAlarm(xmlStr);
			logger.info(">>>>>>>>告警信息上报成功>>>>>>>>");
		}else{
			logger.info(">>>>>>>>告警信息上报失败！！！>>>>>>>>");
		}
		return result;
	}
	
	private Session generateAlarmPacketToXmlStr(List<RptAlarm> rptList){
		alarmList.clear();
		//
		AlarmManage alarmManage = new AlarmManage();
		alarmManage.setRptAlarms(rptList);
		alarmManage.setSystemName("wuxianzhihui");
		alarmList.add(alarmManage);
		//
		Cmd cmd = new Cmd();
		cmd.setAlarmManages(alarmList);
		//
		Session session = new Session();
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		
		return session;
	}
}
