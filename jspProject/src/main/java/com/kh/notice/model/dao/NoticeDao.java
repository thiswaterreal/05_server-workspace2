package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*; /**/
import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	private Properties prop = new Properties();
	public NoticeDao() {
		
		try {
			// (8/11 10:35)
			prop.loadFromXML(new FileInputStream(NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Notice> selectNoticeList (Connection conn) {
		// select문 => ResultSet 객체 => 여러행 => ArrayList<Notice> 객체 == notice객체 여러개 담긴곳이 ArrayList!
		// entry 작성하고 옴
		
		ArrayList<Notice> list = new ArrayList<Notice>();	// 텅빈 리스트 (따라서 null 나올 수 없음)

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList"); // 완벽한 쿼리
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery(); // 쿼리 돌리고 담는거 잊지마!
			
			while(rset.next()) { // *** 커서가 계속 돌도록 while 반복문 돌루!! (여러행 조회) ***
				//list.add(노티스객체만드는구문());
				//list.add(new Notice());
				list.add(new Notice(rset.getInt("notice_no"),
									rset.getString("notice_title"),
									rset.getString("user_id"), // *** notice_writer (x)!! 조심!! (NOTICE_WRITER = USER_NO)로 두 테이블 조인했으니까 user_id 가져올수있음!!
									rset.getInt("count"),
									rset.getDate("create_date")
									));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	public int insertNotice(Connection conn, Notice n) {
		// insert문 => 처리된 행수 반환 => 트랜젝션 처리(서비스에서)
		// entry 추가
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter())); // "1" => 1
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int increaseCount(Connection conn, int noticeNo) {
		// update문 => 처리된 행수 반환 => 트랜젝션 처리
		// entry 추가
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public Notice selectNotice(Connection conn, int noticeNo) {
		// select문 => ResultSet 객체 => 한행 => Notice 객체 하나로 받아
		// entry 추가
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("notice_no"),
							   rset.getString("notice_title"),
							   rset.getString("notice_content"),
							   rset.getString("user_id"), // *** userId (join 했으니까 가능) ***
							   rset.getDate("create_date"));
						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;
	}
	
	public int updateNotice(Connection conn, Notice n) {
		// update문 => 처리된 행수 반환 => 트랜젝션 처리
		// entry 추가
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int deleteNotice(Connection conn, int noticeNo) {
		// update문 => 처리된 행수 반환 => 트랜젝션 처리
		// entry 추가
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
		
	}
	
	
	
	
	
	
}
