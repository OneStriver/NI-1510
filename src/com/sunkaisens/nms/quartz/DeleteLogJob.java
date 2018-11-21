package com.sunkaisens.nms.quartz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunkaisens.nms.util.ConfigurationManager;


public class DeleteLogJob implements Job {
	
	private static Logger logger = Logger.getLogger(DeleteLogJob.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private List<File> cacheFileList = new ArrayList<File>();
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		cacheFileList.clear();
		Integer deleteLogTime = ConfigurationManager.getInstance().getDeleteLogTime();
		try {
			String filesPath = System.getProperty("user.dir")+File.separator+"logs";
			List<File> getAllLogFileList = getFileList(filesPath);
			logger.info(">>>>>>>>>>>>>>>开始删除北向接口日志记录文件>>>>>>>>>>>>>>>>>");
			for (int i = 0; i < getAllLogFileList.size(); i++) {
				String fileName = getAllLogFileList.get(i).getName();
				if(fileName.endsWith(".log")){
					String fileNamePrefix = fileName.split("\\.")[0].split("\\_")[1];
					int intervalDays = (int) ((new Date().getTime() - sdf.parse(fileNamePrefix).getTime()) / (1000*3600*24));
					if(intervalDays>deleteLogTime){
						getAllLogFileList.get(i).delete();
					}
				}
			}
			logger.info(">>>>>>>>>>>>>>>结束删除北向接口日志记录文件>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<File> getFileList(String strPath) {
        File rootFile = new File(strPath);
        if(rootFile.isDirectory()){
        	getFiles(rootFile);
        }
        return cacheFileList;
    }
	
	private void getFiles(File file) {
		File[] files = file.listFiles();
		for (File templeFile : files) {
			if (templeFile.isDirectory()) {
				getFiles(templeFile);
			} else {
				cacheFileList.add(templeFile);
			}
		}
	}
	
}
