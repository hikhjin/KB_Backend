<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Scope 간 생명주기 실습 - scopeTest2.jsp, 사용자에게 안보여질 예정</h1>
	
	<%
		application.setAttribute("name", "홍길동"); 	// 제일 오래 삼
		session.setAttribute("name", "최길동"); 		// 세션이 해지될때 까지
		request.setAttribute("name", "박길동");		// 다음 페이지 전달까지 유효, 단 forward일 경우
		pageContext.setAttribute("name", "김길동"); 	// 현재 페이지에서만 유효
		response.addCookie(new Cookie("name", "왕길동")); // 페이지가 닫혀도 유효
		
		// forward로 넘기는 방법 2가지
		pageContext.forward("scopeTest3.jsp");
//		request.getRequestDispatcher("scopeTest3.jsp").forward(request, response);
		
	%>
	
</body>
</html>