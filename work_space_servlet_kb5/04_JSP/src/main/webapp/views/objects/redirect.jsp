<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	해당 페이지는 보이지 않는 페이지입니다!!

	<% 
		request.setAttribute("id", "testid12345"); // 다음 페이지로 넘길 데이터 
		
		// redirect로 넘기는 방법 - 위에 데이터가 사라짐
//		response.sendRedirect("redirect_target.jsp");
	
		// forward로 넘기는 방법 - 위에 데이터가 유지됨
		request.getRequestDispatcher("redirect_target.jsp").forward(request, response);
	%>


</body>
</html>