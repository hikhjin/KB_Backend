<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>jsp:forward</h3>
	 - JSP 페이지에서 기존 전달받은 요청과 응답을 다른 JSP로 전달할때 사용<br>
	 - URL이 변경되지 않는 특징을 갖는다. 서블릿 포워드랑 동일 기능<br>
	 ※ JSP에서 JSP 페이지 이동은 적합한 MVC2 패턴 설계가 아님으로 권장하지 않는다! <br>
	 
	 <jsp:forward page="forwardPage.jsp">
	 	<jsp:param value="forward value" name="pName"/>
	 </jsp:forward>
	 
</body>
</html>