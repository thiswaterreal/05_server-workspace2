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
		
		// 1) 전달값에 '한글'이 있을 경우 인코딩 처리해야됨 (POST방식 | '한글'들어갈때) 	// (GET)header : utf-8 | (POST)body : ms949
		// request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달값 뽑아서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3) 요청처리 (db에 sql문 실행)
		//	  해당 요청을 처리하는 서비스 클래스의 메소드 호출 및 결과 받기
		Member loginMember = new MemberService().loginMember(userId, userPwd);
		System.out.println(loginMember); //Member [userNo=4, userId=user06, userPwd=pass06, userName=이둥이, phone=01088887777, email=doong@naver.com, address=반룡마을, interest=운동, enrollDate=2023-08-10, modifyDate=2023-08-10, status=Y]
		
		// 4) 처리된 결과를 가지고 사용자가 보게 될 응답뷰(jsp) 지정 후 포워딩 또는 url 재요청
		/*
		 * 응답페이지에 전달할 값이 있을 경우 어딘가에 담아야됨!! (담을 수 있는 영역 == JSP 내장객체 4종류)
		 * 1) application	: 여기에 담긴 데이터는 웹 애플리케이션 전역에서 다 꺼내서 쓸 수 있음
		 * 2) session		: 여기에 담긴 데이터는 내가 직접 지우기 전까지, 세션이 만료(서버가 멈추거나, 브라우저 종료) 되기 전까지
		 * 					  어떤 jsp든, 어떤 servlet이든 꺼내쓸 수 있음
		 * 3) request		: 여기에 담긴 데이터는 현재 이 request 객체를 '포워딩한 응답 jsp에서만' 꺼내쓸 수 있음 (일회성 느낌)
		 * 4) page			: 해당 jsp에서 담고 그 jsp에서만 꺼내쓸 수 있음
		 * 
		 * 공통적으로 데이터를 담고자 한다면   .setAttribute("키", 벨류)
		 * 		   데이터를 꺼내고자 한다면 .getAttribute("키") : 벨류 반환. (object 타입으로) 
		 * 		   데이터를 지우고자 한다면 .removeAttribute("키")
		 */
		
		
		if(loginMember == null) {
			// * 조회결과 없음 == 로그인 실패!! => 에러문구가 보여지는 에러페이지 응답
			
			request.setAttribute("errorMsg", "로그인 실패");	// "키"-벨류
			// 응답페이지(jsp)에게 위임시 필요한 객체 => RequestDispatcher
			// 그 다음, 포워딩 방식
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
			
			
		}else {
			// * 조회결과 있음 == 로그인 성공!! => 메인페이지 응답 (index.jsp)
			
			// 로그인한 회원정보 (loginMember)를 session에 담기 (여기저기서 가져다 쓸 수 있도록!)
			
			// Servlet에서는 session에 접근하고자 한다면 우선 session 객체 얻어와야됨 (request 도움을 받아서)
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember); //"키",벨류(login한 멤버 정보)
			
			// 1. '포워딩 방식' 응답 뷰 출력
			// 해당 선택된 jsp가 보여질 뿐 url에는 여전히 현재 이 서블릿 매핑값(/login.me)이 남아있음
			//RequestDispatcher view = request.getRequestDispatcher("index.jsp"); // "WebContent/" 생략
			//view.forward(request, response);
			// (현재)localhost:8001/jsp/login.me	=>	localhost:8001/jsp (이렇게 바꾸고 싶음 == url재요청방식)
			
			// 2. 'url 재요청 방식' (sendRedirect 방식)
			// 기존에 저 페이지를 응답하는 url이 존재한다면 사용 가능 (한번이라도 본 화면이면 가능)
			// localhost:8001/jsp
			
			//response.sendRedirect("/jsp"); 	//방법1
			response.sendRedirect(request.getContextPath()); // "/jsp"    //방법2
			//사용자에게보내는응답.url로이동해라(프로젝트루트 /jsp = 메인페이지 index.jsp(즉, menubar.jsp))
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
