<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1><%= request.getAttribute("errorMsg") %></h1>	<!-- 얻("키값") -->
	
	<a href="/Test1">메인페이지로 돌아가기</a>

</body>
</html>