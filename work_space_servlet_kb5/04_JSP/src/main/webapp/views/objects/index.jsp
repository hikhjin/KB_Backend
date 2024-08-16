<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>JSP 내장객체</h2>
	<hr>
	
	<h3>1. request 객체</h3>
	<h3>1.1 request - HTTP header 관련 메소드</h3>
	 - 사용자의 HTTP 헤더에 접근하여 브라우저 관련 정보 및 사용자 정보를 가져옴 <br>
	<%
		Enumeration<String> names = request.getHeaderNames();
	
		while(names.hasMoreElements()){
			String name = names.nextElement();
			out.append(name +" : " + request.getHeader(name) + "<br>");
		}
	%>
	<hr><br>
	 
	<h3>1.2 request - URL, URI 관련 메소드</h3>
	서버 도메인명★ : <%= request.getServerName() %><br>
	서버 포트번호★ : <%= request.getServerPort() %><br>
	웹 애플리케이션 경로(Context Path)★★★ : <%= request.getContextPath() %><br>
	서버 URL : <%= request.getRequestURL() %><br>
	서버 URI : <%= request.getRequestURI() %><br>
	프로토콜 : <%= request.getProtocol() %><br>
	요청방식(get,post) : <%= request.getMethod() %><br>
	요청쿼리 : <%= request.getQueryString() %><br>
	클라이언트 호스트명 : <%= request.getRemoteHost() %><br>
	클라이언트 IP 주소 : <%= request.getRemoteAddr() %><br>
	<hr><br>

	
	<h3>2. response 객체</h3>
	 - 서블릿에서 사용되던 response와 같은 객체, JSP 주로 redirect 용도로만 활용됨 <br>
	 - cookie 사용시 활용<br><br>
	 
	  ■ redirect / forward 실습<br>
	  - redirect : 요청 객체 및 응답 객체를 새로 만들어 전송함으로 응답/요청 정보가 남지 않고 페이지 이동 = get <br>
	  - forward : 요청/응답 정보를 재사용하고, 사용자의 Attribute도 추가해서 전달하는 방법 = post <br>
	 <a href="redirect.jsp">redirect test!</a>

	<br><br><br><br><br>
	 

</body>
</html>