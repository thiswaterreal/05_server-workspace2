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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 설정(Post방식이니까)
		request.setCharacterEncoding("UTF-8");
		
		// 요청시 전달 값 뽑아서 변수 및 객체에 담기
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		// 배열 => String으로 변신시켜
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		// 너무 많으니까 m에 담아
		Member m = new Member(userId, userName, phone, email, address, interest);
		System.out.println("m 누구냐?? : " + m); // 기존 myPage 에서는 처음 로그인정보 => 사이트에서 입력값 변경시 그때부터 myPage에서 변경된 상태로 update하기위해 이동!! 	
		
		// 서비스 호출
		Member updateMem = new MemberService().updateMember(m);

		
		if(updateMem != null) { // 성공 (8/10 10:23)
			
			// (url재요청방식으로)
			HttpSession session = request.getSession();
			
			// *** updateMem 으로 받아와서 세션 갈아끼우기 ***
			session.setAttribute("loginMember", updateMem);
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정하였습니다.");
			// 마이페이지로 이동시킴 => /jsp/myPage.me  url재요청!
			response.sendRedirect(request.getContextPath() + "/myPage.me");
			
		}else { // 실패
			// (포워딩방식으로)
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
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
