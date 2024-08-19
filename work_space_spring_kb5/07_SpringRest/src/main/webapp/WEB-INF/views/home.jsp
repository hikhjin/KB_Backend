<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-latest.js"></script>
	<style>
    #div1 {
        height: 200px;
        width: 100%;
        border: 1px solid black;
        margin-top: 3px;
    }
    </style>
</head>
<body>
	<h1>REST API</h1>
	 - REST(Representational State Transfer)는 월드 와이드 웹과 같은 분산 하이퍼미디어 시스템을 위한 소프트웨어 아키텍처의 한 형식이다.<br>
	 - 이미 우리가 사용하고 있던 전자 정부의 OPEN API는 해당 REST API(URL을 통한 요청)을 의미한다.<br>
	 - 현업에서는 Server간 Interface를 담당하며 Read 뿐만 아니라 CRUD 전반적인 행위가 가능하다.<br>
	 - 최근 Mobile 및 JS 기반 프레임 워크 사용시 Back-end 구축 중요 설계 방법<br>
	 <br>

	https://ko.wikipedia.org/wiki/REST<br>
	https://meetup.toast.com/posts/92<br>
	https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html<br><br>
	
	
	<h3>REST의 주요 요청 </h3>
	C 생성 - POST<br>
	R 읽기 - GET<br>
	U 업데이트 - PUT 또는 PATCH<br>
	D 삭제 - DELETE<br>
	<hr><br>
	
	
	<h3>JSON 방식의 REST API 실습</h3>
	<h4>1. Read (읽기, SELECT, get 요청)</h4>
	 - get 요청을 통해 url을 생성하고 이를 통해 읽어오는 과정, 특정 쿼리(=/?test=1)도 url로 만들수 있다. <br>
	 - 모든 고양이 정보 요청 : <a href="${path}/cat">${path}/cat</a> <br>
	 - 특정 이름의 고양이 정보 요청 : <a href="${path}/cat?name=페르시안">${path}/cat?name=페르시안</a><br>
	 - ID(리소스 고유 번호)로 요청하기 : <a href="${path}/cat/1">${path}/cat/1</a><br>
	
	<h4>2. Create (생성, INSERT, POST 요청)</h4>
	<h4>3. Update (갱신, POST 요청)</h4>
	<h4>4. Delete (삭제, POST 요청)</h4>
<!-- 고양이 정보 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=peck119&logNo=221627051831 -->

	ID : <input type="text" id='id'>(R/U/D에서 사용)
	<button id="readRequest">Read</button>
	<br>
	이름 : <input type="text" id="name"><br>
	정보 : <input type="text" id="info"><br>
	눈동자 색상 : <input type="text" id="eyeColor"><br>
	털의 길이 : <input type="text" id="hairLength"><br>
	특징 : <input type="text" id="features"><br>
	색상 : <input type="text" id="color"><br>
	체중 : <input type="text" id="weight"><br>
	<button id="createRequest">Create</button>
	<button id="updateRequest">Update</button>
	<button id="deleteRequest">Delete</button>
	<div id="div1"></div>
	
	<script type="text/javascript">
		$('#readRequest').click(() => {
			let id = $('#id').val();
			$.ajax({
				method:'get',
				url:'${path}/cat/' + id,
				contentType: 'application/json',
				dataType : 'json',
				success: (result) =>{
					alert('get 성공!');
					$('#div1').html(JSON.stringify(result))
				},
				error : (e) => {
					alert('전송 실패!!');
				}
			});
		});

		$('#createRequest').click(() => {
			let name = $('#name').val();
			let info = $('#info').val();
			let eyeColor = $('#eyeColor').val();
			let hairLength = $('#hairLength').val();
			let features = $('#features').val();
			let color = $('#color').val();
			let weight = $('#weight').val();
			
			$.ajax({
				method:'post',
				url:'${path}/cat/',
				contentType: 'application/json',
				dataType : 'json',
				data : JSON.stringify(	
					{name, info ,eyeColor,
						hairLength, features, color, weight }
				),
				success: (result) =>{
					alert('Create 성공!');
					$('#div1').html(JSON.stringify(result))
				},
				error : (e) => {
					alert('전송 실패!!');
				}
			});
		});

		$('#updateRequest').click(() => {
			let id = $('#id').val();
			let name = $('#name').val();
			let info = $('#info').val();
			let eyeColor = $('#eyeColor').val();
			let hairLength = $('#hairLength').val();
			let features = $('#features').val();
			let color = $('#color').val();
			let weight = $('#weight').val();
			
			$.ajax({
				method:'put', // update임을 알리는 method
				url:'${path}/cat/' + id,
				contentType: 'application/json',
				dataType : 'json',
				data : JSON.stringify(	
					{id, name, info ,eyeColor,
						hairLength, features, color, weight }
				),
				success: (result) =>{
					alert('Update 성공!');
					$('#div1').html(JSON.stringify(result))
				},
				error : (e) => {
					alert('전송 실패!!');
				}
			});
		});

		$('#deleteRequest').click(() => {
			let id = $('#id').val();
			
			$.ajax({
				method:'delete', // delete
				url:'${path}/cat/' + id,
				contentType: 'application/json',
				dataType : 'json',
				success: (result) =>{
					alert('Delete 성공!');
					$('#div1').html(JSON.stringify(result))
				},
				error : (e) => {
					alert('전송 실패!!');
				}
			});
		});
	</script>
	
	<br><br><br><br><br><br><br><br><br><br>
	
	</body>
</html>