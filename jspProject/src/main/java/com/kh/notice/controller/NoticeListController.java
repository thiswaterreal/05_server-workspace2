package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [1,2) 요청시 전달값] 할 필요 없으니까
		
		// 3) 요청처리 (응답페이지에 필요한 데이터들 조회)
		ArrayList<Notice> list = new NoticeService().selectNoticeList();
		
		// 4) 응답뷰 지정 => 공지사항 목록 페이지 보여주기
		//	  응답뷰 필요한 데이터는 '포워드방식'일시, request attribute 영역에 담기!
		// 	            만약 'url재요청방식'일시, session attribute 영역에 담기!
		
		request.setAttribute("list", list);
		// (이번엔 한줄로 써봄)
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
