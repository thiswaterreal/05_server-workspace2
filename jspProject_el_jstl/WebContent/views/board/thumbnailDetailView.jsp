<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        height: 800px;
        margin: auto;
        margin-top: 50px;
    }
    
    .detail-area td {
        border: 1px solid white;
        text-align: center;
    }
</style>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer">
        <br>
        <h2 align="center">사진게시판 상세보기</h2>
        <br>

        <table class="detail-area" align="center">
            <tr>
                <td width="70">제목</td>
                <td width="600" colspan="3">${ b.boardTitle }</td>
            </tr>
            <tr>
                <td>작성자</td>
                <td>${ b.boardWriter }</td>
                <td>작성일</td>
                <td>${ b.createDate }</td>
            </tr>
            <tr>
                <td>내용</td>
                <td colspan="3">
                    <p style="height: 50px;">
                        ${ b.boardContent }
                    </p>
                </td>
            </tr>
            <tr>
                <td>대표사진</td>
                <td colspan="3">
                								
                    <img src="${ list.get(0).filePath }/${ list.get(0).changeName }" width="500" height="300">
                </td>
            </tr>
            <tr>
                <td>상세사진</td>
                <td colspan="3">
                    <div>
	                    <!-- 대표사진은(0번) 제외하고, 상세사진만(1,2,3번) 나와야하니까 i=1(o)부터 -->
	                    <c:forEach var="i" begin="1" end="${ list.size() - 1 }">
	                		<img src="${ list.get(i).filePath }/${ list.get(i).changeName }" width="200" height="150">
	                    </c:forEach>
	                    
	                    <!-- 이렇게하면 대표+상세 다 들어가짐 (따라서 이렇게 쓰면 안됨)
	                    <c:forEach var="i" items="${ list }">
	                    	<img src="${ i.filePath }/${ i.changeName }" width="200" height="150">
	                    </c:forEach>
	                     -->
	                    
                    </div>
                </td>
                
            </tr>
        </table>
        <br>
        
        <div align="center">
        	<a href="list.th" class="btn btn-sm btn-secondary">목록가기</a>
        </div>
        
    </div>

</body>
</html>