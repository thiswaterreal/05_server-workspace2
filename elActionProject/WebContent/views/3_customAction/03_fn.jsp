<%@page import="com.kh.model.vo.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Functions Library</h1>
	
	
	<%	
		// 테스트 위해 임의로 배열 하나 맹글기 (사실 servlet에서 받아온 배열 뿌리기)
		// 객체배열
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person("차은우", 20, "남자"));
		list.add(new Person("주지훈", 40, "남자"));
		list.add(new Person("카리나", 15, "여자"));
	%>
	<c:set var="pList" value="<%= list %>" scope="request" /> 	<!--(원래라면) request.setAttribute("pList", list); -->
	
	<c:set var="str" value="How are you?"/>
	
	
	
	str : ${ str } <br>
	
	문자열의 길이	: ${ str.length() } 		<br>
	문자열의 길이	: ${ fn:length(str) } 		<br> <!-- arrayList도 제시 가능함 => 리스트의 사이즈 -->
	list배열의 길이	: ${ fn:length(pList) } 	<br> <!-- arrayList도 제시 가능함 => 리스트의 사이즈 -->

	모두 대문자로 출력 : ${ fn:toUpperCase(str) } <br>
	모두 소문자로 출력 : ${ fn:toLowerCase(str) } <br>
	
	are의 시작인덱스 : ${ fn:indexOf(str, "are") } <br>
	
	are => were 변경 : ${ fn:replace(str, "are", "were") } <br>
	
	<c:if test="${ fn:contains(str, 'are') }">
		포함되어있으면 true를 뱉음
	</c:if>

</body>
</html>