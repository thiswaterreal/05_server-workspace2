package edu.kh.test.user.model.service;

import java.sql.Connection;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.dao.Dao;
import edu.kh.test.user.model.vo.User;

public class Service {

	public User searchUser(int userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		User s = new Dao().searchUser(conn, userNo);
		
		JDBCTemplate.close(conn);
		
		return s;
		
	}
	
	
}
