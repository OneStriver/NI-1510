package com.sunkaisens.nms.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SocketUtil {
	private static Properties p = new Properties();
	public static Properties getProperty(){
		try {
			String path = System.getProperty("user.dir")+"/confs/nmsws.properties";
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			p.load(in);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
