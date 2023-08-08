package edu.kh.test.user.model.service;

import java.sql.Connection;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.dao.UserDao;
import edu.kh.test.user.model.vo.User;

public class UserService {

	public User selectUser(int userNo) {
		
		// 1. 커넥션 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. dao 호출 및 결과 받기 => conn까지 함께 보내야함!
		User user = new UserDao().selectUser(conn, userNo);
		
		
		// 3. 커넥션 객체 반납
		JDBCTemplate.close(conn);
		
		return user;
		
		
	}
	
	public User loginUser(String userId, int age) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		User user = new UserDao().loginUser(conn, userId, age);
		
		JDBCTemplate.close(conn);
		
		return user;
		
	}
	
}
