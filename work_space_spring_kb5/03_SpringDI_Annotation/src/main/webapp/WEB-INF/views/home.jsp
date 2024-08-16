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
<h1>Hello DI(Annotation) world!</h1>
<br>

<h3>1. 어노테이션 기반 Bean으로 생성된 User 출력</h3>
<div>user : ${user.name}, ${user.age}, ${user.pet.name}</div>
<div>cat : ${dog.name}, ${dog.age}</div>


<h3>2. properties 값 읽어오기</h3>
<div>TestBean : ${testBean.valueTest}, ${testBean.name}, ${testBean.value}, ${testBean.driver}, ${testBean.url}</div>


<h3>3. 어노테이션으로 생성한 dataSource 값들 가져오기</h3>
<div>className : ${className}, ${userName}</div>

</body>
</html>
