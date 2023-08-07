package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.lo")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//request.setCharacterEncoding("UTF-8");
		
		// 요청시 전달값 뽑아서 변수에 저장
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 요청처리 (sql문 실행) : 요청 처리 서비스 클래스의 메소드 호출 및 결과받기
		Member loginMember = new MemberService().loginMember(userId, userPwd);
		
		//// Member loginMember = 로 받고,
		//// 처리된 결과를 가지고 사용자가 보게 될 응답뷰(jsp) 지정 후 포워딩 또는 url 재요청
		if(loginMember == null) {
			// 조회결과 없음 == 로그인 실패!!
			request.setAttribute("errorMsg", "로그인 실패");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}else {
			// 조회결과 있음 == 로그인 성공!!
			System.out.println("로그인 성공!!");
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
