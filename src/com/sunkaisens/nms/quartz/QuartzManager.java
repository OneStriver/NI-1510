package com.sunkaisens.nms.quartz;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager { 
	
	private static Logger logger = Logger.getLogger(QuartzManager.class);
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	/**
	 * 定时进行心跳检测任务
	 */
	public static void schedulerJob(Integer interval) {
		try {
			logger.info(">>>>>>>>开始执行设备心跳上报任务>>>>>>>>");
			//是否检测Quartz的版本更新
			//System.setProperty("org.terracotta.quartz.skipUpdateCheck","true");
			// 创建任务
			JobDetail jobDetail = JobBuilder.newJob(HeartBeatJob.class).withIdentity("JobOne", "JobGroup").build();
			// 创建触发器 每10秒钟执行一次
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("TriggerOne", "TriggerGroup")
					//.startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).repeatForever())
					.build();
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 将任务及其触发器放入调度器
			scheduler.scheduleJob(jobDetail, trigger);
			// 调度器开始调度任务
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 定时删除日志
	 */
	public static void addJob() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(DeleteLogJob.class)
                    .withIdentity("Job", "Group")
                    .usingJobData("Name", "Quartz")
                    .build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withSchedule( 
                    	//每天凌晨1点执行*/5 * * * * ?   
                        CronScheduleBuilder.cronSchedule("0 0 1 * * ?"))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
