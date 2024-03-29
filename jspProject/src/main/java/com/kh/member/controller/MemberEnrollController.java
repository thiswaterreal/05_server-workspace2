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

		// 1) 인코딩 작업(POST때 | 한글들어갈때)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청시 전달값 뽑아서 변수 및 객체에 기록하기 ("키값(name)" 적으면 벨류값 가져와서 담음)
		String userId = request.getParameter("userId");		// "user03"	(필수사항이니까 null값x)
		String userPwd = request.getParameter("userPwd");	// "pass03"
		String userName = request.getParameter("userName");	// "차은우"
		String phone = request.getParameter("phone");		// "01011112222" | ""
		String email = request.getParameter("email");		// "a@naver.com" | ""
		String address = request.getParameter("address");	// "서울" | ""
		String[] interestArr = request.getParameterValues("interest"); //["운동", "등산"] | null
		
		
		// 22) 많으니까 담아서 쓰자
		// 22) 방법1_기본생성자로 생성 후 setter 메소드 이용해서 담기 (담는 내용 소량일 때)
		// 22) 방법2_매개변수생성자로 생성과 동시에 담기 (담는 내용 많을 때)
		
		// String[]			--> String
		// ["운동", "등산"]	--> 운동,등산
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		//Member m = new Member(userId, userPwd, userName, phone, email, address, interestArr);	//오류남
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3) 요청처리(db에 sql문 실행) => 서비스 메소드 호출 및 결과 받기
		int result = new MemberService().insertMember(m);
	
		// 4) 처리결과를 가지고 사용자가 보게될 응답 뷰 지정 후, 포워딩 | url 재요청
		if(result > 0) { // 성공 (08/09 12:33)
			
			HttpSession session = request.getSession(); //심부름코드
			session.setAttribute("alertMsg", "성공적으로 회원가입이 되었습니다.");	// request는 포워딩 방식일때만 가능!!
			
			// 성공 => index 페이지 => url 재요청!!(한번이라도 봤으니까 가능)
			response.sendRedirect(request.getContextPath());
			
		}else { // 실패
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
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
