package com.taiwan.utils.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * 專門用於提供資料庫連線的類
 */
public class DbUtil {

	private static final String driver = "com.mysql.cj.jdbc.Driver";
// 資料庫驅動
	private static final String URL = "jdbc:mysql://%s:3306/Taiwan?serverTimezone=Asia/Taipei";
// 要連線到的資料庫地址
	private static final String USER = "root";
// 資料庫的使用者名稱
	private static final String PASSWORD = "rootitri";
// 資料庫的登入密碼
//	private static Connection conn = null;
// 資料庫連線物件，採用單例模式。
	static {
		try {
			Class.forName(driver);
			// 載入驅動
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		final String host = Optional.ofNullable(System.getenv("org.wildfly.datasources.mysql.host")).orElse(
				Optional.ofNullable(System.getProperty("org.wildfly.datasources.mysql.host")).orElse("35.194.146.30"));
		return String.format(URL, host);
	}

// 用於獲取資料庫連線物件的靜態方法，直接由類呼叫
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(getUrl(), USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
