package com.kh.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userNo = Integer.parseInt(request.getParameter("no"));
	
		// Member m = new MemberService().selectMember(userNo);
		// db갔다왔다 가정하고..
		// 위의 Member 객체에 각 필드에 조회된 데이터(회원정보)들이 담겨있을것임
		Member m = new Member(1, "박철수", 30, "남"); // 사실 지금 안갔다 왔으니까 조회된 데이터가 다음과 같다는 가정하에 설정해둠
		
		// response.getWriter().print(m);  // vo객체를 곧바로 응답시 .toString() 의 문자열이 응답됨 // 성공란 success:function(result) m==result로 보냄
		
		// JSONObject {key:value, key:value}에 직접 담기
		/*
		JSONObject jObj = new JSONObject();		// {}
		jObj.put("userNo", m.getUserNo());  	// {userNo:1} 						  "userNo" 키값은 내맘대로 셋팅
		jObj.put("userName", m.getUserName());	// {userNo:1, userName:"박철수"}
		jObj.put("age", m.getAge());			// {userNo:1, userName:"박철수", age:30}
		jObj.put("gender", m.getGender());		// {userNo:1, userName:"박철수", age:30, gender:"남"}
	
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
		*/
		
		// 더 간단한 방법 : 위 과정을 알아서 해주는 GSON 라이브러리 사용
		// GSON : Google JSON
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson(); // Gson객체.toJson(응답할 자바객체, 응답할 스트림);
		gson.toJson(m, response.getWriter());
		//gson.toJson(응답할자바객체, 응답할스트림);
		
		// Gson을 이용해서 vo객체 하나만 응답시, JSONObject {key:value, key:value, ..} 형태로 만들어서 응답
		// (객체는 인덱스번호가 없으니까 키값이 필요)
		// 이때 key는 해당 vo객체의 필드명으로 알아서 셋팅해줌
		
		//	 자바배열 또는 ArrayList로 응답시, JSONArray [value, value, ..] 형태로 만들어서 응답
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
