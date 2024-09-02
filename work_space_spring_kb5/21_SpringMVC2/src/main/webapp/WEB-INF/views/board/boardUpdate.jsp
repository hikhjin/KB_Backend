<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 수정" name="title"/>
</jsp:include>


<section id="board-write-container">
	<h2 align="center">게시글 수정</h2>
	
	<!-- enctype="multipart/form-data" : 파일 첨부를 할수 있는 html 옵션  -->
	<form action="${path}/board/update" method="post">
		<input type="hidden" name="bno" value="${board.bno}">
	
		<table id="tbl-board">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" class="input-text" value="${board.title}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="memberId" value="${board.writer}" readonly class="input-text">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="15" cols="80">${board.content}</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit">수정</button>
					<input type="button" onclick="location.replace('${path}/board/list')" value="목록으로">
				</th>
			</tr>
		</table>
	</form>
</section>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>



<script type="text/javascript">
	function fileDownload(fno) {
		const url = '${path}/board/fileDown?fno=' + fno;
		location.href = url;
	}
</script>





