<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<style>
    section>div#board-write-container{width:600px; margin:0 auto; text-align:center;}
    section>div#board-write-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    .input-text {width: 350px;}
</style>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 작성" name="title"/>
</jsp:include>


<!-- 글쓰기 에디터 추천 : https://summernote.org/ , https://naver.github.io/smarteditor2/user_guide/ -->

<section id="board-write-container">
	<h2 align="center">게시글 작성</h2>
	
	<!-- enctype="multipart/form-data" : 파일 첨부를 할수 있는 html 옵션  -->
	<form action="${path}/board/write" method="post" enctype="multipart/form-data">
		<table id="tbl-board">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" class="input-text"></td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<select name="type">
						<c:forEach var="item" items="${categoryList}" varStatus="status">
							<c:if test="${status.first and loginMember.role == 'ROLE_ADMIN'}">
								<option value="${item.type}">${item.name}</option>
							</c:if>
							<c:if test="${not status.first and not status.last}">
								<option value="${item.type}">${item.name}</option>
							</c:if>
							<c:if test="${status.last and loginMember.role == 'ROLE_ADMIN'}">
								<option value="${item.type}">${item.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="memberId" value="${loginMember.id}" readonly class="input-text">
				</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td>
					<input type="file" multiple="multiple" name="upfiles">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="15" cols="50"></textarea>
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







