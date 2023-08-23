package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//import com.kh.common.JDBCTemplate;
import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.vo.Member;

public class MemberDao {

	// prop 객체 생성
	private Properties prop = new Properties();
	
	public MemberDao() {  // 기본생성자. MemberDao() 호출될때마다 계속 이 기본생성자 불러들임 / 반환형 없으니까 생성자. 
		
		// 해당 위치에서 .class에 접근해서 저 파일 갖다줘
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		// select문 => ResultSet 객체 (한행) => Member 객체로 받을 수 있음
		Member m = null;	// Member 객체 m
		
		// 쿼리 돌려야하니까 준비
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");	// "키값"을 통해 value(SELECT~)를 가져와서 sql에 담음
		
		try {
			pstmt = conn.prepareStatement(sql);	// 미완성 쿼리 ('?' 들어있음) // 규칙 구문
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);	// 이제 완성 (.setInt 놓치지 않기)("userId" 이건 딱 이 값을 넣는거니까 변수 userId(사용자가 입력하는 값)로 작성)
			
			rset = pstmt.executeQuery();	// 실행. 조회된 결과가 있다면 한행 | 조회된 결과가 없다고 하면 아무것도 안담김
			
			if(rset.next()) {	// (true) 커서깜박이 이동했다 == 조회된 결과가 있다
				// new Member() == 기본생성자 호출
				// new Member(어쩌고, 저쩌고, ...) == 매개변수생성자 호출 / 반드시 vo에 원하는 매개변수생성자 있어야함
				// rset에는 db의 컬럼이 들어가 있음. 따라서 ("user_no") <= db컬럼과 동일해야함(단, 대소문자상관x)!! 안그럼 '부적절한 index 오류' 뜸
				// 뽑아온 값을 null로 초기화해둔 m에 담아
				m = new Member(rset.getInt("user_no"),	
							   rset.getString("user_id"),
							   rset.getString("user_Pwd"),
							   rset.getString("user_name"),
							   rset.getString("phone"),
							   rset.getString("email"),
							   rset.getString("address"),
							   rset.getString("interest"),  // "운동, 요리, 게임"
							   rset.getDate("enroll_date"),
							   rset.getDate("modify_date"),
							   rset.getString("status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*JDBCTemplate.*/close(rset);
			/*JDBCTemplate.*/close(pstmt);
		}
		
		return m;
	
	}
	
	
	public int insertMember(Connection conn, Member m) {
		
		// insert문 => 처리된 행 수(반환) => 트렌젝션 처리(삽입, 업데이트, 삭제)
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember"); // "entry 키값"
		
		try {
			pstmt = conn.prepareStatement(sql);  // 쿼리를 불러오고 (미완성)
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());	// (완성)
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt); /*jdbctemplate 생략*/
		}
		
		return result;
		
	}
	
	
	public int updateMember(Connection conn, Member m) {
		
		// update문 => 처리된 행 수(반환) => 트랜젝션 처리
		// member-mapper에 쿼리문 추가해주고 옴
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember"); //entry 키값

		try {
			pstmt = conn.prepareStatement(sql); // 미완성 쿼리
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate(); // result로 반드시 받기!!! 안그럼 result는 계속 '0' 이라서 값이 안나옴
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public Member selectMember(Connection conn, String userId) { // * 이름 내맘대로, m.getUserId() 반환형 String이니까 맞춤 *
		// select문 => 한행 => ResultSet 객체 => Member 객체
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// entry 추가해주고 오기
		String sql = prop.getProperty("selectMember"); // 미완성
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("user_no"),	
						   rset.getString("user_id"),
						   rset.getString("user_Pwd"),
						   rset.getString("user_name"),
						   rset.getString("phone"),
						   rset.getString("email"),
						   rset.getString("address"),
						   rset.getString("interest"),
						   rset.getDate("enroll_date"),
						   rset.getDate("modify_date"),
						   rset.getString("status"));
			} // * m : 갱신된 회원 정보 다시 조회해서 그 정보 담음 *
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
		
	}
	
	
	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		
		// update문 => 처리된 행수(반환) => 트랜젝션 처리
		// entry 추가하고 옴

		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);  // 미완성
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public int outMember(Connection conn, String userId, String userPwd) {
		
		// update문 => 처리된 행수(반환) => 트랜젝션 처리
		// entry 추가하고 옴
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("outMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();  //.executeUpdate() 반환형 : int
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	// ajax 아이디 중복 체크
	public int idCheck(Connection conn, String checkId) {
		// select문 => ResultSet => 한개 숫자 => int
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("count"); // count(*)을 별칭 count로 줌
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	
	
}
