package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollController
 */
@WebServlet("/insert.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String memberName = request.getParameter("memberName");
		String memberId = request.getParameter("memberId");
		String nickname = request.getParameter("nickname");
		String memberPwd = request.getParameter("memberPwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Member m = new Member();
		m.setMemberName(memberName);
		m.setMemberId(memberId);
		m.setNickname(nickname);
		m.setMemberPwd(memberPwd);
		m.setPhone(phone);
		m.setEmail(email);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			
			request.setAttribute("successMsg", "어머, 성공해버림!!");
			request.getRequestDispatcher("views/common/success.jsp").forward(request, response);
			
		}else {
			request.setAttribute("failMsg", "아; 실패ㅜㅜ");
			request.getRequestDispatcher("views/common/fail.jsp").forward(request, response);
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
