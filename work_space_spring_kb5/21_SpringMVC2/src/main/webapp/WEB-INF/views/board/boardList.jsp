<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판 목록" name="title"/>
</jsp:include>

<section id="content">
	<div id='board-list-container'>
		<h2>자유 게시판</h2>
		
		<!-- 글쓰기 버튼 -->
		<button type="button" id="btn-add" style="margin-bottom: 3px; margin-left: 120px;"
						onclick="location.href='${path}/board/write'">글쓰기</button>

		<!-- 게시판 목록 시작 -->
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
				<c:forEach var="item" items="${list}"> <!--외우기-->
					<tr>
						<td>${item.bno}</td>
						<td>
							<a href="${path}/board/view?bno=${item.bno}">
								${item.title}
							</a>
						</td>
						<td>${item.writer}</td>
						<td>${item.createDate}</td>
					</tr>
				</c:forEach>
		</table>
		<!-- 게시판 목록 끝 -->
	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>



