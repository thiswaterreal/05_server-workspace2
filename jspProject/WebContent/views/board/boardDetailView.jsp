<%@page import="com.kh.board.model.vo.Attachment"%>
<%@page import="com.kh.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Board b = (Board)request.getAttribute("b");
	// 글번호, 카테고리명, 제목, 내용, 작성자, 작성일 (selectBoard 결과)
	Attachment at = (Attachment)request.getAttribute("at");
	// 첨부파일이 없다면 null
	// 첨부파일이 있다면 파일번호, 원본명, 수정명, 저장경로 (selectAttachment 결과)
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
        height: auto;
        margin: auto;
        margin-top: 50px;
    }

</style>

</head>
<body>

	<%@ include file = "../common/menubar.jsp" %>
	
    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세보기</h2>
        <br>

        <table id="detail-area" border="1" align="center">
            
            <tr>
                <th width="70">카테고리</th>
                <td width="70"><%= b.getCategory() %></td>
                <th width="70">제목</th>
                <td width="350"><%= b.getBoardTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%= b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 200px;"><%= b.getBoardContent() %></p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                	<% if(at == null) { %>
	                    <!-- case1. 첨부파일이 없을 경우 -->
	                    첨부파일이 없습니다.
                    <% }else { %>
	                    <!-- case2. 첨부파일이 있을 경우 -->
	                    		 <!-- /jsp     /resources/board_upfiles/2023081715314133439.PNG -->
	                    <a download="<%=at.getOriginName()%>" href="<%=contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName()%>"><%= at.getOriginName() %></a>
	                <% } %>
                </td>
            </tr>
        </table>

        <br>

        <div align="center">
            <a href="<%=contextPath%>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>

			<% if(loginMember != null && loginMember.getUserId().equals(b.getBoardWriter())) { %>
	            <!-- 로그인한사용자 == 게시글작성자 일 경우 -->
	            <a href="<%=contextPath%>/updateForm.bo?bno=<%=b.getBoardNo()%>" class="btn btn-sm btn-warning">수정하기</a>
	            <a href="#" class="btn btn-sm btn-danger">삭제하기</a>
        	<% } %>
        </div>

        <br>

        <div id="reply-area">

            <table border="1" align="center">
                
                <thead>
                    <tr>
                        <th>댓글작성</th>
                        <% if(loginMember != null) { %>
	                        <!-- 로그인한 사용자만 보이는 화면 -->
	                        <td>
	                            <textarea id="replyContent" cols="50" rows="3" style="resize: none;"></textarea>
	                        </td>
	                        <td>
	                            <button onclick="insertReply();">댓글등록</button>
	                        </td>
    					<% }else { %>
	                        <!-- 로그인 안한 사용자가 보는 화면 -->
	                        <td>
	                            <textarea id="" cols="50" rows="3" style="resize: none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
	                        </td>
	                        <td>
	                            <button disabled>댓글등록</button>
	                        </td>
                        <% } %>
                    </tr>
                </thead>

                <tbody>
                    <!-- for문 돌려서 얻은 댓글값들 여기에 넣어 -->
                </tbody>

            </table>

			<script>
                
                $(function(){
                    selectReplyList(); // 모든 그림 다 그려지고 selectReplyList() 호출
                })

                
                // *** ajax로 댓글 작성용 ***
                function insertReply(){
                	
                	$.ajax({
                		url:"rinsert.bo",
                		data:{
                				content:$("#replyContent").val(),
                			  	bno:<%=b.getBoardNo()%>
                			  },
                		type:"post",
                		success:function(result){
                			
                			if(result > 0) { // 댓글 작성 성공 => 갱신된 댓글 리스트 조회
                				selectReplyList();
                				// 밑에 짜놓은 댓글조회 호출(재활용)
                				$("#replyContent").val("");
                			}else { // 실패
                				
                			}
                			
                		},
                		error:function(){
                			console.log("댓글작성용 ajax 통신 실패ㅜㅜ");
                		}
                	})
                	
                }
                
                
                // *** ajax로 해당 게시글에 딸린 댓글 목록 조회용 ***
                function selectReplyList(){
                    $.ajax({
                        url:"rlist.bo",
                        data:{bno:<%= b.getBoardNo() %>},
                        success:function(list){ // list == list
							console.log(list);
                        
                        	let result = "";
                        	for(let i=0; i<list.length; i++) {
                        		result += "<tr>"
                        			    + "<td>" + list[i].replyWriter + "</td>"
                        			    + "<td>" + list[i].replyContent + "</td>"
                        			    + "<td>" + list[i].createDate + "</td>"
                        			    + "</tr>";
                        	}
                        	
                        	$("#reply-area tbody").html(result);
                        
                        },
                        error:function(){
                            console.log("댓글 조회용 ajax 통신 실패ㅜㅜ");
                        }
                    })
                }


			</script>


        </div>




    </div>


</body>
</html>