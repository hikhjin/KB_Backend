<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공통 헤더부 입니다.</title>
</head>
<body>
	<% // 로컬 변수 영역
		String userName = "홍길동";
		String loginTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	%>
	
	<header style="background: lightskyblue">
		<h4 align="left"><%=userName %>님의 로그인을 환영합니다. time : <%=loginTime %></h4>
		<h1>My JSP Page</h1>
		<div align="left">
			<a href="#">메뉴1</a>
			<a href="#">메뉴2</a>
			<a href="#">메뉴3</a>
		</div>
	</header>

	
	
	
	