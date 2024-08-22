<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.multi.mvc.member.model.vo.Member, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원정보" name="title"/>
</jsp:include>

<style>
div#update-container{width:400px; margin:0 auto; text-align:center;}
div#update-container input, div#update-container select {margin-bottom:10px;}
</style>

<div id="update-container">
	<h3>CSRF 방지를 위한 authentication principal 활용법</h3>

	<form:form
		name="memberUpdateFrm"
		action="${pageContext.request.contextPath}/member/memberUpdate.do" 
		method="post">
		<!-- var속성 으로 변수선언 -->
		<sec:authentication property="principal" var="principal"/>

		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="id" id="id"
			   				value='<sec:authentication property="principal.id"/>' readonly required/>
		<input type="text" class="form-control" placeholder="이름" name="name" id="name"
			   				value="<sec:authentication property="principal.name"/>" required/>
		<input type="text" class="form-control" placeholder="이름" name="age" id="age"
			   value="<sec:authentication property="principal.age"/>" required/>
		<br />
		<input type="submit" class="btn btn-outline-success" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
		<!--CSRF 방지를 위해 자동으로 아래 보안 키가 삽입된다.-->
<%--		<input type="hidden" name="_csrf" value="a73f283c-5f78-499d-b82c-44ea07c2f89f" />--%>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>