<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Scope 간 생명주기 실습 - a태그로 페이지 이동됨(scopeTest.jsp) </h1>
	- 결과 : redirect와 같은 효과로 request, pageContext에 데이터가 사라짐 <br>

	application : <%=application.getAttribute("name") %> <br>
	session : <%=session.getAttribute("name") %> <br>
	request : <%=request.getAttribute("name") %> <br>
	pageContext : <%=pageContext.getAttribute("name") %> <br>
	Cookie : <%=request.getCookies()[0].getValue() %> <br>

	
	<a href="scopeTest2.jsp">다음 페이지로 이동</a>

</body>
</html>