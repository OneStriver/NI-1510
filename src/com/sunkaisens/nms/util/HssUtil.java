package com.sunkaisens.nms.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sunkaisens.nms.ws.dbUtils.alarmDeviceStatus.AlarmDeviceStatusDbUtil;

public class HssUtil {
	private static Properties p;
	private static Connection conn=null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static List<String> list = new ArrayList<>();
	
	static {
		try {
			p = new Properties();
			p.load(new FileInputStream("confs/db.properties"));
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getImsi(String mdn) {
		try {
			String sql = "select IMSI from hss.HLR where MDN = ?";
			conn = DriverManager.getConnection(
					p.getProperty("jdbcurl"), p.getProperty("username"),
					p.getProperty("password"));
			ps = conn.prepareStatement(sql);
			ps.setString(1, mdn);
			rs = ps.executeQuery();
			while(rs.next()){
				return rs.getString("IMSI");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}
  
	public static List<String> getGroupNum(){
		list.clear();
		try {
			String sql = "select groupNum from hss.csHLRGroupInfo";
			conn = DriverManager.getConnection(
					p.getProperty("jdbcurl"), p.getProperty("username"),
					p.getProperty("password"));
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String groupStr = rs.getString("groupNum").trim();
				list.add(groupStr);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			AlarmDeviceStatusDbUtil.free(rs, ps, conn);
		}
		return null;
	}
	
	
}
