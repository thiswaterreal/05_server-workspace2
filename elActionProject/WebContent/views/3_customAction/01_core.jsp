<%@page import="com.kh.model.vo.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Core Library</h1>

	<h3>1. 변수(속성 == attribute)</h3>
	<pre>
	* 변수 선언과 동시에 초기화 (c:set var="" value="" [scope=""])
	- 변수 선언하고 초기값을 대입해두는 기능을 제공
	- 해당 변수를 어떤 scope에 담아둘껀지 지정 가능함 (생략시, 기본적으로 pageScope에 담김)
		=> 즉, 해당 scope에 setAttribute 를 통해서 key-value 형태로 데이터를 담아 놓는 것이라고 생각하면 됨
		=> c:set 으로 선언된 변수는 EL로 접근해서 사용 가능 (스크립팅 원소로는 접근 불가!)	
	- 변수 타입을 별도로 지정하지 않음 (알아서 만들어짐)
	- 초기값을 반드시 지정해둬야 됨!
	</pre>

	<c:set var="num1" value="10"></c:set>			<!-- pageContext.setAttribute("num1", "10") -->
	<c:set var="num2" value="20" scope="request"/>	<!-- request.setAttribute("num2", "20") -->

	num1의 변수값 : ${ num1 } <br>
	num2의 변수값 : ${ num2 } <br>

	<c:set var="result" value="${ num1 + num2 }" scope="session"/>
	
	result 변수값 : ${ result } <br><br>
	
	${ pageScope.num1 } 같아요 ${ num1 } <br>
	${ requestScope.num2 } 같아요 ${ num2 } <br>
	${ sessionScope.result } 같아요 ${ result } <br><br>
	${ requestScope.result } : 잘못쓰면 아무것도 안나옴 <br>
	
	<!-- value 속성 대신에 시작태그와 종료태그 사이에 초기값 지정 가능 -->
	<c:set var="result" scope="request">9999(value길어질 땐 여기에 씀)</c:set>
	
	${ requestScope.result } <br>
	
	<hr>
	
	<pre>
	* 변수 삭제 (c:remove var="제거하고자하는변수명" [scope=""])
	- 해당 scope 영역에서 해당 변수를 찾아서 제거하는 태그
	- scope 지정 생략시, 모든 scope에서 해당 변수 다 찾아서 제거함
		=> 즉, 해당 scope에 .removeAttribute 를 통해 제거하는 것이라고 생각하면 됨
	</pre>
	
	0) 삭제 전 result : ${ result } <br><br>
	
	1) 특정 scope 지정해서 삭제 <br>
	<c:remove var="result" scope="request"></c:remove>
	request 에서의 삭제 후 result : ${ result } <br><br>
	
	2) 모든 scope 에서 삭제 <br>
	<c:remove var="result"/>
	모든 scope에서의 삭제 후 result : ${ result }
	
	<hr>
	
	<pre>
	* 변수(데이터) 출력 (c:out value="출력하고자하는값" [default="기본값"] [escapeXml="true|false"])
	- 데이터를 출력하고자 할 때 사용하는 태그
	</pre>
	
	<c:out value="${ result }"></c:out>
	<c:out value="${ result }" default="없음"/> <br><br>
	
	<c:set var="outTest" value="<b>출력테스트</b>"/>
	
	그냥 : ${ outTest } <br>
	<c:out value="${ outTest }"/><br> 					<!-- escapeXml 생략시, 기본값이 true == 태그 해석 안됨(문자열로 취급) --> 
	<c:out value="${ outTest }" escapeXml="false"/><br>
	
	<hr>
	
	
	
	
	<h3>2. 조건문 - if (c:if test="조건식")</h3>
	
	<pre>
	- JAVA의 if문과 비슷한 역할을 하는 태그
	- 조건식은 test속성에 작성 (단, 조건을 EL구문으로 기술해야됨)
	</pre>
	
	<%-- 
	<% if(num1 > num2) { %>
		어쩌고저쩌고
	<% } %>
	--%>
	
	<c:if test="${ num1 gt num2 }">
		<b>num1이 num2보다 큽니다.</b>
	</c:if>
	
	<c:if test="${ num1 le num2 }">
		<b>num1이 num2보다 작거나 같습니다.</b>
	</c:if>
	
	<br>
	
	<c:set var="str" value="안녕하세요"/>
	<%--   
	<% if(str.equals("안녕하세요")) { %>
		어쩌구저쩌구
	<% } %>
	--%>
	
	<c:if test="${ str eq '안녕하세요' }">
		<h4>Hello World</h4>
	</c:if>
	
	<c:if test='${ str ne "안녕하세요" }'>
		<h4>Bye World</h4>
	</c:if>
	
	
	
	
	<h3>3. 조건문 - Choose (c:choose, c:when, c:otherwise)</h3>
	<pre>
	- JAVA의 if-else, if-else if문과 비슷한 역할을 하는 태그
	- 각 조건들을 c:choose의 하위요소로 c:when 을 통해서 작성 (else문 역할 == c:otherwise)
	</pre>
	<%-- 
	<% if(num1 > 20) { %>
	
	<% }else if(num1 >= 10) { %>
		
	<% }else { %>
		
	<% } %>
	--%>
	
	<c:choose>
		<c:when test="${ num1 gt 20 }">
			<!-- 주석은 이곳에(c:choose 와 c:when 사이에 쓰면 에러남) -->
			<b>처음뵙겠습니다.</b>
		</c:when>
		<c:when test="${ num1 ge 10 }">
			<b>다시 만나서 반갑습니다.</b>
		</c:when>
		<c:otherwise>
			<b>안녕하세요.</b>
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	
	
	
	<h3>4. 반복문 - forEach</h3>
	<pre>
	- for loop문 : (c:forEach var="변수명" begin="초기값" end="끝값" [step="반복시증가값"]) 											 / (기존)for(int i=0; i<5; i++){}
	- 향상된 for문 : (c:forEach var="변수명" items="순차적으로 접근하고자하는 객체(배열|컬렉션)" [varStatus="현재 접근된 요소의 상태값을 보관할 변수명"]) / (기존) for(변수 : 배열Array|컬렉션ArrayList){}

	var 속성으로 선언된 변수에 접근하고자 할 때는 반드시 EL 구문으로 접근해야함
	</pre>
	<%-- 
	<% for(int i=1; i<=10; i+=2) { %>
	
	<% } %>
	--%>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		반복문 확인 : ${ i } <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="6">
		<h${ i }>태그안에서도 적용 가능 </h${ i }>
	</c:forEach>
	
	
	<c:set var="colors">
		red, yellow, green, pink
	</c:set>
	
	colors 변수값 : ${ colors } <br>
	
	<ul>
		<c:forEach var="c" items="${ colors }">
			<li style="color:${c}">${ c }</li>
		</c:forEach>
	</ul>
	
	
	<%	
		// 테스트 위해 임의로 배열 하나 맹글기 (사실 servlet에서 받아온 배열 뿌리기)
		// 객체배열
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(new Person("차은우", 20, "남자"));
		list.add(new Person("주지훈", 40, "남자"));
		list.add(new Person("카리나", 15, "여자"));
	%>
	
	<c:set var="pList" value="<%= list %>" scope="request" /> 	<!--(원래라면) request.setAttribute("pList", list); -->
	
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody>
			<%-- 
			<% if(pList.isEmpty()) { %>
			
			<% }else { %>
			
				<% for(Person p : pList) { %>
				
				<% } %>
			
			<% } %>
			--%>
			
			<c:choose>
				<c:when test="${ empty pList }">
					<tr>
						<td colspan="3">조회된 사람이 없습니다.</td>
					</tr>				
				</c:when>
				<c:otherwise>
					<c:forEach var="p" items="${ pList }" varStatus="status">
						<tr>
							<td>${ status.count }</td>	<!-- status.index : 0부터 시작 | status.count : 1부터 시작 -->
							<td>${ p.name }</td>
							<td>${ p.age }</td>
							<td>${ p.gender }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		</tbody>
	</table>
	
	<br>
	
	
	
	
	<h3>5. 반복문 - forTokens</h3>
	<pre>
	(c:forTokens var="변수명" items="분리시키고자하는문자열" delims="구분자")
	
	- 구분자를 통해서 분리된 각각의 문자열에 순차적으로 접근하면서 반복 수행
	- JAVA split("구분자") 또는 StringTokenizer 비슷한 기능 처리
	</pre>
	
	<c:set var="device" value="컴퓨터, 핸드폰, TV. 에어컨/ 냉장고. 세탁기"/>
	
	<ol>
		<c:forTokens var="d" items="${ device }" delims=",./">
			<li>${ d }</li>
		</c:forTokens>
	</ol>
	
	
	
	
	<h3>6. url, 쿼리스트링 관련 - url, param</h3>	
	<pre>
	- url 경로를 생성하고, 쿼리스트링을 정의해둘 수 있는 태그
	
	c:url var="변수명" value="요청할url"
		c:param name="키값(bno)" value="전달할값(1)" /
		c:param name="키값(bno)" value="전달할값(1)" /	(여러개도 가능)
	/c:url
	</pre>
	
	
	<a href="list.do?cpage=1&num=2">기존방식</a> <br>
	
	
	<c:url var="ttt" value="list.do">
		<c:param name="cpage" value="1"/>
		<c:param name="num" value="2"/>
	</c:url>
	
	<a href=${ ttt }>c:url을 이용한 방식</a>
	
	
	<br><br><br><br><br><br><br><br><br><br>
</body>
</html>