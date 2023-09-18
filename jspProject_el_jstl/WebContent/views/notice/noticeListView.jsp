<%@page import="com.kh.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// NoticeListController에서 .setAttribute 해둔 키-벨류 던진거 받기
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); // object반환형
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer {
        background-color: black;
        color: white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }

    .list-area {
        border: 1px solid white;
        text-align: center;
    }

    .list-area>tbody>tr:hover {
        background-color: gray;
        cursor: pointer;
    }

</style>

</head>
<body>

	<%@ include file = "../common/menubar.jsp" %>

	<div class="outer">
        <br>
        <h2 align="center">공지사항</h2>
    	<br>

        <!-- 현재 로그인한 사용자가 '관리자'일 경우 보여질 div -->
	        <!-- 로그인 o면 session의 loginMember : 해당 사용자 정보 있음
	        but, 로그인 x면 session의 loginMember : null -->
        <% if(loginMember != null && loginMember.getUserId().equals("admin")) { %>
	        <div align="right" style="width: 850px;">
	            <!-- <button class="btn btn-sm btn-secondary">글작성</button> -->
	            <a href="<%= contextPath %>/enrollForm.no" class="btn btn-sm btn-secondary">글작성</a> <!-- 위랑 똑같음 -->
	            <br><br>
	        </div>
        <% } %>

        <table class="list-area" align="center">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="400">글제목</th>
                    <th width="100">작성자</th>
                    <th>조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- case1. 공지글이 없을 경우 (ArrayList 텅빈경우)-->
                <% if(list.isEmpty()) { %>
	                <tr>
	                    <td colspan="5">존재하는 공지사항이 없습니다.</td>
	                </tr>
                	
                <% } else {%>
	                 <!-- case2. 공지글이 있을 경우 -->
	                 <!-- *** n.getNoticeWriter() vo에 적어뒀던 껍데기명으로!! (알맹이는 userId) *** -->
	                 <% for(Notice n :list) {   // n : notice객체 하나, 하나, ..돌면서 예쁘게 찍어 %>
		                 <tr>
		                    <td><%= n.getNoticeNo() %></td>
		                    <td><%= n.getNoticeTitle() %></td>
		                    <td><%= n.getNoticeWriter() %></td>
		                    <td><%= n.getCount() %></td>
		                    <td><%= n.getCreateDate() %></td>
		                 </tr>
	                 <% } %>
                 <% } %>
                 
                 <!-- list : 반복할 컬렉션 -->
                 <!-- Notice : 컬렉션 내 요소의 타입 (n의 타입) -->
                 <!-- 루프가 실행될 때마다 "n"은 "list"에 있는 각 요소를 순서대로 나타냄 (n : 해당 행 => 제목, 작성자, 등록일, ..) -->
                 <!-- n.어쩌구저쩌구 : 해당 행 속 컬럼에 접근하는 것 -->
	      	 
            </tbody>
        </table>
    </div>
    

	<script>
		$(function(){
			$(".list-area>tbody>tr").click(function() {
				
				const num = $(this).children().eq(0).text();
				//내가누른tr.자식들중.첫번째자식의.값(==글번호)
				//console.log(num);
				
				// 요청할url?키=벨류$키=벨류
				// 요청시 전달값 (키=벨류) <= 쿼리스트링
				
				// http://localhost:8001/jsp/detail.no?num=2
				// http://localhost:8001/jsp/detail.no?num=클릭한글번호
				location.href ='<%= contextPath %>/detail.no?num='+num;
				// 이러면 자동 키값 "num" 으로 해당 값이 controller로 넘어감
			})
			
		})
	</script>



</body>
</html>