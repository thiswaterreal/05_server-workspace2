<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.kh.member.model.vo.Member"%>

<% 
	String contextPath = request.getContextPath();	// contextPath == /jsp	// root

	Member loginMember = (Member)session.getAttribute("loginMember");
	// 회원번호, 아이디, 비번, 이름, 폰번호, 이메일, 주소, 취미, 등록일, 수정일, 회원상태
	// 로그인 시도 전 menubar.jsp 로딩시 : null
	// 로그인 성공 후 menubar.jsp 로딩시 : 로그인 성공한 회원의 정보가 담겨있는 Member 객체
	
	String alertMsg = (String)session.getAttribute("alertMsg"); // Object반환형. 형변환필수
	// 서비스 요청 전(예)로그인 전) menubar.jsp 로딩시 : null
	// 서비스 성공 후(예)로그인 후) menubar.jsp 로딩시 : alert로 띄워줄 메시지 문구
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .login-area>* {float: right;}
    .login-area a {
        text-decoration: none;
        color: black;
        font-size: 12px;
    }
    .nav-area {background-color: black;}
    .menu {
        display: table-cell;
        width: 150px;
        height: 50px;
    }
    .menu a {
        text-decoration: none;
        color: white;
        font-size: 20px;
        font-weight: bold;

        /*3:47*/
        display: block;
        width: 100%;
        height: 100%;
        line-height: 50px; 
    }
.menu a:hover {
    background-color: darkgray;
}

</style>

<!-- 부트스트랩 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


</head>
<body>

	<!-- index.jsp 에서 include로 연결했기때문에 여기가 메인 페이지 -->
	
	
	<!-- 알람창 빵! 세션 -->
	<!-- alertMsg 이거 쓸라면 맨위에서 변수에 담아두고 사용!! -->
	<% if(alertMsg != null) { %> <!-- "alertMsg"키값으로 한 벨류값이 있을 때 (알람띄울게 있을 때) -->
		
		<script>
			alert("<%= alertMsg %>");
		</script>
		<!-- 어떤 알람창이든 한번 띄워줬으면 계속 뜨지 않도록 세션 지우기 -->
		<% session.removeAttribute("alertMsg"); %>
		
	<% } %>
	
	
	
    <h1 align="center">Welcome Sujin World</h1>

    <div class="login-area">

		<% if(loginMember == null) { %>
		
	        <!-- case1. 로그인 전 -->
	        <!--<form action="/jsp/login.me" method="post">-->
	        <form action="<%= contextPath %>/login.me" method="post">
	            <table>
	                <tr>
	                    <th>아이디 </th>
	                    <td><input type="text" name="userId" required></td>  <!-- name 꼭 필요 db로 값 전달해야하니까!! -->
	                </tr>
	                <tr>
	                    <th>비밀번호 </th>
	                    <td><input type="password" name="userPwd" required></td>
	                </tr>
	                <tr>
	                    <th colspan="2">
	                        <button type="submit">로그인</button>
	                        <button type="button" onclick="enrollPage();">회원가입</button>
	                    </th>
	                </tr>
	            </table>
	            <script>
	            	function enrollPage() {	<!-- 회원가입 버튼 누르면 -->
	            		// location.href = "<%= contextPath%>/views/member/memberEnrollForm.jsp";
	            		// http://localhost:8001/jsp/views/member/memberEnrollForm.jsp
	            		// 웹 애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약. 따라서 이거 안쓰고
	            		
	            		// 단순한 페이지 요청도 servlet 호출해서 servlet 거쳐갈 것! (즉, url에는 서블릿 매핑값만 노출)
	            		// http://localhost:8001/jsp/enrollForm.me
	            		location.href = "<%= contextPath%>/enrollForm.me";
	            		
	            	}
	            
	            </script>
	            
	        </form>
        <% }else { %>
			
			<!-- case2. 로그인 후 -->
	        <div>
	            <b><%= loginMember.getUserName() %>님</b>의 방문을 환영합니다. <br><br>
	            <div align="center">
	                <a href="<%= contextPath %>/myPage.me">마이페이지</a>
	                <a href="<%= contextPath %>/logout.me">로그아웃</a>
	            </div>
	        </div>
	    <% } %>
	
	        </div>
	        
	        <br clear="both">   <!--float에 영향받고있음 따라서 다음줄로 가도록-->
	        <br>
	
	        <div class="nav-area" align="center">
	            <div class="menu"><a href="<%= contextPath %>">HOME</a></div>
	            <div class="menu"><a href="<%= contextPath %>/list.no">공지사항</a></div>
	            <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1">일반게시판</a></div>
	            <div class="menu"><a href="<%= contextPath %>/list.th">사진게시판</a></div>
	        </div>

		




</body>
</html>