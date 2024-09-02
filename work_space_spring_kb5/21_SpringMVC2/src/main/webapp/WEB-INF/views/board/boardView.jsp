<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 상세조회" name="title"/>
</jsp:include>

<section id="board-write-container">
	<h2 align="center">게시글 상세조회</h2>
	
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td>${board.bno}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.writer}</td>
		</tr>
		</tr>
		<tr>
			<th>작성 시간</th>
			<td>${board.createDate} </td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="20" cols="80" readonly>${board.content}</textarea>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<button type="button" id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</th>
		</tr>
	</table>
	<br>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


<script type="text/javascript">

	$(document).ready(() => {
		$("#btnUpdate").click((e) => {
			location.href = "${path}/board/update?bno=${board.bno}";
		});
		
		$("#btnDelete").click((e) => {
			if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
				location.replace("${path}/board/delete?bno=${board.bno}");
			}
		});
	});

</script>


















