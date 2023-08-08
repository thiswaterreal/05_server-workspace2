<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- http://localhost:8001/Test1/ -->
	<!-- 잘뜨나? -->
	
	<h1>회원 정보 검색(회원 번호 검색)</h1>
	<form action="/Test1/test.do" method="get">
		<!-- name(키값) 반드시 줘야함. db에 필요한 값이니까 => localhost:8001/Test1/test.do?userNo=100-->
		<input type="text" name="userNo" placeholder="회원 번호 입력">
		<input type="submit" value="조회">
	</form>

	<br><br>

	<form action="/Test1/login.do" method="get">
		아이디 : <input type="text" name="userId"> <br>
		나이 : <input type="number" name="age"> <br>
		<input type="submit">
	</form>

</body>
</html>