package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		// 로그인한 회원 정보를 얻어내는 방법
		// 1. input type = "hidden" 으로 애초에 요청시 숨겨서 전달하기
		int userNo = Integer.parseInt(request.getParameter("userNo"));	// request.getParameter로 가져오면 다 String형이니까 다시 형맞추기	
		// 2. *** session 안에 담긴 loginMember 를 활용하는 방법 *** (8/11 12:28)
		//HttpSession session = request.getSession();
		//int userNo = ((Member)session.getAttribute("loginMember")).getUserNo(); // 반환형 object임
	
		// 3개를 담아갈건데..
		// 이번엔 기본생성자로 해보장구(예)0 null null 0 0 )
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		// *** int형인 userNo을 => String 형으로 바꾸기 *** 왜?? vo에서 짤 때, 요소들 전부 String형으로 짬
		// (방법1) n.setNoticeWriter(userNo + "");
		// (방법2)
		n.setNoticeWriter(String.valueOf(userNo));
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) { // 공지사항 등록 성공 => alert 띄우면서 목록조회 화면으로 url 재요청(본적o)
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 공지사항 등록되었습니다!");
			
			response.sendRedirect(request.getContextPath() + "/list.no");
			
		} else { // 실패 => 에러문구 담아서 에러페이지로 포워딩(본적x)
			request.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
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
