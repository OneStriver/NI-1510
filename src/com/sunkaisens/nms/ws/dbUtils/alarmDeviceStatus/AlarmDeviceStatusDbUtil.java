package com.sunkaisens.nms.ws.dbUtils.alarmDeviceStatus;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AlarmDeviceStatusDbUtil {

	private static Properties p;
	static {
		try {
			p = new Properties();
			p.load(new FileInputStream("confs/db.properties"));
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 初始化数据库连接
	public static Connection getInitConnection() {
		try {
			Connection conn = DriverManager.getConnection(p.getProperty("jdbc.mysql.url"),
					p.getProperty("jdbc.mysql.username"), p.getProperty("jdbc.mysql.password"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 获取故障上报的告警的对应关系
	public static Connection getNorthIntefaceConnection() {
		try {
			Connection conn = DriverManager.getConnection(p.getProperty("northInterface_jdbcurl"),
					p.getProperty("northInterface_username"), p.getProperty("northInterface_password"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
}
