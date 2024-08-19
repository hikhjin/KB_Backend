<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>    

<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>
	
	<p> member1 : ${member1}</p>
	<p> member2 : ${member2}</p>
	
	<a href="${path}/member/index.do">member index</a>
	 
</body>
</html>
