package com.kh.common;

import java.sql.Connection;
import java.util.Properties;

public class JDBCTemplate {

	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		String filePath = JDBCTemplate.class.getResource("/db/driver.driver.properties").getPath();
		
		
		
	}
	
	
}
