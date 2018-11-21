package com.sunkaisens.nms.ws.createFile.server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.sunkaisens.nms.entity.TerminalInfoConfigFileEntity;
import com.sunkaisens.nms.ftp.FtpUtils;
import com.sunkaisens.nms.notify.device.CncpDevPacketHandler;
import com.sunkaisens.nms.notify.perf.CncpPerfHandler;
import com.sunkaisens.nms.notify.route.CncpRouteHandler;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpProtoType;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.CncpTaskExecutor;
import com.sunkaisens.nms.udpMessageThread.cncpMsg.SendCncpMsgFactory;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcMsg;
import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.server.comeInGoOut.TermialInfoConfigUtils;
import com.sunkaisens.nms.ws.createFile.server.performanceInfo.PerformanceInfoConfigUtils;
import com.sunkaisens.nms.ws.dbUtils.DataBaseUtil;
import com.sunkaisens.nms.ws.dbUtils.comeInGoOutConfig.QueryTerminalInfoConfigUtils;
import com.sunkaisens.nms.ws.dbUtils.dbResponseDeviceStatusEntity.DeviceStatusQuery;
import com.sunkaisens.nms.ws.dbUtils.dbResponsePerfInfoEntity.DbQueryPerformanceInfo;
import com.sunkaisens.nms.ws.dbUtils.dbResponsePerfInfoEntity.PackageDbResponsePerfInfo;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.device.Device;

@WebService(endpointInterface="com.sunkaisens.nms.ws.createFile.server.NmsNorthInterface",serviceName="NmsNorthInterface")
public class NmsNorthInterfaceImpl implements NmsNorthInterface {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private SendCncpMsgFactory sendCncpMsgFactory = SendCncpMsgFactory.getInstance();
	private CncpTaskExecutor cncpTaskExecutor = new CncpTaskExecutor();
	private List<DeviceStatusQuery> allDeviceStatusList = new ArrayList<DeviceStatusQuery>();
	private List<Device> list = new ArrayList<Device>();
	private static List<PackageDbResponsePerfInfo> allPerfInfoList = new ArrayList<PackageDbResponsePerfInfo>();
	public static Map<String,String> templeMap = new HashMap<String,String>();
	
	/**
	 * 性能查询
	 */
	@Override
	public boolean perfQueryMethod(String xml) {
		//清空数据
		allPerfInfoList.clear();
		//获取携带的系统名称
		Object object = XMLUtil.convertXmlStrToObject(Session.class, xml);
		Session sessionTempl = (Session)object;
		String systemName = sessionTempl.getCmd().getPerfManages().get(0).getSystemName();
		//查询信道已占用的数量
		List<DbQueryPerformanceInfo> queryUsePerformanceInfoList = DataBaseUtil.selectDbPerformanceInfo(1);
		//查询信道未占用的数量
		List<DbQueryPerformanceInfo> queryNoUsePerformanceInfoList = DataBaseUtil.selectDbPerformanceInfo(0);
		//获取迭代器
		Iterator<DbQueryPerformanceInfo> useIterators = queryUsePerformanceInfoList.iterator();
		Iterator<DbQueryPerformanceInfo> noUseIterators = queryNoUsePerformanceInfoList.iterator();
		//先统计BtsId相同的数据
		while(useIterators.hasNext()){
			DbQueryPerformanceInfo useObject = useIterators.next();
			while(noUseIterators.hasNext()){
				DbQueryPerformanceInfo noUseObject = noUseIterators.next();
				if(useObject.getBtsId()==noUseObject.getBtsId()){
					allPerfInfoList.add(new PackageDbResponsePerfInfo(useObject.getBtsId(), useObject.getCountChannelNumber(), noUseObject.getCountChannelNumber()));
					useIterators.remove();
					noUseIterators.remove();
					break;
				}
			}
		}
		//信道被占用
		Iterator<DbQueryPerformanceInfo> useIteratorsOne = queryUsePerformanceInfoList.iterator();
		while(useIteratorsOne.hasNext()){
			DbQueryPerformanceInfo useObject = useIteratorsOne.next();
			allPerfInfoList.add(new PackageDbResponsePerfInfo(useObject.getBtsId(), useObject.getCountChannelNumber(), 0));
		}
		//信道未被占用
		Iterator<DbQueryPerformanceInfo> noUseIteratorsOne = queryNoUsePerformanceInfoList.iterator();
		while(noUseIteratorsOne.hasNext()){
			DbQueryPerformanceInfo noUseObject = noUseIteratorsOne.next();
			allPerfInfoList.add(new PackageDbResponsePerfInfo(noUseObject.getBtsId(), 0, noUseObject.getCountChannelNumber()));
		}
		//将performance的list集合转换为session
		Session performanceInfoSession = PerformanceInfoConfigUtils.generatePerformanceInfoXmlStr(allPerfInfoList, systemName);
		String xmlStr = XMLUtil.convertToXml(performanceInfoSession);
		System.err.println("性能查询:"+xmlStr);
		boolean result = CncpPerfHandler.getPerfConnectAndSend(xmlStr);
		return result;
	}
	
	/**
	 * 设备状态查询
	 */
	@Override
	public boolean queryDeviceStatusMethod(String xml) {
		//清空数据
		list.clear();
		allDeviceStatusList.clear();
		//获取携带的系统名称
		Object object = XMLUtil.convertXmlStrToObject(Session.class, xml);
		Session sessionTempl = (Session)object;
		String systemName = sessionTempl.getCmd().getDevManages().get(0).getSystemName();
		//查询数据库中所有的设备信息
		allDeviceStatusList = DataBaseUtil.selectAllDeviceStatusInfo();
		for (DeviceStatusQuery deviceStatus : allDeviceStatusList) {
			Device device = new Device();
			device.setType(deviceStatus.getDeviceType());
			device.setIndex(deviceStatus.getDeviceIndex());
			device.setStatus(deviceStatus.getDeviceStatus());
			list.add(device);
		}
		//将所有的设备信息封装成一个对象
		Session session = CncpDevPacketHandler.generateDeviceStatusXmlStr(list,systemName);
		String xmlStr = XMLUtil.convertToXml(session);
		System.err.println("设备状态查询:"+xmlStr);
		boolean result = CncpDevPacketHandler.getDeviceStatusAndSend(xmlStr);
		return result;
	}

	/**
	 * 查询终端信息配置(出入局配置)
	 */
	@Override
	public boolean queryTerminalInfoConfigMethod(String xml) {
		//第一步解析出参数系统名称
		Object object = XMLUtil.convertXmlStrToObject(Session.class, xml);
		Session sessionTempl = (Session)object;
		String systemName = sessionTempl.getCmd().getRoutings().get(0).getSystemName();
		//查询OMC的终端用户写成xml文件上传FTP
		List<TerminalInfoConfigFileEntity> terminalInfos = QueryTerminalInfoConfigUtils.getTerminalInfos();
		Session queryTerminalInfoSession = TermialInfoConfigUtils.generateTerminalInfoConfigXmlStr(terminalInfos, systemName);
		//上传文件名字
		String fileName = "TermInfo_wuxianzhihui_full_"+(sdf.format(new Date()))+".xml";
		File convertFile = XMLUtil.convertToXmlFile(queryTerminalInfoSession,"confs/ftp/"+fileName);
		boolean uploadResult = FtpUtils.upload(convertFile,fileName);
		//删除本地的缓存文件
		convertFile.delete();
		//生成XML字符串
		Session terminalInfoNotifySession = TermialInfoConfigUtils.terminalInfoNotify(fileName, systemName);
		String terminalInfoNotifyStr = XMLUtil.convertToXml(terminalInfoNotifySession);
		//通知上级网管
		boolean notifyResult = CncpRouteHandler.getTerminalInfoAndSend(terminalInfoNotifyStr);
		//两者都成功才表示成功
		if(uploadResult==true && notifyResult==true){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 上级网管调用北向接口更新终端信息   发送消息到OMC
	 */
	@Override
	public boolean terminalInfoUpdateNotifyMethod(String xml) {
		String downloadObjectToXmlStr=null;
		templeMap.clear();
		//解析出上级网管发送的XML信息
		Object object = XMLUtil.convertXmlStrToObject(Session.class,xml);
		Session session = (Session)object;
		String systemName = session.getCmd().getRoutings().get(0).getSystemName();
		String fileName = session.getCmd().getRoutings().get(0).getRptTermInfos().get(0).getFilename();
		//存放上级网管下发的文件名字
		templeMap.put("systemName", systemName);
		templeMap.put("fileName", fileName);
		//从FTP下载对应的文件进行解析
		File downLoadFile = FtpUtils.downLoadFile(fileName);
		//
		if(downLoadFile!=null){
			Session downLoadSessionObject = (Session)XMLUtil.convertDownLoadXmlFileToObject(Session.class, downLoadFile);
			downloadObjectToXmlStr = XMLUtil.convertToXml(downLoadSessionObject);
		}else{
			//FTP没有对应的文件
			Session updateResultSession = TermialInfoConfigUtils.terminalInfoUpdateResultNotify(fileName, systemName, "false", "FTP文件校验失败!");
			String updateNotifyStr = XMLUtil.convertToXml(updateResultSession);
			CncpRouteHandler.updateTerminalInfoResultAndSend(updateNotifyStr);
			return false;
		}
		//解析文件转换成字符串
		if(downloadObjectToXmlStr==null || "".equals(downloadObjectToXmlStr)){
			return false;
		}
		//发送消息给OMC,OMC更新相应的用户
		NIToOmcMsg msg = sendCncpMsgFactory.createNIToOmcCncpMsg(CncpProtoType.NI_HEADER, CncpProtoType.OMC_HEADER, CncpProtoType.CNCP_NI_OMC_MSG, 255, 1, (char)0, downloadObjectToXmlStr);
		cncpTaskExecutor.invokeNIToOmcMsg(msg, true,ConfigurationManager.getInstance().getOmcIp(),Integer.valueOf(ConfigurationManager.getInstance().getOmcPort()));
		System.err.println(">>>>>>>>上级网管调用北向接口更新终端信息完成>>>>>>>>");
		return true;
	}

	/**
	 * 终端信息更新结果
	 */
	@Override
	public boolean terminalInfoUpdateResultMethod(String xml) {
		return false;
	}

}
