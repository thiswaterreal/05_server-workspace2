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
        height: 700px;
        margin: auto;
        margin-top: 50px;
    }

    #enroll-form input, #enroll-form textarea {
        width: 100%;
        box-sizing: border-box;
    }

</style>

</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer">
        <br>
        <h2 align="center">사진게시판 작성하기</h2>
        <br>

        <form action="insert.th" id="enroll-form" method="post" enctype="multipart/form-data">
			<!-- *** hidden : userNo == BOARD_WRITER *** -->
			<input type="hidden" name="userNo" value="${ loginMember.userNo }"/>
			
            <table align="center">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3"><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"><textarea name="content" rows="5" style="resize: none;" required></textarea></td>
                </tr>
                <tr>
                    <th>대표이미지</th>
                    <td colspan="3" align="center">
                        <img id="titleImg" width="250" height="170" onclick="chooseFile(1);">
                    </td>
                </tr>
                <tr>
                    <th>상세이미지</th>
                    <td><img id="contentImg1" width="150" height="120" onclick="chooseFile(2);"></td>
                    <td><img id="contentImg2" width="150" height="120" onclick="chooseFile(3);"></td>
                    <td><img id="contentImg3" width="150" height="120" onclick="chooseFile(4);"></td>
                </tr>
            </table>

            <div id="file-area" style="display:none;">
            	<!-- onchange : 파일이 들어가고 빠져나가고 -->
                <input type="file" name="file1" id="file1" onchange="loadImg(this, 1);" required>
                <input type="file" name="file2" id="file2" onchange="loadImg(this, 2);">
                <input type="file" name="file3" id="file3" onchange="loadImg(this, 3);">
                <input type="file" name="file4" id="file4" onchange="loadImg(this, 4);">
            </div>
            
            <script>
            	function chooseFile(num) {
            		$("#file" + num).click();
            		// 해당 이미지(1,2,3,4) 누름 (사실 그 input'파일선택'이 눌리는것임)
            	}
            	
            	function loadImg(inputFile, num) {
            		// 예:(this, 1)
            		// inputFile : (==this)현재 변화가 생긴 input type = 그 "file" 요소 객체
            		// num : 몇번째 input 요소인지 확인 후 해당 그 영역에 미리보기 하기 위해 전달받는 숫자
            		
            		// 선택된 파일이 있다면 inputFile.files[0]에 선택된 파일이 담겨 있음
            					 //=> inputFile.files.length 또한 1이 될것임
            		if(inputFile.files.length == 1) { // 1 반환했다는 것은 파일이 선택된 경우라는 것 => 파일 읽어들여서 '미리보기'
            			
            			// 파일을 읽어들일 FileReader 객체 생성
            			const reader = new FileReader();
            			
            			// 파일을 읽어들이는 메소드 호출
            			// 해당 파일을 읽어들이는 순간, inputFile.files[0]에 담긴 이 파일만의 '고유한 url'(겁나긴거) 부여해주는 코드
            			reader.readAsDataURL(inputFile.files[0]);
            			
            			// 파일 읽어들이기가 완료(onload) 됐을 때, 실행할 함수 정의해두기
            			reader.onload = function(e) {
            				// (event)는 기본적으로 항상 있는 존재.. 우린 부여받은 '고유한 url' 필요하니까 e로 받고, (script에서 매개변수의 자료형은 필요x)
            				// e.target.result == 읽어들인 파일의 '고유한 url'(겁나긴거) 들어있음
            				
            				switch(num) {
            					case 1: $("#titleImg").attr("src", e.target.result); break;
            					case 2: $("#contentImg1").attr("src", e.target.result); break;
            					case 3: $("#contentImg2").attr("src", e.target.result); break;
            					case 4: $("#contentImg3").attr("src", e.target.result); break;
            				}
            				// 총 설명 : 8/22 (2:42)
            			}
            			
            		}else { // 선택된 파일이 취소된 경우(뺌) => 미리보기 한것도 사라지도록
            			switch(num) {
	    					case 1: $("#titleImg").attr("src", null); break;
	    					case 2: $("#contentImg1").attr("src", null); break;
	    					case 3: $("#contentImg2").attr("src", null); break;
	    					case 4: $("#contentImg3").attr("src", null); break;
    					}
            		}		 
            	}
            
            </script>

            <br>

            <div align="center">
                <button type="submit">등록하기</button>
            </div>

        </form>
    </div>


</body>
</html>