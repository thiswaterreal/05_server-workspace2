package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.vo.User;

public class UserDao {
	
	// 조회 메소드
	public User selectUser(Connection conn, int userNo) {
		
		//select문 => ResultSet 객체 필요 => 한행 조회 => 따라서, User 객체 하나 있으면 됨
		
		// 1) jdbc용 객체 생성 + 필요한 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		// 2) sql 쿼리 작성
		String sql = "SELECT * FROM TB_USER WHERE USER_NO = ?";
		
		try {
			// 3) pstmt, rset 채워넣기
			pstmt = conn.prepareStatement(sql);	//미완성 (?를 담을수있음)
			
			pstmt.setInt(1, userNo);	  //완성
			
			rset = pstmt.executeQuery();  // 돌리면, 뭐라도있거나 | null
			
			if(rset.next()) {	// true 뭐라도있음
				user = new User(rset.getInt("user_no"),    // db에서 가져와야하니까 컬럼명 그대로(대소문자 상관x)
						        rset.getString("user_id"),
						        rset.getString("user_name"),
						        rset.getInt("user_age"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;	// user의반환형 : User
		
	}
	
	
	// 로그인 메소드
	public User loginUser(Connection conn, String userId, int age) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		String sql = "SELECT * FROM TB_USER WHERE USER_ID = ? AND USER_AGE = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, age);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				user = new User(rset.getInt("user_no"),
								rset.getString("user_id"),
								rset.getString("user_name"),
								rset.getInt("user_age"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
		
	}

}
