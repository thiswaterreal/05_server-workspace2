<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
    .outer {
        background-color: black;
        color: white;
        width: 1000px;
        height: 800px;
        margin: auto;
        margin-top: 50px;
    }

    .list-area {
        width: 760px;
        margin: auto;
    }

    .thumbnail {
        border: 1px solid white;
        width: 220px;
        display: inline-block;
        margin: 14px;
    }
</style>

<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">사진게시판</h2>
        <br>

        <!-- 로그인한 회원만 보여짐 -->
        <% if(loginMember != null) { %>
	        <div style="width: 850px;" align="right">
	            <a href="<%= contextPath %>/enrollForm.th" class="btn btn-sm btn-secondary">글작성</a>
	        </div>
        <% } %>

        <div class="list-area">

            <!-- 썸네일 한개 -->
            <div class="thumbnail" align="center">
                <img src="이미지경로" width="200" height="150">
                <p>
                    No.20 운동가요(게시글제목) <br>
                    조회수 : 500
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="이미지경로" width="200" height="150">
                <p>
                    No.20 운동가요(게시글제목) <br>
                    조회수 : 500
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img src="이미지경로" width="200" height="150">
                <p>
                    No.20 운동가요(게시글제목) <br>
                    조회수 : 500
                </p>
            </div>
            
        </div>

    </div>

</body>
</html>