package edu.kh.test.user.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {	/* 메소드들을 static으로 올린다 */

	// 1) 커넥션 생성 후 반환
	public static Connection getConnection() {
		
		// 1. conn 생성
		Connection conn = null;
		
		try {
			// 2. 클래스 파일로 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 3. conn 할당    (url, 계정, 비번)
			//  . 커넥션 객체 생성(url, 계정명, 비번) => driverManager
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TEST1", "TEST1");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4. conn 반환
		return conn;
		
	}
	
	
	
	// 2) 반납(conn, stmt, rset)
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
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
