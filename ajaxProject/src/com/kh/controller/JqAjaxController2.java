package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); // 입력받을땐 String형으로 받으니까..
	
		// 요청처리가 다 됐다는 가정하에 '응답할 데이터'
		
		
		// v1. 응답하는 데이터가 => 하나의 문자열로 return
		/*
		String responseData = "이름 : " + name + " | 나이 : " + age;
		
		response.setContentType("text/html; charset=UTF-8"); // '응답할 데이터'에 한글이 있을 경우 인코딩!! 위에 '이름 : ' 이런것처럼
		response.getWriter().print(responseData); // 저 responseData를 보내줄것임. 어디에? 성공란 success:function(a)에 매개변수 a로
		*/
		
		
		// v2. 응답하는 데이터가 => 여러개의 문자열로 return
		/*
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(name);
		response.getWriter().print(age);
		=> success:function의 매개변수에 연이어서 하나의 문자열로 담겨 있음. 데이터 가공 어려움. "차은우20" 
		*/
		
			/*
			 * JSON (JavaScript Object Notation : 자바스크립트 객체 표기법)
			 * - ajax 통신시, 데이터 전송에 자주 사용되는 포맷형식 중 하나
			 * 
			 *  	> [value, value, value]		=> 자바스크립트에서의 배열 객체 => JSONArray 형태
			 *  	> {key:value, key:value}	=> 자바스크립트에서의 일반 객체 => JSONObject 형태
			 *  
			 * - 라이브러리 필요 : (https://code.google.com/archive/p/json-simple/downloads)
			 */
		
		// > JSONArray 형태
		/*
		JSONArray jArr = new JSONArray(); 	// []
		jArr.add(name);						// ["차은우"]
		jArr.add(age);						// ["차은우", 20]
		
		//response.setContentType("text/html; charset=UTF-8"); 		// 이러면 String형이라 삐-
		response.setContentType("application/json; charset=UTF-8"); // JSON 데이터를 응답시에는 요렇게 작성 (이제서야 배열형)
		response.getWriter().print(jArr);							// 성공란 success:function()에 꽂힘
		*/
		
		// > JSONObject 형태
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name);				// {name:"차은우"}
		jObj.put("age", age);				// {name:"차은우", age:20}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
