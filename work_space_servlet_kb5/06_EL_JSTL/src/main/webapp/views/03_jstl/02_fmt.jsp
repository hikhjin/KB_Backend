<%@page import="java.time.LocalDateTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>JSTL Formatting Library (숫자, 통화, 날짜 포멧팅 + 국제화)</h2>

	<h3>0. Locale 지정 = 국제화</h3>
	 - 포멧을 표시할 국가를 지정하여 화폐단위, 날짜형식, 특수한 시간 포멧을 미리 지정할수 있음<br>
	 - 기본 locale(톰캣에서 설정된) : ${pageContext.response.locale} <br><br>

	<!-- 로케일 변경하는 문법 -->
	<!-- (as defined by ISO-639) / (as defined by ISO-3166). -->
	<fmt:setLocale value="en_us"/>
	변경된 Locale : ${pageContext.response.locale} <br>
	변경된 통화 단위 : <fmt:formatNumber value="10000" type="currency"/><br><br>

	<fmt:setLocale value="ja_jp"/>
	변경된 Locale : ${pageContext.response.locale} <br>
	변경된 통화 단위 : <fmt:formatNumber value="10000" type="currency"/><br><br>

	<fmt:setLocale value="zh_cn"/>
	변경된 Locale : ${pageContext.response.locale} <br>
	변경된 통화 단위 : <fmt:formatNumber value="10000" type="currency"/><br><br>

	<fmt:setLocale value="ko_kr"/>
	변경된 Locale : ${pageContext.response.locale} <br>
	변경된 통화 단위 : <fmt:formatNumber value="10000" type="currency"/><br><br>

	
	<h3>1. formatNumber</h3>
	- 숫자에 관련된 포멧팅을 설정할때 사용<br>
	- 자릿수, 소수점, 통화기호, % 등의 숫자 형식을 지정할수 있다.<br><br>
	<br>
	
	1) groupingUsed - 자릿수 구분용도 <br>
	세자리 구분(default=true) : <fmt:formatNumber value="123456789"/><br>
	세자리 (강제) : <fmt:formatNumber value="123456789" groupingUsed="true"/><br>
	세자리 (해제) : <fmt:formatNumber value="123456789" groupingUsed="false"/><br>
	<br>
	
	2) pattern - 통화 패턴을 정의하는 용도, 소수점만 의미 있다. <br>
	#을 통한 패턴화(소수점 2자리) : <fmt:formatNumber value="${10/3.0}" pattern="#.##"/><br>
	#을 통한 패턴화(소수점 5자리) : <fmt:formatNumber value="1.23456789" pattern="#.#####"/><br>
	0을 통한 패턴화(소수점 1자리) : <fmt:formatNumber value="1.23456789" pattern="0.0"/><br>
	0을 통한 패턴화(소수점 3자리) : <fmt:formatNumber value="1.23456789" pattern="0.000"/><br>
	<br>

	3) type 속성 <br>
	number(기본) : <fmt:formatNumber type="number" value="123456789" /><br>
	currency(통화-기본) : <fmt:formatNumber type="currency" value="123456789" /><br>
	currency(통화-달러) : <fmt:formatNumber type="currency" value="123456789" currencySymbol="$" /><br>
	percent(%) : <fmt:formatNumber type="percent" value="0.3033" /><br>
	<br>

	<h3>2. formatDate</h3>
	- 날짜와 시간에 관련된 포맷을 지정할때 사용<br>
	- value는 반드시 Date류 사용해야한다.<br><br>
	<c:set var="date" value="<%=new java.util.Date()%>"/>
	<!-- JSP에서는 LocalDateTime이 호환되지 않음으로 Date 형식으로 다루는 것을 권장!!!  -->
<%-- 	<c:set var="date2" value="<%=LocalDateTime.now()%>"/>   --%>
	
	■ 국내 날짜 표기<br>
	포멧 없이 출력 : ${date} <br>
	오늘 날짜(기본-Date) : <fmt:formatDate value="${date}"/> <br>
<%-- 	오늘 날짜(기본-Date) : <fmt:formatDate value="${date2}"/> <br> --%>
	오늘 날짜(날짜만) : <fmt:formatDate value="${date}" type="date"/> <br>
	현재 시간 : <fmt:formatDate value="${date}" type="time"/> <br>
	날짜 + 시간 : <fmt:formatDate value="${date}" type="both"/> <br>
	[short] : <fmt:formatDate value="${date}" type="both" dateStyle="short" timeStyle="short"/> <br>
	[medium] : <fmt:formatDate value="${date}" type="both" dateStyle="medium" timeStyle="medium"/> <br>
	[long] : <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long"/> <br>
	[full] : <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full"/> <br>
	패턴 지정 : <fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd(E) hh:mm:ss(a)"/> <br>
	short패턴 : <fmt:formatDate value="${date}" type="both" pattern="YY-MM-dd HH:mm:ss"/> <br>
	<hr><br>

	<fmt:setLocale value="en_us"/>
	■ 미국날짜 표기<br>
	오늘 날짜(기본) : <fmt:formatDate value="${date}"/><br>
	오늘 날짜(날짜만) : <fmt:formatDate value="${date}" type="date"/><br>
	현재 시간 : <fmt:formatDate value="${date}" type="time"/><br>
	날짜 + 시간 : <fmt:formatDate value="${date}" type="both"/><br>
	[short] : <fmt:formatDate value="${date}" type="both" dateStyle="short" timeStyle="short"/><br>
	[medium] : <fmt:formatDate value="${date}" type="both" dateStyle="medium" timeStyle="medium"/><br>
	[long] : <fmt:formatDate value="${date}" type="both" dateStyle="long" timeStyle="long"/><br>
	[full] : <fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full"/><br>
	패턴 지정 : <fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	short : <fmt:formatDate value="${date}" type="both" pattern="YY/MM/dd HH:mm:ss"/><br>
	<hr><br>	
	
	
	<fmt:setLocale value="ko_kr"/>
	■ 현지 시간 표기(timeZone)<br>
	GMT-0 : <fmt:formatDate value="${date}" timeZone="Etc/GMT-0" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	GMT 설정 : <fmt:formatDate value="${date}" timeZone="Etc/GMT-9" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	한국 시간 : <fmt:formatDate value="${date}" timeZone="Asia/Seoul" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	미국 시간(LA) : <fmt:formatDate value="${date}" timeZone="America/Los_Angeles" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	미국 시간(뉴욕) : <fmt:formatDate value="${date}" timeZone="America/New_York" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/><br>
	참고 : https://www.ibm.com/docs/en/cloudpakw3700/2.3.0.0?topic=SS6PD2_2.3.0/doc/psapsys_restapi/time_zone_list.html

	<br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br>
</body>
</html>













