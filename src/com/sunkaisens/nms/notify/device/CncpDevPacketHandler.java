package com.sunkaisens.nms.notify.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sunkaisens.nms.comm.ByteUtil;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;
import com.sunkaisens.nms.ws.createFile.client.equip.DevManageWebService;
import com.sunkaisens.nms.ws.dbUtils.DataBaseUtil;
import com.sunkaisens.nms.ws.dbUtils.dbResponseDeviceStatusEntity.DeviceStatusQuery;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.device.DevManage;
import com.sunkaisens.nms.ws.objectXmlConvert.device.Device;
import com.sunkaisens.nms.ws.objectXmlConvert.device.RptDevInfo;

/**
 * 设备管理服务 
 */
public class CncpDevPacketHandler {
	
	private static Logger logger = Logger.getLogger(CncpDevPacketHandler.class);
	private static Session session = new Session();
	private static Cmd cmd = new Cmd();
	private static DevManage devManage = new DevManage();
	private static RptDevInfo rptDevInfo = new RptDevInfo();
	private static List<Device> devicelist = new ArrayList<Device>();
	private static List<DevManage> devManageList = new ArrayList<DevManage>();
	private static Map<Integer,String> deviceTypeMap = new HashMap<Integer,String>();
	
	static{
		deviceTypeMap.put(48, "通信基站");
		deviceTypeMap.put(49, "交换机");
		deviceTypeMap.put(50, "数传服务器");
		deviceTypeMap.put(51, "综合服务器");
	}
	
	public void processCncpMsg(Object msg) {
		devicelist.clear();
		Packet packet = (Packet) msg;
		byte[] bytes = packet.getMsg();
		int messageType = CodecUtil.getUnsignedShort(bytes, 4);
		if(messageType != CncpProtoType.CNCP_STATUS_MSG){
			return;
		}
		logger.info("Dispatch On DevThread:[ 设备状态上报 ]");
		//设备类型
		long neId = ByteUtil.bytesToUnsignedInt(bytes, 8);
		System.err.println("设备状态上报的[neId]:"+neId);
		//设备索引号
		long instId = ByteUtil.bytesToUnsignedInt(bytes, 12);
		System.err.println("设备状态上报的[instId]:"+instId);
		//设备状态
		short status = ByteUtil.byteToUnsignedChar(bytes[17]);
		System.err.println("设备状态上报的[status]:"+status);
		if(neId==0 || instId==0){
			System.err.println("设备类型或设备索引号不能没有!!!");
			return;
		}
		Device device = new Device();
		device.setType(deviceTypeMap.get((int)neId));
		device.setIndex(String.valueOf(instId));
		if(status==1){
			device.setStatus("正常");
		}else{
			device.setStatus("故障");
		}
		devicelist.add(device);
		//根据设备类型获取该设备的所有信息
		DeviceStatusQuery getDeviceStatus = DataBaseUtil.selectDeviceStatusInfoByDeviceType(device.getType());
		if(getDeviceStatus==null){
			//数据库中没有该设备的信息   写入数据库中
			DataBaseUtil.insertDeviceStatusInfo(device.getType(), device.getIndex(), device.getStatus());
		}else{
			//数据库中有该设备的信息  更新数据库的信息
			DataBaseUtil.updateDeviceStatusInfo(device.getType(), device.getIndex(), device.getStatus());
		}
		//
		String xmlStr = XMLUtil.convertToXml(generateDeviceStatusXmlStr(devicelist,"wuxianzhihui"));
		getDeviceStatusAndSend(xmlStr);
	}
	
	public static boolean getDeviceStatusAndSend(String xmlStr){
		// 链路连接
		boolean result = DevServiceConnect.DevStatusConnect();
		DevManageWebService devManageWebService = DevServiceConnect.devManageWebService;
		// 调用北向接口的通知方法
		if(result==true){
			devManageWebService.rptDevInfo(xmlStr);
			logger.info(">>>>>>>>设备状态信息上报成功>>>>>>>>");
		}else{
			logger.info(">>>>>>>>设备状态信息上报失败！！！>>>>>>>>");
		}
		return result; 
	}
	
	public static Session generateDeviceStatusXmlStr(List<Device> list,String xmlStr){
		devManageList.clear();
		//
		rptDevInfo.setDevices(list);
		//
		devManage.setSystemName(xmlStr);
		devManage.setRptDevInfo(rptDevInfo);
		devManageList.add(devManage);
		//
		cmd.setDevManages(devManageList);
		//
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		//
		return session;
	}
	
}
