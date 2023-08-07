package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

		/* JDBC 메소드 모음집 */

public class JDBCTemplate {

	// 1. Connection 객체 생성한 후 해당 Connection 객체를 반환 시켜주는 getConnection 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		// classes 폴더 내에 'driver.properties' 파일
		String filePath = JDBCTemplate.class.getResource("/db/driver/driver/properties").getPath();
		
		
		try {
			prop.load(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			Class.forName(prop.getProperty("dricer"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;

		
	}
	
	
	
	// 2_1. Connection 객체 전달 받아서, commit 시켜주는 commit 메소드
	
	// 2_2. Connection 객체 전달 받아서, rollback 시켜주는 rollback 메소드
	
	// 3_1. Connection 객체 전달 받아서, 반납 시켜주는 close 메소드
	
	// 3_2. Statement 객체 전달 받아서, 반납 시켜주는 close 메소드
	
	// 3_3. ResultSet 객체 전달 받아서, 반납 시켜주는 close 메소드
	
}