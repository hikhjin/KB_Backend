<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 작성" name="title"/>
</jsp:include>

<!-- 글쓰기 에디터 추천 : https://summernote.org/ , https://naver.github.io/smarteditor2/user_guide/ -->

<section id="board-write-container">
	<h2 align="center">게시글 작성</h2>
	
	<!-- enctype="multipart/form-data" : 파일 첨부를 할수 있는 html 옵션  -->
	<form action="${path}/board/write" method="post">
		<table id="tbl-board">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" class="input-text"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="writer" class="input-text">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="15" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit">등록</button>
					<button type="reset">취소</button>
				</th>
			</tr>
		</table>
	</form>
</section>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>







