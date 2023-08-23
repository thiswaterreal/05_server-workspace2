package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqAjaxController1
 */
@WebServlet("/jqAjax1.do")
public class JqAjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String str = request.getParameter("input");
		// System.out.println("요청시 전달값 : " + str);
		
		// 요청 처리 다 했다는 가정하에 응답할 데이터
		String responseData = "입력된 값 - " + str + ", 길이 - " + str.length();
		
		// 응답데이터 돌려주기 (인코딩 => 돌려주기) success:function(result){} 에 꽂힘!!  (8/23 3:20)
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
