package com.sunkaisens.nms.ws.dbUtils.performanceInfo;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PerformanceInfoDbUtil {

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

	// 性能数据数据库连接
	public static Connection getPerfInfoCorrespondConnection() {
		try {
			Connection conn = DriverManager.getConnection(p.getProperty("queryPerformanceInfo_jdbcurl"),
					p.getProperty("queryPerformanceInfo_username"), p.getProperty("queryPerformanceInfo_password"));
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
