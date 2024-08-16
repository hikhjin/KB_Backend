<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP 2.0 - EL/JSTL</title>
</head>
<body>

<h2>JSP 2.0 - EL / JSTL</h2>
<hr><br>

<h3>EL(Expression Language)</h3>
- EL(표현 언어)는 기존 스클립틀릿 표현식을 대체해서 사용되는 JSP 2.0의 핵심 표현식<br>
- 표현 뿐만 아니고 간단한 연산도 가능함으로 문법이 확장되어 사용됨 <br>


<h3>1. EL - 기본문법</h3>
<a href="el.do">실습 보러가기</a>
<hr><br>


<h3>2. EL(Param) - 파라메터 처리법</h3>
<form action="${pageContext.request.contextPath}/views/01_EL/02_elParam.jsp"
      method="post">
    <fieldset>
        <legend>물품 주문</legend>
        <input type="text"  name="pName" placeholder="제품명"/><br>
        <input type="number" name="pNumber" placeholder="수량"/><br>
        <input type="text" name="option" placeholder="옵션1"/><br>
        <input type="text" name="option" placeholder="옵션2"/><br>
        <input type="text" name="option" placeholder="옵션"/><br>
        <input type="submit" value="제출">
    </fieldset>
</form>
<hr><br>


<h2>JSP Action Tag</h2>
- 기존 스클립틀릿에서 사용하던 include, forward와 같은 기능을 HTML 문법과 유사하게 제공<br>
- 좋은 점 : JSP가 HTML과 유사하게 보이게 된다. <br>
<br>

<h3>1. 표준 액션 태그 (Standard Action Tag)</h3>
- JSP 페이지에서 사용 가능하며, 태그 앞에 jsp: 접두어가 붙는 태그 <br>
<a href="${pageContext.request.contextPath}/views/02_JAT/01_include.jsp">jsp:include</a>
<a href="${pageContext.request.contextPath}/views/02_JAT/02_forward.jsp">jsp:forward</a>
<hr><br>

<h3>2. 커스텀 액션 태그(Custom Action Tag) = JSTL(Java의 표준적인 커스텀 액션 태그)</h3>
- JSP 2.0의 기능으로 사용자가 정의한 특별한 태그를 사용할수 있도록 지원하는 기능 <br>
-> JSTL(표준 태그) 외에는 사실상 잘 사용되지 않는다. <br>
- 개발자나 프로젝트 단위로 특정한 태그를 커스텀하고 라이브러리 형태로 설치해야 사용가능하다. <br>
- 커스텀 액션 태그는 모든 태그 앞에 고유 접두어 (ex) c:, fn:, fmt:)가 붙여 사용가능 <br>
<hr><br>

<h2>JSTL (JSP, Standard Tag Library)</h2>
- JSP에서 사용하는 표준적인 기능을 커스텀 태그로 만들어 직접 배포한 Library <br>

<h3>0. 라이브러리 설치 방법</h3>
- https://tomcat.apache.org/download-taglibs.cgi#Standard-1.2.5 <br>
- Standard-1.2.5-xxx.jar 파일들을 다운로드하여 임포트 (webapp/WEB-INF/lib)<br>
<br>

<h3>1. JSTL Core Library</h3>
- 변수 관리와 URL 포멧팅, 조건문, 반복문 등과 같이 언어와 로직에 관련된 JSTL 기능을 제공<br>
<a href="${pageContext.request.contextPath}/views/03_jstl/01_core.jsp">View Details</a>
<br>

<h3>2. JSTL Formatting Library</h3>
- 날짜, 시간, 숫자 등의 데이터를 출력할때 형식 관련하여 사용하는 태그<br>
<a href="${pageContext.request.contextPath}/views/03_jstl/02_fmt.jsp">View Details</a>
<br>

<h3>3. JSTL Function Library</h3>
- 문자열에 관련된 함수가 포함된 태그<br>
<a href="${pageContext.request.contextPath}/views/03_jstl/03_fn.jsp">View Details</a>
<br><br><br><br><br>
<br><br><br><br><br>

</body>
</html>