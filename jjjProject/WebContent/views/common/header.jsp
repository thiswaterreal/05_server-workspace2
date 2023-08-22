<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
     <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 
<title>Insert title here</title>

<style>
    .hd_wrap{
        /* border: 2px solid red; */
        width: 1000px;
        height: 1200px;
        margin: auto;
        /* box-sizing: border-box; */
    }

    .hd_header{
        /* border: 2px solid blue; */
        background-color: aliceblue;
        width: 100%;
        box-sizing: border-box;
        height: 14%;
    }

    /*-------------content틀------------*/
    .hd_body{
        /* border: 1px solid green; */
        width: 100%;
        box-sizing: border-box;
        height: 65%;
    }

    /*-------------------------*/
  
    #hd_navigator{
        /* border: 2px solid yellowgreen; */
        width: 100%;
        box-sizing: border-box;
        height: 6%;
        margin-top: 15px;
        background-color: rgb(236, 236, 236);
    }

    .hd_header>div{
        /* border: 1px solid yellow; */
        height: 100%;
        float: left;
        box-sizing: border-box;
    }

    
    /*---------------------------------*/
    #hd_header_1{
        width: 25%;
        position: relative;
    }

    #hd_header_1 img{
        max-width: 100%;
        position: absolute;
        top: 50px;
        bottom: 50px;
        left: 10px;
    }
    /*---------------------------------*/
    #hd_header_2{
        width: 40%;
        position: relative;
    }

    #hd_search {
        position: absolute;
        top: 60px;
        left: 50px;
    }
    #hd_search div{float: left;}

    #hd_search_btn{margin-left: 5px;}

    select{
        border-color: rgb(198, 198, 198);
        border-width: 2px;
        font-size: 14px;
    }

    /*---------------------------------*/
    #hd_header_3{
        width: 15%;
        position: relative;
    }
    #hd_weather{
        /* border: 3px solid purple; */
        margin-top: 50px;
        /* line-height: 80px; */
        width: 135px;
    }

    #hd_weather>a{text-decoration: none;}

    /*---------------------------------*/
    #hd_header_4{
        width: 20%;
        position: relative;
    }
    
    /*로그인 전*/
    #hd_header_4 a{
        text-decoration: none;
        font-size: large;
        font-weight: 600;
    }
    #hd_before_login{
        position: absolute;
        top: 70px;
        left: 75px;
    }

    /*로그인 후*/
    
    #hd_user_info{
        display: inline-block;
        margin-top: 50px;
        margin-left: 5px;
    }
    #hd_user_info img{
        width: 53px;
        display: inline-block;
        margin: 0;
    }
    #hd_user_info b{
        font-size: 14px;
        vertical-align: -30%;
        display: inline-block;
    }

    #hd_usermenu{
        /* border: 1px solid; */
        float: right;
        margin-top: 60px;
        /* line-height: 165px; */
        margin-right: 5px;
        cursor: pointer;
    }

    #hd_usermenu>img{
        width: 50px;
    }

    /*---------------------------------*/

    /* #user_account a{
        font-size: small;
    } */
    
    /*---------------------------------*/

    #hd_navigator li{
        /* border: 1px solid; */
    }

    #hd_navigator a{
        text-align: center;
        width: 140px;
        margin: 15px;
    }
    </style>
    
</head>
<body>
<div class="hd_wrap">
        <div class="hd_header">

            <div id="hd_header_1">
                <!-- 로고 -->
                <a href="#"><img src="../resources/logo_blue_subwhere.png" alt="logo"></a>

            </div>
            <div id="hd_header_2">
                <!-- 역 검색창 -->
                <div id="hd_search">
                    <div id="hd_search_station">
                        <form action="">
                            <select onchange="categoryChange(this)" style="height: 36px; width: 130px;">
                                <option>호선 번호 선택</option>
                                <option value="line1">1호선</option>
                                <option value="line2">2호선</option>
                                <option value="line3">3호선</option>
                                <option value="line4">4호선</option>
                                <option value="line5">5호선</option>
                                <option value="line6">6호선</option>
                                <option value="line7">7호선</option>
                                <option value="line8">8호선</option>
                                <option value="line9">9호선</option>
            
                            </select>
                            
                            <select id="hd_station" style="height: 36px; width: 110px;">
                            <option>역 명 선택</option>
                            </select>
                            <script>
                            function categoryChange(e) {
                                var station1 = ["서울역", "용산", "시청", "종로 3가", "동대문", "동묘앞", "청량리", "회기", "영등포", "구로", "인천", "부천"];
                                var station2 = ["홍대입구", "합정", "신림", "서울대입구", "사당", "강남", "성수", "왕십리", "잠실"];
                                var station3 = ["안국", "경복궁", "홍제", "종로3가", "을지로3가", "압구정", "신사", "수서"];
                                var station4 = ["삼각지", "명동", "동대문역사공원", "총신대입구(이수)", "혜화", "미아"];
                                var station5 = ["광화문", "여의도", "공덕", "청구", "군자", "천호", "굽은다리"];
                                var station6 = ["응암", "불광", "망원", "이태원", "안암"];
                                var station7 = ["노원", "건대입구", "청담", "논현", "고속터미널", "대림"];
                                var station8 = ["잠실", "천호", "암사", "가락시장"];
                                var station9 = ["등촌", ,"선유도", "흑석", "종합운동장", "석촌", "올림픽공원"];
                                var target = document.getElementById("station");
                            
                                if(e.value == "line1") var d = station1;
                                else if(e.value == "line2") var d = station2;
                                else if(e.value == "line3") var d = station3;
                                else if(e.value == "line4") var d = station4;
                                else if(e.value == "line5") var d = station5;
                                else if(e.value == "line6") var d = station6;
                                else if(e.value == "line7") var d = station7;
                                else if(e.value == "line8") var d = station8;
                                else if(e.value == "line9") var d = station9;
                            
                                target.options.length = 0;
                            
                                for (x in d) {
                                    var opt = document.createElement("option");
                                    opt.value = d[x];
                                    opt.innerHTML = d[x];
                                    target.appendChild(opt);
                                }
                            }
                            </script>
                            
                        </form>
                
                    </div>
                
                    <div id="hd_search_btn">
                        <input type="submit" value="검색" class="hd_btn btn-primary" style="border: 1px;">
                    </div>

                </div>
                
                
            </div>
            <div id="hd_header_3">
                <div id="hd_weather">
                    <!-- Weather widget by https://meteodays.com -->
                    <a id="ms-informer-link-1c0f2690c0b614035a8e37b7b9f11ada" class="ms-informer-link" href="https://meteodays.com/ko/weather/overview/seoul">서울날씨</a>
                    <script class="ms-informer-script" src="https://meteodays.com/ko/informer/script/1c0f2690c0b614035a8e37b7b9f11ada"></script>
                    <!-- End -->
                </div>
            </div>

            <div id="hd_header_4">
                <!-- case1. 로그인 전 : 로그인 -->
                <!-- <div id="before_login"><a href="#" onclick="로그인창띄우기">Login</a></div> -->

                <!-- case2. 로그인 후 : 프사+닉네임 -->
                <div id="hd_after_login">

                    <div id="hd_user_info">
                        <img src="../resources/profile_img.png" alt="profile">
                        <b>여기닉네임</b>
                    </div>
                    
                    <div id="hd_usermenu" onclick="햄버거눌루">
                        <!-- Button to Open the Modal -->
                        <img src="../resources/hamburger_btn.png" data-toggle="modal" data-target="#myModal">
                    </div>

                    <div class="hd_container">
                        
                        <!-- The Modal -->
                        <div class="modal fade" id="myModal">
                            <div class="modal-dialog">
                            <div class="modal-content">
                            
                                <!-- Modal Header -->
                                <div class="modal-header">
                                <h4 class="modal-title">로그인</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                
                                <!-- Modal body -->
                                <div class="modal-body">
                                
                                <form action="#">
                                    <div class="hd_form-group">
                                      <label for="hd_id">아이디:</label>
                                      <input type="id" class="hd_form-control" placeholder="아이디" id="hd_id">
                                    </div>
                                    <div class="hd_form-group">
                                      <label for="hd_pwd">비밀번호:</label>
                                      <input type="password" class="hd_form-control" placeholder="비밀번호" id="hd_pwd">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-primary">로그인</button>
                                </form>

                                <div id="hd_user_accout" style="margin-top: 5px;"></div>
                                    <a href="#" style="font-size: 12px; color: black">ID 찾기</a>
                                    <a href="#" style="font-size: 12px; color: black;"> | 비밀번호 찾기</a>
                                    <a href="#" style="font-size: 12px; color: black;"> | 회원가입</a>
                                </div>
                                
                                
                                <!-- Modal footer -->
                                <div class="hd_modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                                
                            </div>
                            </div>
                        </div>
                        
                    </div>

                </div>

            </div>
            
        </div>
        <div id="hd_navigator">
            <!-- 메뉴바 -->
            <ul class="nav nav-pills justify-content-center ">
                <li class="hd_nav-item">
                    <a class="btn btn-light" href="#">홈</a>
                </li>
                <li class="hd_nav-item">
                    <a class="btn btn-primary" href="#">여행지 추천</a>
                </li>
                <li class="hd_nav-item">
                    <a class="btn btn-primary" href="#">여행 코스 추천</a>
                </li>
                <li class="hd_nav-item">
                    <a class="btn btn-primary" href="#">여행 후기</a>
                </li>
                <li class="hd_nav-item">
                      <a class="btn btn-light" href="#">공지사항</a>
                </li>
            </ul>
        </div>
    <div>

<div>
</body>
</html>