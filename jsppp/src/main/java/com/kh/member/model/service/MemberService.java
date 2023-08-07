package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		
		// 'connection 생성 후 반환하는 메소드' 만들어 놓은거 가져와서 쓸거야
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
	
		//// Member m = 로 받고, close, return, 자료형변경
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
}
