package com.kh.member.model.service;

import java.sql.Connection;

//import com.kh.common.JDBCTemplate;
import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		
		// 'connection 객체 생성 후 반환하는 메소드' 만들어 놓은거 가져와서 쓸거야
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		// dao 호출하고, 결과받고 (자료형은 꼭 맞추고, 변수는 맘대로)
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
	
		/*JDBCTemplate.*/close(conn);
		
		return m;
	}
	
	public int insertMember(Member m) {
		
		Connection conn = getConnection(); 		/*JDBCTemplate. 생략*/
		int result = new MemberDao().insertMember(conn, m);
	
		// (DML문이니까) 트랜젝션 처리
		if(result > 0) { // 성공
			commit(conn);
		}else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
}
