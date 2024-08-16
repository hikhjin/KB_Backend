<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jsp:include</h2>
	- 현재 페이지에서 다른 페이지를 포함하고자 할때 사용하는 태그<br>
	- 주로 header, footer를 공통적으로 활용할때 사용 <br>
	
	<h3>1. include 지시어 (JSP 1.0, 정적 include 방식, 기존꺼!!!)</h3>
	 - 부모 jsp 파일이 컴파일 되기 이전에 자식 jsp의 코드가 삽입되서 컴파일이 되는 방식<br>
	   -> 특징 : 부모-자식간의 변수를 공유할수 있다. 컴파일 1번만 된다. <br>
	<hr>
	<%String pName = "parameter"; %>
	<%@ include file="includePage.jsp" %>
	<hr><br>
	
	<h3>2. Action Tag Include 지시어 (JSP 2.0, 동적 include 방식, 앞으로 쓸것!!) ★권장</h3>
	- 부모 JSP 파일이 화면에 출력되는 시점에 자식 jsp 파일이 실행되어 삽입되는 방식 (따로 컴파일 됨) <br>
	  -> 특징 : 변수명이 공유되지 않고 독립적인 공간으로 분리 된다. (에러가 덜 날수있다..?) <br>
	  -> 빠를 것으로 추정된다. (?) <br> 
    - 파라메터를 외부에서 주입 시킬수 있다. 'jsp:param'을 사용<br>
    <hr>
	
	<jsp:include page="includePage.jsp">
		<jsp:param name="pName" value="include 파라메터" />
	</jsp:include>
	<hr><br>
	
    ※ 프로그램 설계 방식에 따라 선호하는 방식을 선택하여 활용하길 권장<br>
	1. 정적 방식 : header를 통해 공통적으로 사용하는 변수를 공유하고 재사용 가능하다.<br>
	2. 동적 방식 : 프로젝트에서 여러사람이 변수 네이밍을 격리 시킬수 있어 안전하게 활용 가능<br>

</body>
</html>