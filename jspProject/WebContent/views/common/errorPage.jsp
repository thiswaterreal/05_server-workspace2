<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 실패시, 화면 -->
	
	<%@ include file = "menubar.jsp" %>
	
	<br><br>
	
	<h1 align="center" style="color:red;"><%= request.getAttribute("errorMsg") %></h1>
											  <!-- LoginController에서 벨류값 "로그인실패" 을 set해뒀으니까 그걸 띄움 -->
											  <!-- 아까 벨류값이 "회원가입실패"였으니까 그걸 띄움 -->
											  <!-- 따라서, 재활용 가능!! -->
											  
											  <!-- 수쟌스 하위~ -->

</body>
</html>