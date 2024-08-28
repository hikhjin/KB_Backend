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
    div#comment-container button#btn-insert{width:60px;height:50px; color:white; background-color:#3300FF;position:relative;top:-20px;}
    
    /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
</style>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 상세조회" name="title"/>
</jsp:include>

<section id="board-write-container">
	<h2 align="center">게시글 상세조회</h2>
	
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td><c:out value="${board.bno}"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><c:out value="${board.title}"/></td>
		</tr>
		<tr>
			<th>타입</th>
			<td><c:out value="${typeMap[board.type]}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${board.memberName}(${board.memberId})"/></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><c:out value="${board.readCount}"/></td>
		</tr>
		<tr>
			<th>작성 시간</th>
			<td><fmt:formatDate type="both" value="${board.createDate}"/> </td>
		</tr>
		<tr>
			<th>수정 시간</th>
			<td><fmt:formatDate type="both" value="${board.modifyDate}"/> </td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td>
				<c:if test="${empty board.attachFiles}">
					<span>-</span>
				</c:if>		
				<c:if test="${not empty board.attachFiles}">
					<c:forEach var="item" items="${board.attachFiles }">
						<div>
							<a href="javascript:fileDownload('${item.fno}');">
								<img src="${path}/resources/images/file.png" width="20" height="20"/>
								<c:out value="${item.originalFilename}"></c:out>		
							</a>
						</div>
					</c:forEach>
				</c:if>		
			</td>		
		</tr>
		<tr>
			<th>첨부파일(이미지)</th>
			<td>
				<c:if test="${not empty board.attachFiles}">
					<c:forEach var="item" items="${board.attachFiles}" >
					<c:if test="${fn:contains(item.originalFilename,'.jpg') 
								  or fn:contains(item.originalFilename,'.png')  
								  or fn:contains(item.originalFilename,'.jpeg')}">
					<div>
						<img src="${path}/resources/upload/board/${item.renamedFilename}"
																	width="100%" height="100%"/>
					</div>
					</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="15" cols="50" readonly><c:out value="${board.content}"/></textarea>
			</td>
		</tr>
		<!-- 수정 삭제 버튼 보이는 부분 -->
		<c:if test="${not empty loginMember && (loginMember.id == board.memberId 
														|| loginMember.role == 'ROLE_ADMIN')}">
			<tr>
				<th colspan="2">
					<button type="button" id="btnUpdate">수정</button>
					<button type="button" id="btnDelete">삭제</button>
				</th>
			</tr>
		</c:if>
	</table>
	
	<!-- 리플 작성 Form -->
	<c:if test="${not empty loginMember}">
		<div id="comment-container">
	    	<div class="comment-editor" align="center">
	    		<form action="${path}/board/reply" method="post">
	    			<input type="hidden" name="bno" value="${board.bno}" />
	    			<input type="hidden" name="memberId" value="${loginMember.id}" />
					<textarea name="content" id="replyContent" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>	  	
	    		</form>
	    	</div>
	   	</div>
	</c:if>
	
	<!-- 리플 리스트 출력 -->
	<table id="tbl-comment">
		<c:if test="${empty board.replies }">
			<tr>
				<td style="text-align: center;">등록된 리플이 없습니다.</td>
			</tr>
		</c:if>
		
		
		<c:if test="${not empty board.replies}">
			<c:forEach var="reply" items="${board.replies}">
				<tr>
					<td>
						<sub class="comment-writer"><c:out value="${reply.memberName}(${reply.memberId})"/> </sub>
						<sub class="comment-date"><fmt:formatDate type="both" value="${reply.createDate}"/></sub>	
						<br>
						<c:out value="${reply.content}"/>
					</td>
					<td>
						<!-- 삭제버튼 -->
						<c:if test="${not empty loginMember && (loginMember.id == reply.memberId
																|| loginMember.role == 'ROLE_ADMIN') }">
							<button class="btn-delete" onclick="deleteReply('${reply.rno}','${board.bno}');" >삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	<br>

</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


<script type="text/javascript">

	$(document).ready(() => {
		$("#btnUpdate").click((e) => {
			location.href = "${path}/board/update?no=${board.bno}";
		});
		
		$("#btnDelete").click((e) => {
			if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
				location.replace("${path}/board/delete?no=${board.bno}");
			}
		});
	});

	function fileDownload(fno) {
		const url = '${path}/board/fileDown?fno=' + fno;
		location.href = url;
	}

	function deleteReply(rno, bno) {
		const url = '${path}/board/replyDel?rno=' + rno + '&bno=' + bno;
		location.href = url;
	}

</script>


















