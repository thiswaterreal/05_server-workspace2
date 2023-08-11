package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeOutFormController
 */
@WebServlet("/outForm.no")
public class NoticeOutFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeOutFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNo = Integer.parseInt(request.getParameter("num"));
		String status = request.getParameter("status");
		
		Notice n = new Notice();
		
		n.setNoticeNo(noticeNo);
		n.setStatus(status);
		
		int result = new NoticeService().outNotice(n);
		
		if(result > 0) { // 성공 => 공지사항 목록페이지 alert(공지사항이 성공적으로 삭제됐습니다.)
			// list.no 는 이미 본 화면 => url재요청
			// (한줄로)
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항 삭제 되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.no");
			
		} else { // 실패 => 에러문구 보여지는 에러페이지
			request.setAttribute("errorMsg", "공지사항 삭제에 실패했습니다.");
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
