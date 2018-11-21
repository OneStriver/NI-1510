package com.sunkaisens.nms.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.sunkaisens.nms.util.StackUtil;

public class FtpUtils {
	private static Logger logger = Logger.getLogger(FtpUtils.class);
	private static String ip;
	private static int port;
	private static String username;
	private static String password;
	private static Properties p;
	private static FTPClient ftp;
	private static String ftpConfigPath;
	static {
		p = new Properties();
		ftpConfigPath = "confs/ftp.properties";
		try {
			p.load(new FileInputStream(ftpConfigPath));
			ip = p.getProperty("ip");
			port = Integer.parseInt(p.getProperty("port"));
			username = p.getProperty("username");
			password = p.getProperty("password");
		} catch (Exception e) {
			logger.error(StackUtil.getTrace(e));
		}
	}
	/**
	 * FTP上传文件 
	 */
	public static synchronized boolean upload(File file, String fileName) {
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("UTF-8");
			ftp.connect(ip, port);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

			ftp.login(username, password);
			ftp.enterLocalPassiveMode();
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			FileInputStream input = null;
			input = new FileInputStream(file);
			// 上传文件存储
			ftp.storeFile(fileName, input);
			input.close();
			return true;
		} catch (Exception e) {
			logger.error("上传文件异常");
			return false;
		} finally {
			closeOperation();
		}
	}
	
	/**
	 * FTP下载文件
	 */
	public static synchronized File downLoadFile(String fileName) {
		try {
			ftp = new FTPClient();
			ftp.setControlEncoding("UTF-8");
			ftp.connect(ip, port);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

			ftp.login(username, password);
			ftp.enterLocalPassiveMode();
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			
			File localFile = new File("confs/ftp/" + fileName);
			FTPFile[] ftpFiles = ftp.listFiles();
			for (FTPFile file : ftpFiles) {
				String name = file.getName();
				if (name.equals(fileName)) {
					OutputStream outputStream = new FileOutputStream(localFile);
					ftp.retrieveFile(name, outputStream);
					outputStream.close();
					break;
				}
			}
			return localFile;
		} catch (Exception e) {
			logger.error("下载文件异常");
			return null;
		} finally {
			closeOperation();
			
		}
	}

	private static void closeOperation() {
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			logger.error("连接异常", e);
		}
	}
	
}