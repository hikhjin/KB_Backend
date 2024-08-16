
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>EL(Expression Language) 실습부 코드 </h2>
    <hr><br>

    <h3>1. 기존 JSP 1.0(스크립틀릿)을 통한 Scope 객체를 가져오는 방법</h3>
    request : <%= request.getAttribute("scope") %> <br>
    session : <%= session.getAttribute("scope") %> <br>
    application : <%= application.getAttribute("scope") %> <br>
    null 값 : <%= request.getAttribute("없는 값") %> <br>
    null 없애는 법 : <%= request.getAttribute("없는 값") == null ? "" : request.getAttribute("없는 값") %> <br>
    <hr><br>

    <h3>2. EL을 통해 Scope 객체를 가져오는 방법</h3>
    <h4>2.1 가장 기본적인 사용법</h4>
    scope : ${scope} <br>  <!-- 가장 작은 범위의 scope 영역의 값을 가져온다. -->
    member : ${member} <br>  <!-- toString을 자동으로 호출한다. -->
    member의 이름 : ${member.name} <br> <!-- 자동으로 getter를 호출한다. ★★★★★ -->
    member의 나이 : ${member.age} <br>
    member의 scope : ${member.scope} <br>
    null 값 : ${nullValue} <br> <!-- el에서는 null 값을 빈값으로 취급한다 ★★★★★ -->
    <hr><br>

	■ scope 객체 이름이 중복된 경우는 가장 작은 범위인 request로 부터 값을 가져옴!<br>
	■ 순서 : page < request < session < application 범위 순으로 큼<br>
	※ 팁1 : 스코프간 변수명이 겹치지 않게끔 설계 / 관리 한다. <br>
	※ 팁2 : 만약 데이터가 없는 경우는 'null'이 출력되지 않고 빈칸으로 출력됨 (유용함!!!) <br>
	※ 단점 : 자동완성이 되지 않고, 런타임 이전에는 에러도 잡을수 없다.<br><br>

    <h4>2.2 영역명으로 지정해서 가져오는 방법(스코프 변수명 겹칠때)</h4>
    page : ${pageScope.scope}<br>
    request : ${requestScope.scope}<br>
    session : ${sessionScope.scope}<br>
    application : ${applicationScope.scope} <br>
    <br>

    ■ Member 값<br>
    page : ${pageScope.member}<br>
    request : ${requestScope.member}<br>
    session : ${sessionScope.member}<br>
    application : ${applicationScope.member}<br>
    <br>

    ■ Member - name 값<br>
    page : ${pageScope.member.name}<br>
    request : ${requestScope.member.name}<br>
    session : ${sessionScope.member.name}<br>
    application : ${applicationScope.member.name}<br>
    <hr><br>


    <h3>3. HTTP 헤더 가져오기</h3>
    3.1 스클립틀릿으로 가져오기 <br>
    host : <%=request.getHeader("host") %> <br>
    user-agent : <%=request.getHeader("user-agent") %><br>
    <br>

    3.2 EL로 가져오기 <br>
    host : ${header.host} <br>
    user-agent : ${header['user-agent']} <br> <!-- 변수명에 '-'가 포함되어 있으면 getter로 가져올수 없음 -->

    <h3>4. contextPath 가져오는 방법 ★★★★★</h3>
    4.1 스클립틀릿으로 가져오는 방법<br>
    contextPath : <%=request.getContextPath() %><br>
    <br>

    4.2 EL로 가져오는 방법<br>
    contextPath(O) : ${pageContext.request.contextPath} <br>
<%--    contextPath(X) : ${contextPath} <br> <!-- 안되는 코드! -->--%>
<%--    contextPath(X) : ${pageContext.contextPath} <br> <!-- 안되는 코드! -->--%>


    <br><br><br><br>
    <br><br><br><br>
    <br><br><br><br>
</body>
</html>















