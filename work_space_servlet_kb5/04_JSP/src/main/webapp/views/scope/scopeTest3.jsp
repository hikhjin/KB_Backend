<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Scope 간 생명주기 실습 - forward로 이동됨(scopeTest3.jsp) </h1>
	- pageContext가 초기화되고 나머지는 유지 <br>
	
	
	application : <%=application.getAttribute("name") %> <br>
	session : <%=session.getAttribute("name") %> <br>
	request : <%=request.getAttribute("name") %> <br>
	pageContext : <%=pageContext.getAttribute("name") %> <br>
	<br>
	Cookie(번외) : <%=request.getCookies()[0].getValue() %> <br>
	
</body>
</html>