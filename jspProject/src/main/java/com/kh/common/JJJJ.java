package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JJJJ {

	public static Connection getConnection() {
			
			Connection conn = null;
			
			// prop생성, driver(4개) 변수에 담고
			Properties prop = new Properties();	
			String filePath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
			
			try {
				
				// 담은거 prop에 실어
				prop.load(new FileInputStream(filePath));
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				
				// 클래스등록 (드라이버)
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}
	
	
	
	
}
