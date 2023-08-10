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
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// (비밀번호변경이라서 한글 없으니 인코딩 생락)
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().updatePwd(userId, userPwd, updatePwd);
		
		// * updateMem 으로 받아와서 세션 갈아끼우기 *
		HttpSession session = request.getSession();
		
		if(updateMem == null) { // 실패
			session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
		}else { // 성공
			session.setAttribute("loginMember", updateMem);
			session.setAttribute("alertMsg", "성공적으로 비밀번호 변경되었습니다.");
		}
		
		// 성공 실패 모두 알람창 띄우고, 마이페이지 화면으로 돌아가도록 (마이페이지는 본 페이지니까 url요청방식-response)
		response.sendRedirect(request.getContextPath() + "/myPage.me");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
