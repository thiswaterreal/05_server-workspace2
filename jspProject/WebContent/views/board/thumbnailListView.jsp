<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
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
    
    .thumbnail:hover {
    	cursor: pointer;
    	opacity: 0.7;
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
			<% for(Board b : list) { %>
	            <!-- 썸네일 한개 -->
	            <div class="thumbnail" align="center">
	            	<!-- hidden : 내가클릭한게시글글번호 가져오기위해 (p태그 안에 있는 boardNo은 가져오기 힘들어) -->
	            	<input type="hidden" value="<%= b.getBoardNo()%>">
	                <img src="<%= contextPath %>/<%= b.getTitleImg() %>" width="200" height="150">
	                <p>
	                    No.<%= b.getBoardNo() %> <%= b.getBoardTitle() %> <br>
	                    조회수 : <%= b.getCount() %>
	                </p>
	            </div>
            <% } %>
            
        </div>

    </div>
    
    <script>
    	$(function() {
    		$(".thumbnail").click(function(){
    			//location.href = "/jsp/detail.th?bno=내가클릭한게시글글번호";
    			location.href = "<%=contextPath%>/detail.th?bno=" + $(this).children("input").val();
    		})
    	})
    </script>

</body>
</html>