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
	
	public Member updateMember(Member m) {
		
		Connection conn = getConnection();	//JDBCTemplate 생략
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // 정보수정 성공
			commit(conn);
			
			// 커밋=확정 후,
			// * 갱신된 회원 객체 다시 조회 해오기 + 결과 받아 *
			updateMem = new MemberDao().selectMember(conn, m.getUserId()); // dao에서 쿼리 돌려야하니까 conn 필요. 조회해야되니까 m 속 id필요
			
		}else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		// * 갱신된 회원 정보를 다시 조회해서 담은 그 정보를 반환! *
		return updateMem;
	}
	
	
	public Member updatePwd(String userId, String userPwd, String updatePwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) { // 비밀번호변경 성공
			commit(conn);
			
			// 바뀐 비밀번호 확정 후,
			// * 갱신된 회원 객체 다시 조회해오기 *
			updateMem = new MemberDao().selectMember(conn, userId); // updateMem에 꼭 담기
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	
	public int outMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().outMember(conn, userId, userPwd);
		
		if(result > 0) { // 성공
			commit(conn);
		}else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	// ajax 아이디 중복 체크
	public int idCheck(String checkId) {
		
		Connection conn = getConnection();
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		return count;
				
	}
	
	
}
