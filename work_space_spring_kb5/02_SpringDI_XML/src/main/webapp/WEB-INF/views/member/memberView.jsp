<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
    
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="title" name="회원정보 보기" />
</jsp:include>
<%--<jsp:useBean id="memberDTO" scope="request" type="com.multi.basic.member.model.dto.MemberDTOber"/>--%>

<section id="content" class="container text-center" style="width: 40%">
	<h3 class="text-center">회원 정보 보기</h3>
	<table class="table text-center">
		<tr>
			<th>아이디</th>
			<td>
			<c:out value="${member.id}"/> </td>
		</tr>
		<tr>
			<th>이름</th>
			<td> <c:out value="${member.name}"/> </td>
		</tr>
		<tr>
			<th>나이</th>
			<td> <c:out value="${member.age}"/> </td>
		</tr>
		<tr>
			<th>성별</th>
			<td> 
				<c:out value="${member.gender}"/>
			</td>
		</tr>
		<tr>
			<th>좋아하는 언어</th>
			<td>
				<c:forEach var="item" items="${member.devLang}" varStatus="status">
					<c:out value="${item}"/><c:if test="${not status.last}"><c:out value=","/></c:if>
				</c:forEach>
			</td>
		</tr>
	</table>
</section>
    

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>







