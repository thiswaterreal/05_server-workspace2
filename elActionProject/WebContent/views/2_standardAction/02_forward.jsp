<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- url로는.. http://localhost:8003/ea/views/2_standardAction/02_forward.jsp 이지만 -->
	 
	<h1>여기는 02_forward.jsp 페이지임</h1>
	 
	<jsp:forward page="footer.jsp"></jsp:forward>
	<!-- 해당 페이지 내용 보여주지 않고, 바~~로 footer.jsp 화면을 보여줌 (해당 페이지 숨길 때 사용) -->
	
</body>
</html>