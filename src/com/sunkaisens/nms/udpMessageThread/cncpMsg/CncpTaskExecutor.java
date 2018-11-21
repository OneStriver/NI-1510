package com.sunkaisens.nms.udpMessageThread.cncpMsg;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.CncpBaseResponseMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcMsg;
import com.sunkaisens.nms.udpMessageThread.messageEncapsulation.NIToOmcResponseMsg;
import com.sunkaisens.nms.udpMessageThread.timeOutHandle.NIToOmcTimerOutJob;
import com.sunkaisens.nms.udpMessageThread.udpMsgEntity.Packet;
import com.sunkaisens.nms.util.CodecUtil;

/**
 * CNCP消息收发器, 提供工程内部所有CNCP消息的收发 注意: TransactionID 必须由此接口获得方能保证运行期间事务ID的一致性,
 * 任意服务层不可以随意定义事务ID
 */
public class CncpTaskExecutor {
	
	private static String JOB_GROUP_NAME = "TIMEOUT_JOBGROUP";  
	private static String TRIGGER_GROUP_NAME = "TIMEOUT_TRIGGERGROUP";
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();  
    //日志记录
	private static Logger logger = Logger.getLogger(CncpTaskExecutor.class);
	//消息队列的处理
	private UDPNetComm udpNetComm = UDPNetComm.getInstance();
	//超时时长
	private int timeOut = 5;
	//获取线程池
	private static final ExecutorService pool = Executors.newCachedThreadPool();
    //消息收发器构造器
	public CncpTaskExecutor() {
		
	}
	
	/**
	 * 北向接口发送给OMC的消息
	 */
	public NIToOmcResponseMsg invokeNIToOmcMsg(NIToOmcMsg msg, boolean isBlock,String ip,Integer port) {
		if (msg == null){
			throw new IllegalArgumentException("消息不能为空！");
		}
		NIToOmcTask niToOmcTask = new NIToOmcTask(msg, isBlock,ip,port);
		NIToOmcResponseMsg result = null;
		try {
			int transId = niToOmcTask.msg.getTransId();
			if (isBlock){
				nIToOmcTimeOutJob(timeOut,transId,niToOmcTask);
			}
			System.err.println("北向接口发送给OMC消息的TransId:"+transId);
			Future<NIToOmcResponseMsg> poolResult = pool.submit(niToOmcTask);
			result = poolResult.get();
			if (result!=null && result.getResult() != CncpProtoType.TIMEOUT) {
				System.err.println("北向接口发送给OMC的消息>>>超时处理");
				removeTimeOutJob(""+transId, JOB_GROUP_NAME, TRIGGER_GROUP_NAME);
			}
		} catch (Exception e) {
			logger.error(">> task interrupted ", e);
		}
		return result;
	}
	//北向接口发送给OMC的消息
	class NIToOmcTask implements Callable<NIToOmcResponseMsg> {
		final NIToOmcMsg msg;
		final boolean isBlock;
		final String ip;
		final Integer port;
		NIToOmcTask(NIToOmcMsg msg,boolean isBlock,String ip,Integer port) {
			this.msg = msg;
			this.isBlock=isBlock;
			this.ip = ip;
			this.port = port;
		}
		@Override
		public NIToOmcResponseMsg call() {
			try {
				Packet pack = new Packet();
				byte[] bts = new byte[msg.getMessageLength()];
				msg.writeToNIToOmcMsg(bts, msg);
				pack.setMsg(bts);
				pack.setLen(bts.length);
				logger.info(">>>>>>>>>>>>北向接口发送给OMC的消息,发送数据包>>>>>>>>>>>>>>");
				//发送消息
				udpNetComm.send(pack, ip, port);
				int sendMsgTransId = msg.getTransId();
				//接收相应消息
				NIToOmcResponseMsg convertObj=null;
				
				while (!Thread.interrupted()) {
					BlockingQueue<Packet> receivedMsgQueue = UDPNetComm.responseMsgQueue;
					//标志位
					int niToOmcFlag = 0;
					if(!receivedMsgQueue.isEmpty()){
						Iterator<Packet> niToOmcIterator = receivedMsgQueue.iterator();
						while(niToOmcIterator.hasNext()){
							Packet omcToNINextMsg = niToOmcIterator.next();
							int responseTransId = Integer.parseInt(CodecUtil.getBCDString(omcToNINextMsg.getMsg(), 6, 8), 16);
							if(sendMsgTransId==responseTransId){
								logger.info("北向接口发送给OMC的消息接收响应消息 Transaction Id:"+ responseTransId);
								convertObj = (NIToOmcResponseMsg)udpNetComm.convertToProtoObj(omcToNINextMsg);
								niToOmcFlag = 1;
								niToOmcIterator.remove();
								break;
							}
						}
					}
					if(niToOmcFlag==1){
						break;
					}
				}
				return convertObj;
			} catch (Exception e) {
				logger.error("线程【"+Thread.currentThread().getName()+"】InterruputedExecption", e);
			}
			return null;
		}
	}
	
	/**
	 * 逻辑处理之后的回复消息
	 */
	public void invokeResponseMsg(CncpBaseResponseMsg msg,String ip,Integer port) {
		if (msg == null){
			throw new IllegalArgumentException("消息不能为空！");
		}
		CncpBaseResponseTask baseResponseTask = new CncpBaseResponseTask(msg, ip, port);
		try {
			pool.submit(baseResponseTask);
		} catch (Exception e) {
			logger.error(">> task interrupted ", e);
		}
	}
	//逻辑处理之后的回复消息
	class CncpBaseResponseTask implements Runnable {
		final CncpBaseResponseMsg msg;
		final String ip;
		final Integer port;
		CncpBaseResponseTask(CncpBaseResponseMsg msg,String ip,Integer port) {
			this.msg = msg;
			this.ip = ip;
			this.port = port;
		}
		@Override
		public void run() {
			try {
				Packet pack = new Packet();
				byte[] bts = new byte[20];
				msg.writeCncpBaseResponseMsg(bts, msg);
				pack.setMsg(bts);
				pack.setLen(bts.length);
				logger.info(">>>>>>>>>>>>逻辑处理之后的回复消息,发送数据包>>>>>>>>>>>>>>");
				//发送消息
				udpNetComm.send(pack, ip, port);
			} catch (Exception e) {
				logger.error("线程【"+Thread.currentThread().getName()+"】InterruputedExecption", e);
			}
		}
	}
	
    public static void nIToOmcTimeOutJob(int outTime,int transId,NIToOmcTask task) throws SchedulerException{
    	System.err.println("nIToOmcTimeOutJob>>>>>>>>>>>>>>");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(NIToOmcTimerOutJob.class).withIdentity(""+transId, JOB_GROUP_NAME).build();
        NIToOmcMsg nIToOmcMsg = task.msg;
		CncpBaseResponseMsg cncpBaseResponseMsg = new CncpBaseResponseMsg(nIToOmcMsg.getSrcType(), nIToOmcMsg.getDestType(),
				nIToOmcMsg.getMessageLength(), nIToOmcMsg.getMessageType(), transId, nIToOmcMsg.getNeType(),
				nIToOmcMsg.getInstId(), nIToOmcMsg.getSubType(), nIToOmcMsg.getHoldBit(), (char) CncpProtoType.TIMEOUT,
				(char) 0);
		jobDetail.getJobDataMap().put(NIToOmcTimerOutJob.NITOOMCUSERDATA, cncpBaseResponseMsg);
        //创建触发器
        Trigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity(""+transId, TRIGGER_GROUP_NAME)
        				.startAt(DateBuilder.futureDate(outTime, DateBuilder.IntervalUnit.SECOND)).build();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();
    }
    
    /** 
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
     */  
    public static void removeTimeOutJob(String transId, String jobGroupName,String triggerGroupName) {  
    	System.err.println("removeTimeOutJob>>>>>>>>>>>>>>");
        try {  
            Scheduler sched = schedulerFactory.getScheduler();  
            TriggerKey triggerKey = TriggerKey.triggerKey(transId, triggerGroupName);
            sched.pauseTrigger(triggerKey);// 停止触发器  
            sched.unscheduleJob(triggerKey);// 移除触发器  
            sched.deleteJob(JobKey.jobKey(transId, jobGroupName));// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }   
    
}
