<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

	<h2>JSTL Function Library</h2>
	<h3>- 일반적으로 문자열 전용이지만 일부 메소드는 컬랙션과 Array에서도 사용 가능하다.</h3>

	fn:contains(string, substring) : string이 substring을 포함하면 return true 반환<br>
	fn:containsIgnoreCase(string, substring) : 대소문자 관계없이 string이 substring을 포함하면 return true 반환<br>
	fn:startsWith(string, prefix) : string이 prefix로 시작하면 return True<br>
	fn:endsWith(string, suffix) : string이 suffix로 끝나면 return True<br>
	fn:escapeXml(string) : string에 XML과 HTML에서  ><& ' " 문자들이 있으면, XML엔티티 코드로 바꿔준뒤 문자열 반환<br>
	fn:indexOf(string, substring) : string에서 substring이 처음으로 나타나는 인덱스 반환<br>
	fn:split(string, separator) : string내의 문자열 separator에 따라 나누어서 배열로 구성해서 반환<br>
	fn:join(array, separator) : array요소들을 separator를 구분자로 하여 연결해서 반환<br>
	fn:length(item) : item이 배열이나 컬렉션이면 요소의 개수를 문자열이면 문자의 개수를 반환<br>
	fn:replace(string, before, after) : string내에 있는 before 문자열을 after 문자열로 모두 변경해서 반환<br>
	fn:substring(string, begin, end) : string에서 begin인덱스에서 시작해서 end인덱스에 끝나는 부분의 문자열 반환<br>
	fn:substringAfter(string, substring) : string에서 substring이 나타나는 이후의 문자열 반환<br>
	fn:substringBefore(string, substring) : string에서 substring이 나타나는 이전의 문자열 반환<br>
	fn:toUpperCase(string): string을 모두 대문자로 변경 후 리턴<br>
	fn:toLowerCase(string) : string을 모두 소문자로 변경 후 리턴<br>
	fn:trim(string) : string앞뒤의 공백을 모두 제거한 후 반환 <br><br>

	<h3>문자열</h3>
	<c:set var="str" value="Hello Java World! JSTL is Good Function!!" />
	str : ${str}<br><br>

	대문자 : ${fn:toUpperCase(str)}<br>
	소문자 : ${fn:toLowerCase(str)}<br>
	contains : ${fn:contains(str,'JSTL') }<br>
	contains : ${fn:contains(str,'JSP') }<br>
	indexOf : ${fn:indexOf(str,'JSTL') }<br>
	indexOf : ${fn:indexOf(str,'JSP') }<br>
	replace : ${fn:replace(str,'JSTL', 'JSTL/EL') }<br>
	length : ${fn:length(str)}<br>
	<hr><br>

	
	<h3>컬랙션</h3>
	<%
		List<String> list = new ArrayList<>();
		list.add("테스트1");
		list.add("테스트2");
		list.add("테스트3");
		list.add("테스트4");
		pageContext.setAttribute("list", list);
	%>
	
	길이 : ${fn:length(list)} <br>
	contains : ${fn:contains(list, '테스트0')} <br>
	contains : ${fn:contains(list, '테스트1')} <br>
	indexOf : ${fn:indexOf(list, '테스트0')} <br>
	indexOf : ${fn:indexOf(list, '테스트1')} <br>
	삼항연산자 예시 : ${fn:contains(list, '테스트0') ? 'OK' : 'NULL'}<br>
	삼항연산자 예시 : ${fn:contains(list, '테스트1') ? 'OK' : 'NULL'}<br>

	<h3>체크박스 응용</h3>
	<%
		// 이전페이지에서 체크 되었던 list
		List<String> checkList = new ArrayList<>();
		checkList.add("서울");
		checkList.add("경기도");
		checkList.add("강원도");
		checkList.add("부산");
		pageContext.setAttribute("checkList", checkList);
	%>
	
	<form action="#">
		<input type="checkbox" name="sido" value="서울" 		${fn:contains(checkList, '서울') ? 'checked' :''}>서울<br>
		<input type="checkbox" name="sido" value="경기도" 	${fn:contains(checkList, '경기도') ? 'checked' :''}>경기도<br>
		<input type="checkbox" name="sido" value="인천" 		${fn:contains(checkList, '인천') ? 'checked' :''}>인천<br>
		<input type="checkbox" name="sido" value="강원도" 	${fn:contains(checkList, '강원도') ? 'checked' :''}>강원도<br>
		<input type="checkbox" name="sido" value="경상북도" 	${fn:contains(checkList, '경상북도') ? 'checked' :''}>경상북도<br>
		<input type="checkbox" name="sido" value="전라남도" 	${fn:contains(checkList, '전라남도') ? 'checked' :''}>전라남도<br>
		<input type="checkbox" name="sido" value="대전" 		${fn:contains(checkList, '대전') ? 'checked' :''}>대전<br>
		<input type="checkbox" name="sido" value="부산" 		${fn:contains(checkList, '부산') ? 'checked' :''}>부산<br>
	</form>

	<br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br>

</body>
</html>