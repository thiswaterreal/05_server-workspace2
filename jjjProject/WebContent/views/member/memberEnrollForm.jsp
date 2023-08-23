<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- w3schools 부트스트랩 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        div * {box-sizing: border-box;}

        .all {
            margin: auto;
            width: 1000px;
            height: 1500px;
        }

        /* -------------------------profile--------------------------- */
        #file {
            display: none;
        }
        
        #file_btn {
            margin-top: 50px;
            width: 220px;
            height: 220px;
            background-image: url("image/profile.png");
            cursor: pointer;
        }

        /* -------------------------con1--------------------------- */
        #con1 {
            padding-top: 20px;
        }
        
        #con1>p {
            padding-left: 100px;
            color: #007fff;
            font-weight: bold;
        }

        input {
            width: 80%;
            height: 45px;
            border: none;
            background-color: #f7f7f7;
            margin-bottom: 15px;
        }

        input::placeholder {
            color: rgb(161, 161, 161);
        }


        /* -------------------------con2--------------------------- */

        #con2>#btn {
            width: 500px;
            height: 50px;
            background-color: #007fff;
            color: white;
            font-size: 17px;
            line-height: 40px;
            text-decoration: none;
            border: 1px solid lightgray;
            cursor: pointer;
        }

        #con2>#btn:hover {
            background-color: #0077ee;
            color: white;
        }

    </style>

</head>
<body>

	

    <div class="all">
        <form action="<%= contextPath%>/insert.me" method="post">
            <!------------------------profile----------------------------->
            
            <div id="profile" align="center">
                <input type="file" name="file" id="file">
                <label for="file">
                    <div id="file_btn"></div>
                </label>
            </div>
            
    
            
            <!------------------------con1----------------------------->
            <div id="con1" align="center">
                <p align="left">* 모든 입력 사항은 필수 정보입니다!</p>
                
                <input type="text" name="memberName" placeholder="이름"> <br>
                <input type="text" name="memberId" placeholder="아이디"> <br>
                <input type="text" name="nickname" placeholder="닉네임"> <br>
                <input type="password" name="memberPwd" placeholder="비밀번호"> <br>
                <input type="password" name="checkPwd" placeholder="비밀번호 확인"> <br>
                <input type="text" name="phone" placeholder="' - ' 를 포함한 연락처를 입력해주세요."> <br>
                <input type="email" name="email" placeholder="'@' 를 포함한 이메일을 입력해주세요."> <br>
            </div>
    
    
            <!------------------------con2----------------------------->
            <br>
            <div id="con2" align="center">
                <button type="submit" id="btn">가입하기</button>
            </div>
            
         
            
        </form>

    </div>
    <br><br>



</body>
</html>