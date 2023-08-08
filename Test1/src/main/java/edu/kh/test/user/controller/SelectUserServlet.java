package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

/**
 * Servlet implementation class SelectUserServlet
 */

@WebServlet("/test.do")

public class SelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("잘뜨나?");
		//String userNo = request.getParameter("userNo");	getParameter 반환형이 String이라서 이것도 가능하지만, int로 바꾸면?
		
		// 1) 필요한 데이터 뽑기 (db에 필요한 데이터들)
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		// 2) Service 호출(뽑은 데이터 가지고) 및 결과받기
		User user = new UserService().selectUser(userNo);
		// System.out.println("user : " + user);	// user : User [userNo=1, userId=gd_hong, userName=홍길동, age=20]
		
		// 3) 응답화면 결졍
		if(user == null) {	
			// 조회 실패
			request.setAttribute("errorMsg", "조회된 결과가 없습니다");	// ("키값", "어쩌구저쩌구") 들고 지정해둔 forward로 날아감
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/searchFail.jsp");
			view.forward(request, response);
		}else {
			// 조회 성공
			request.setAttribute("user", user);	//("키값", user담김것) user객체하나를 "user"키값에 담고 attribute에 셋팅해둠
			// "user" 속성에 user라는 값을 할당. 이를 통해 서블릿 또는 JSP 페이지에서 "user"라는 키값을 사용하여 user 객체에 접근할 수 있음
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/searchSuccess.jsp");
			// "WEB-INF/views/searchSuccess.jsp" 경로에 있는 JSP 페이지로 전달할 수 있는 RequestDispatcher 객체
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
