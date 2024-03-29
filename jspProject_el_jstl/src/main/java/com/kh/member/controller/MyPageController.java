package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/myPage.me")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("마이페이지");
		
		// 로그인 전에 url쳐서 직접 요청도 가능하긴함
		// 로그인 전 요청시 => 메인페이지 응답, alert 띄우기 => url재요청 (request x)!!!!기억!!!!
		// 로그인 후 요청시 => 마이페이지 응답			  => 포워딩
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) { // 로그인 전. *** 세션이 뒀기 때문에 servlet에서도 쓸 수 있음 ***
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스 입니다.");
			response.sendRedirect(request.getContextPath());
		}else { // 로그인 후 (loginMember에 회원정보(m값) 담긴 상태로 메인페이지 도착해서..) 마이페이지를 누를시
			
			RequestDispatcher view = request.getRequestDispatcher("views/member/myPage.jsp");
			view.forward(request, response);
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
