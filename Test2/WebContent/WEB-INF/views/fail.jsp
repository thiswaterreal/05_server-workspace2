<%@page import="edu.kh.test.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1><%= request.getAttribute("error") %></h1>
	
	<a href="/Test2">메인페이지로 돌아가기</a>

</body>
</html>