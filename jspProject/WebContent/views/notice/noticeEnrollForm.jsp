<%@page import="com.kh.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    } /* 동일한 내용이라서 쓰기 귀찮으면 메뉴바에 추가하면 됨 */

    /*#enroll-form table {border: 1px solid white;}*/
    #enroll-form input, #enroll-form textarea {
        width: 100%;
        box-sizing: border-box;
    }
</style>

</head>
<body>

	<%@ include file = "../common/menubar.jsp" %>
	
    <div class="outer" align="center">
        <br>
        <h2>공지사항 작성하기</h2>
        <br>

        <form action="<%= contextPath %>/insert.no" id="enroll-form" method="post">
			
			<!-- 방법1) *** hidden 으로 userNo 가져가자 *** -->
			<input type="hidden" name="userNo" value="<%= loginMember.getUserNo() %>" >
			
            <table>
                <tr>
                    <th width="50">제목</th>
                    <td width="450"><input type="text" name="title" required></td>
                </tr>

                <tr>
                    <th>내용</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="content" rows="10" style="resize: none;" required></textarea>
                    </td>
                </tr>
            </table>

            <br><br>

            <div>
                <button type="submit">등록하기</button>
                <button type="reset">초기화</button>
                <button type="button" onclick="history.back();">뒤로가기</button>
            </div>

        </form>

    </div>


</body>
</html>