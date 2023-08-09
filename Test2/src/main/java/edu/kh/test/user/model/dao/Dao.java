package edu.kh.test.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.test.user.common.JDBCTemplate;
import edu.kh.test.user.model.vo.User;

public class Dao {

	public User searchUser(Connection conn, int userNo) {
		
		// 필요한 객체 맹글고
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		
		String sql = "SELECT * FROM TB_USER WHERE USER_NO = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				u = new User (rset.getInt("user_no"),
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
		return u;
	}
	
	
}
