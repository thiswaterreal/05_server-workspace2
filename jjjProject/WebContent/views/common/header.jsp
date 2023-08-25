<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>header</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <style>
        
        #header{width: 1400px; height: 168px; margin: auto;}
        #navigator{width: 1400px; height: 72px; margin: auto;}
        
        /*-------------------------------------*/
        
        #header{
            /* border: 2px solid blue; */
            /* background-color: aliceblue; */
            box-sizing: border-box;
            
        }
        #navigator{ /*네비바 영역 관련*/
            /* border: 2px solid yellowgreen; */
            box-sizing: border-box;
            box-sizing: border-box;
            height: 6%;
            background-color: rgb(236, 236, 236);
        }

        #header>div{
            border: 1px solid yellow;
            height: 100%;
            float: left;
            box-sizing: border-box;
        }
        
        /*---------------------------------*/
        #header_1{
            width: 30%;
            position: relative;
        }

        #header_1 img{
            max-width: 100%;
            position: absolute;
            top: 30px;
            bottom: 50px;
            left: 10px;
        }
        /*---------------------------------*/
        #header_2{
            width: 30%;
            position: relative;
        }

        #search {
            width: 400px;
            position: absolute;
            top: 60px;
            left: 30px;
            border: 1px solid black;
        }
        #search div{float: left;}

        #search_btn{margin-left: 5px;}

        select{
            border-color: lightgray;
            border-width: 2px;
            font-size: 14px;
        }

        /*---------------------------------*/
        #header_3{
            width: 20%;
            position: relative;
           
        }
        #weather{
            /* border: 3px solid purple; */
            margin-top: 40px;
            /* line-height: 70px; */
            /* width: 135px; */
            /* height: 100px; */
            margin-left: 50px;
        }

        #weather>a{text-decoration: none;}

        /*---------------------------------*/
        #header_4{
            width: 20%;
            position: relative;
        }
        
        /*-----------로그인 전-----------*/
        #header_4 a{
            text-decoration: none;
            font-size: large;
            font-weight: 600;
        }
        #before_login{
            position: absolute;
            top: 70px;
            left: 75px;
        }

        /*-----------로그인 후-----------*/
        
        #user_info{
            display: inline-block;
            margin-top: 50px;
            margin-left: 5px;
        }
        #user_info img{
            width: 53px;
            display: inline-block;
            margin: 0;
        }
        #user_info b{
            font-size: 14px;
            vertical-align: -30%;
            display: inline-block;
        }

        #usermenu{ /*햄버거버튼*/
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: transparent; /* 뒷 배경을 부모와 같게 맞춰줌 */
            border:none;
            margin-top: 1rem;
            float: right;
            margin-top: 55px;
        }

        #usermenu>img{ /*햄버거사진*/
            width: 45px;
        }

        #usermenu_page{
            width: 400px; 
            height: 600px;
            position: absolute;
            display: none;
            /* margin-right: 500px; */
            border: 0.1px solid lightgray;
            border-radius: 1rem;
            background-color: #ededed;
            z-index: 9999;
            right: 1px;
            top: 120px;
            box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);
        }
        
        /*---------------------------------*/

        /* #navigator li{
            border: 1px solid;
        } */

        #navigator a{ /*네비바 버튼 요소 관련*/
            text-align: center;
            width: 140px;
            margin: 15px;
        }        

        .btn-light{
            background-color: #fffefe;
            border: 1px #ededed;
        }
        
       
    </style>
</head>
<body>
    
    <div id="header">
        <div id="header_1">
            <!-- 로고 -->
            <a href="#"><img src="../resources/logo_blue_subwhere.png" alt="logo"></a>
        </div>
        <div id="header_2">
            <!-- 역 검색창 -->
            <div id="search">
                <div id="search_station">
                    <form action="">
                        <select onchange="categoryChange(this)" style="height: 36px; width: 170px;">
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
                        
                        <select id="station" style="height: 36px; width: 150px;">
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
            
                <div id="search_btn">
                    <input type="submit" value="검색" class="btn btn-primary" style="border: 1px;">
                </div>

            </div>
            
            
        </div>
        <div id="header_3">
            <div id="weather">
                <!-- Weather widget by https://meteodays.com -->
                <a id="ms-informer-link-1c0f2690c0b614035a8e37b7b9f11ada" class="ms-informer-link" href="https://meteodays.com/ko/weather/overview/seoul">서울날씨</a>
                <script class="ms-informer-script" src="https://meteodays.com/ko/informer/script/1c0f2690c0b614035a8e37b7b9f11ada"></script>
                <!-- End -->
            </div>
        </div>

        <div id="header_4">
            <!-- case1. 로그인 전 : 로그인 -->
            <!-- <div id="before_login"><a href="#" onclick="로그인창띄우기">Login</a></div> -->

            <!-- case2. 로그인 후 : 프사+닉네임 -->
            <div id="after_login">

                <div id="user_info">
                    <img src="../resources/profile_img_nocamera.png" alt="profile">
                    <b>여기닉네임</b>
                </div>
                
                
                <!-- 메뉴바 햄버거 -->
                <button id="usermenu" onclick="openUserMenu()"><img src="../resources/hamburger_btn.png"></button>
                
                    <div id="usermenu_page">
                        <table border="1" align="center">
                            
                            <h3 align="center">로그인</h3>

                            <tr>
                                <th><input type="text"></th>
                            </tr>
                            <tr>
                                <th><input type="password"></th>
                            </tr>
                            <tr>
                                <th><input type="submit" value="로그인"></th>
                            </tr>
                            <tr>
                                <th>ID 찾기 | 비밀번호 찾기 | 회원가입</th>
                            </tr>
                            
                        </table>
                    </div>
                
                
                <script>
                    function openUserMenu(){

                        if(document.getElementById('usermenu_page').style.display==='block'){
                            document.getElementById('usermenu_page').style.display='none';
                        }else{
                            document.getElementById('usermenu_page').style.display='block';
                        }
                        
                    }
                </script>
                

            </div>

            </div>

        </div>


    </div>
    <div id="navigator">
        <!-- navi -->
        <ul class="nav nav-pills justify-content-center ">
            <li class="nav-item">
                <a class="btn btn-light" href="#">홈</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" href="#">여행지 추천</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" href="#">여행 코스 추천</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" href="#">여행 후기</a>
            </li>
            <li class="nav-item">
                  <a class="btn btn-light" href="#">공지사항</a>
            </li>
        </ul>
    </div>
    
    
    
</body>
</html>