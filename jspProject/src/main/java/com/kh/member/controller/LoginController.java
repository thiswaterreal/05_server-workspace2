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

@WebServlet("/login.me")
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
		
		// 1) 전달값에 '한글'이 있을 경우 인코딩 처리해야됨 (POST방식에만)
		// request.setCharacterEncoding("UTF-8");	// 아이디,비번 다 영어숫자니까 필요x
		
		// 2) 요청시 전달값 뽑아서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 요청처리 (db에 sql문 실행)
		//	  해당 요청을 처리하는 서비스 클래스의 메소드 호출 및 결과 받기
		Member loginMember = new MemberService().loginMember(userId, userPwd);
		System.out.println(loginMember);
		
		// 4) 처리된 결과를 가지고 사용자가 보게 될 응답뷰(jsp) 지정 후 포워딩 또는 url 재요청
		/*
		 * 응답페이지에 전달할 값이 있을 경우 어딘가에 담아야됨!! (담을 수 있는 영역 == JSP 내장객체 4종류)
		 * 1) application	: 여기에 담긴 데이터는 웹 애플리케이션 전역에서 다 꺼내서 쓸 수 있음
		 * 2) session		: 여기에 담긴 데이터는 내가 직접 지우기 전까지, 세션이 만료(서버가 멈추거나, 브라우저 종료) 되기 전까지
		 * 					  어떤 jsp든, 어떤 servlet이든 꺼내쓸 수 있음
		 * 3) request		: 여기에 담긴 데이터는 현재 이 request 객체를 '포워딩한 응답 jsp에서만' 꺼내쓸 수 있음 (일회성 느낌)
		 * 4) page			: 해당 jsp에서 담고 그 jsp에서만 꺼내쓸 수 있음
		 * 
		 * 공통적으로 데이터를 담고자 한다면 .setAttribute("키", 벨류)
		 * 		   데이터를 꺼내고자 한다면 .getAttribute("키") : 벨류 반환. (object 타입으로) 
		 * 		   데이터를 지우고자 한다면 .removeAttribute("키")
		 */
		
		
		if(loginMember == null) {
			// 조회결과 없음 == 로그인 실패!! => 에러문구가 보여지는 에러페이지 응답
			request.setAttribute("errorMsg", "로그인 실패");	// 키-벨류
			// 응답페이지(jsp)에게 위임시 필요한 객체 => RequestDispatcher
			// 그 다음, 포워딩 방식
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}else {
			// 조회결과 있음 == 로그인 성공!!
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
