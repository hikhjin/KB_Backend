<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>리다이텍트 된 페이지</h2>
	<% String id = (String)request.getAttribute("id"); %>
	사용자 id : <%=id%>
</body>
</html>