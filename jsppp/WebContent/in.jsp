<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form action="/jsppp/login.lo" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="userId"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="userPwd"></td>
				</tr>
				<tr>
					<th colspan = "2">
					<button type="submit">로그인</button>
					</th>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>