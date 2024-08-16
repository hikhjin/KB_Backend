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
	<div id="demo-container">
		<h2 style="text-align: center;">파라미터 처리 예제</h2>
	
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberServlet.do';" class="list-group-item list-group-item-action">Servlet
				서블릿의 HttpRequest로 파라미터 처리
			</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberParams.do';"
				class="list-group-item list-group-item-action">
				@RequestParam 어노테이션 활용 파라미터 처리</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberCommand.do';"
				class="list-group-item list-group-item-action">
				Command 방식(객체,VO)를 통한 파라미터 처리</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberMap.do';"
				class="list-group-item list-group-item-action">
				컬렉션 Map을 통한 파라미터 처리
			</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberArrayParam.do';"
					class="list-group-item list-group-item-action">
				파라미터 배열 처리하는 방법
			</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/rest/test';"
					class="list-group-item list-group-item-action">
				path 기반(URL)으로 파라미터 처리
			</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberAddInfo.do';"
						class="list-group-item list-group-item-action">
				추가데이터 가져오기 예제(header,cookie)
			</button>
		</div>
		<div class="list-group">
			<button type="button" onclick="location='${path}/member/memberEnroll.do';"
				class="list-group-item list-group-item-action">
				회원 가입하기 (데이터저장)
			</button>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />