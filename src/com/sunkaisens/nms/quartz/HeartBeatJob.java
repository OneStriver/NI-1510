package com.sunkaisens.nms.quartz;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunkaisens.nms.util.ConfigurationManager;
import com.sunkaisens.nms.ws.createFile.client.heart.HeartBeatService;
import com.sunkaisens.nms.ws.createFile.client.heart.HeartBeatWebService;
import com.sunkaisens.nms.ws.objectXmlConvert.Cmd;
import com.sunkaisens.nms.ws.objectXmlConvert.Session;
import com.sunkaisens.nms.ws.objectXmlConvert.XMLUtil;
import com.sunkaisens.nms.ws.objectXmlConvert.heartBeat.HeartBeat;
import com.sunkaisens.nms.ws.objectXmlConvert.heartBeat.RptHeartBeat;

public class HeartBeatJob implements Job {
	
	private static Logger logger = Logger.getLogger(HeartBeatJob.class);
	private HeartBeatWebService heartBeatWebService;
	private List<HeartBeat> list = new ArrayList<HeartBeat>();
	private HeartBeat heartBeat = new HeartBeat();
	private RptHeartBeat rptHeartBeat = new RptHeartBeat();
	private Cmd cmd = new Cmd();
	private Session session = new Session();
	private String wsdl = ConfigurationManager.getInstance().getRemoteHeartBeatWsdl();
	private URL url;
	private HeartBeatService heartBeatService;
	private boolean reachable = false;
	private InputStream inputStream = null;
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//判断网络是否能Ping通
		String ipAddr = wsdl.split("//")[1].split("/")[0].split(":")[0];
		System.err.println(">>>>>>>>>>IP地址>>>>>>>>>>>>:"+ipAddr);
		try {
			InetAddress address = InetAddress.getByName(ipAddr);
			reachable = address.isReachable(3000);
			if(!reachable){
				logger.info(">>>>>>>>网络链路出现异常！！！>>>>>>>>");
				return;
			}
			logger.info(">>>>>>>>网络链路正常>>>>>>>>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//判断WebService服务是否存在
		try {
			url = new URL(wsdl);
			URLConnection openConnection = url.openConnection();
			openConnection.setConnectTimeout(3000);
			openConnection.setReadTimeout(3000);
			openConnection.connect();
			inputStream = openConnection.getInputStream();
			logger.info(">>>>>>>>WebService服务连接正常>>>>>>>>");
		} catch (IOException e) {
			logger.info(">>>>>>>>WebService服务连接异常！！！>>>>>>>>");
			return;
		}finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//判断WebService服务心跳上报
		try {
			url = new URL(HeartBeatService.class.getResource("."), wsdl);
			logger.info(">>>>>>>>上级网管设备心跳上报接口正常>>>>>>>>");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.info(">>>>>>>>上级网管设备心跳上报接口异常！！！>>>>>>>>");
			return;
		}
		heartBeatService = new HeartBeatService(url,new QName("http://HeartBeat.webservice.com", "HeartBeat_Service"));
		heartBeatWebService = heartBeatService.getHeartBeatEndPoint();
		//调用北向接口的通知方法
		String convertToXml = XMLUtil.convertToXml(generateHeartBeatXmlStr());
		logger.info(">>>>>>>>调用接口上报信息>>>>>>>>");
		heartBeatWebService.rptHeartBeat(convertToXml);
		
	}
	
	//生成设备心跳上报的对象
	private Session generateHeartBeatXmlStr(){
		heartBeat.setSystemName("wuxianzhihui");
		heartBeat.setRptHeartBeats(rptHeartBeat);
		list.add(heartBeat);
		cmd.setHeartBeats(list);
		session.setsId("12345");
		session.setUsername("admin");
		session.setPassword("admin");
		session.setCmd(cmd);
		return session;
	}
	
}
