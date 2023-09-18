<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String year = new SimpleDateFormat("yyyy").format(new Date());
	%>
	
	
	Copyright © 1998-<%= year %> KH Information Educational Institute All Right Reserved <br>
	
	
	test : ${ param.test } <br>
	<%--
	
		   ${ param.test }  :  01_include.jsp 에서 값 보내준것
			
		   특징2 : 포함시, include 하는 페이지로 값 전달 가능
		   <jsp:include page="footer.jsp">
		    	<jsp:param value="Bye" name="test"/>
		   </jsp:include>
	
	 --%>

</body>
</html>