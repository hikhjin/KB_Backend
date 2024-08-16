<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>Hello DI(XML) world!</h1>
<br>

<h3>1. XML기반 Bean으로 생성된 User 출력</h3>
<div>user1 : ${user1.name}, ${user1.age}, ${user1.pet.name}</div>
<div>user2 : ${user2.name}, ${user2.age}, ${user2.pet.name}</div>
<div>testUser3 : ${testUser3.name}, ${testUser3.age}, ${testUser3.pet.name}</div>
<div>cat : ${cat.name}, ${cat.age}</div>


<h3>2. properties로 부터 값 읽어오기</h3>
<div>TestBean : ${valueTest}, ${testBean.name}, ${testBean.value}, ${testBean.driver}, ${testBean.url}</div>


<h3>3. xml로 생성한 dataSource 값들 가져오기</h3>
<div>className : ${className}, ${userName}</div>

</body>
</html>