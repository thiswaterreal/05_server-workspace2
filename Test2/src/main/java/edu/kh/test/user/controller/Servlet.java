package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.service.Service;
import edu.kh.test.user.model.vo.User;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/test.dodo")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("됨용?");
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		User j = new Service().searchUser(userNo);
		
		
		
		if(j == null) {
			// 조회실패
			request.setAttribute("error", "조회 불가요~");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/fail.jsp");
			view.forward(request, response);
		}else {
			// 조회성공
			request.setAttribute("user", j);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/success.jsp");
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
