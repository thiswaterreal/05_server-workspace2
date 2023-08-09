package edu.kh.test.user.model.service;

import java.sql.Connection;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.dao.Dao;
import edu.kh.test.user.model.vo.User;

public class Service {

	public User searchUser(int userNo) {
		
		// connection 열어주고
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출하고, 결과값 받고
		User s = new Dao().searchUser(conn, userNo);
		
		JDBCTemplate.close(conn);
		
		return s;
		
	}
	
	
}
