package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection() {
			
			Connection conn = null;
	
			Properties prop = new Properties();	
			
			// > 읽어들이고자 하는 classes 폴더 내에 'driver.properties' 파일의 물리적인 경로. (규칙)
			// C:/05_server-workspace2/jspProject/WebContent/classes/db/driver/driver.properties
			String filePath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
			
			
			try {
				prop.load(new FileInputStream(filePath));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
	
			try {
				Class.forName(prop.getProperty("driver"));
				
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
			
		}
		
		
		
		
		// 2_1. Connection 객체 전달 받아서, commit 시켜주는 commit 메소드
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		
		// 2_2. Connection 객체 전달 받아서, rollback 시켜주는 rollback 메소드
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 3_1. Connection 객체 전달 받아서, 반납 시켜주는 close 메소드
		public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 3_2. Statement 객체 전달 받아서, 반납 시켜주는 close 메소드
		public static void close(Statement stmt) {
			try {
				if(stmt != null && stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		
		// 3_3. ResultSet 객체 전달 받아서, 반납 시켜주는 close 메소드
		public static void close(ResultSet rset) {
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
}
