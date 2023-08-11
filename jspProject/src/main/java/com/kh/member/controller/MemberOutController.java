package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberOutController
 */
@WebServlet("/outMember.me")
public class MemberOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		int result = new MemberService().outMember(userId, userPwd);
		
		
		if(result > 0) { // 회원탈퇴 성공
			
			// request 는... (8/11 9:19)
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원탈퇴 되었습니다. 그동안 이용해주셔서 감사합니다.");
			// 로그아웃 시키기 (세션에 loginMember 라는 '키값'에 해당하는걸 지우기)
			// session.invalidate(); 불가!! 이거하면 session영역에 있는 loginMember, alertMsg 모두 지워져서 성공후 알람창도 안뜨게됨..
			session.removeAttribute("loginMember");
			
			response.sendRedirect(request.getContextPath());
			
		}else {	// 실패
			
			HttpSession session = request.getSession();
			session.setAttribute("errorMsg", "회원탈퇴 실패!!");
			
			// 마이페이지로 이동시킴 => /jsp/myPage.me  url재요청!
			response.sendRedirect(request.getContextPath() + "/myPage.me");
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
