<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="title" name="demoTest" />
</jsp:include>


<section id="container">

	<h3 style="text-align: center;">회원가입</h3>
	<h3 style="text-align: center;">Servlet HttpRequest로 파라미터 처리</h3>
	<div id="demo-container">
		<form id="form1" name="form1" action="${path}/member/memberServlet.do" method="post">
			<div class="form-group row">
				<label for="id"
					class="col-sm-3 col-form-label align-self-center text-center">아이디</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="id" name="id"
						value="test">
				</div>
			</div>

			<div class="form-group row">
				<label for="name"
					class="col-sm-3 col-form-label align-self-center text-center">이름</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="name" name="name"
						value="홍길동">
				</div>
			</div>


			<div class="form-group row">
				<label for="age"
					class="col-sm-3 col-form-label align-self-center text-center">나이</label>
				<div class="col-sm-9">
					<input type="number" class="form-control" id="age" name="age"
						value="21">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-3 col-form-label align-self-center text-center">성별</label>
				<div class="col-sm-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							checked="checked" id="gender1" value="M"> <label
							class="form-check-label" for="gender1">남</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="gender2" value="F"> <label class="form-check-label"
							for="gender2">여</label>
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-3 col-form-label">좋아하는 언어</label>
				<div class="col-sm-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input" checked="checked" type="checkbox"
							name="devLang" id="devLang0" value="Java"> <label
							class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
							id="devLang1" value="C"> <label class="form-check-label"
							for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" checked="checked" type="checkbox"
							name="devLang" id="devLang2" value="Javascript"> <label
							class="form-check-label" for="devLang2">JavaScript</label>
					</div>
				</div>
			</div>
			
			<div class="container ">
		        <div class="mx-auto " style="width: 100px;">
				  <button type="submit" class="btn btn-primary">
				  	회원가입
				  </button>
		        </div>
		    </div>
		</form>

	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />