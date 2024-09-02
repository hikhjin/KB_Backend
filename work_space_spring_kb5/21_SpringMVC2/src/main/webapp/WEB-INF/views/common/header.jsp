<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${param.title}</title>
	<link rel="stylesheet" href="${path}/resources/css/main.css">
	<script type="text/javascript" src="${path}/resources/js/jquery-3.7.0.js"></script>
</head>
<body>
	<header>
		<h2>${param.title}</h2>
		<div class="login-container">
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="${path}">Home</a></li>
				<li class="board"><a href="${path}/board/list">게시판</a></li>
			</ul>
		</nav>
	</header>
