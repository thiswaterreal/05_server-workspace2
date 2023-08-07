<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- index.jsp 에서 include로 연결했기때문에 여기가 메인 페이지 -->
	
    <h1>로그인 성공해보쟝구</h1>

    <div class="login-area">

        <!-- case1. 로그인 전 -->
        <form action="/jsppp/login.lo" method="post">
            <table>
                <tr>
                    <th>아이디 </th>
                    <td><input type="text" name="userId" required></td>
                </tr>
                <tr>
                    <th>비밀번호 </th>
                    <td><input type="password" name="userPwd" required></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <button type="submit">로그인</button>
                        <button type="button">회원가입</button>
                    </th>
                </tr>
            </table>
        </form>
       

</body>
</html>