package com.sunkaisens.nms.ws.dbUtils.comeInGoOutConfig;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ComeInGoOutConfigDbUtil {

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

	// 查询终端信息配置
	public static Connection getTerminalInfoConfigConnection() {
		try {
			Connection conn = DriverManager.getConnection(p.getProperty("queryTerminalInfoConfig_jdbcurl"),
					p.getProperty("queryTerminalInfoConfig_username"), p.getProperty("queryTerminalInfoConfig_password"));
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
