<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/views/common/header.jsp"%>
<style>
    section #enroll-container {
        text-align: center;
    }
    section #enroll-container input {
        margin: 3px;
    }
    section #enroll-container table {
        margin: 0 auto;
    }
    section #enroll-container table th {
        padding: 0 10px;
        text-align: right;
    }
    section #enroll-container table td {
        padding: 0 10px;
        text-align: left;
    }
</style>


<section id="content">
	<h3 align="center">회원 가입 정보</h3>
	<div id="enroll-container">
		<form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/enroll" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="newId" id="newId" placeholder="아이디(4글자 이상)" required/>
						<input type="button" id="checkDuplicate1" value="중복검사1"/>
						<input type="button" id="checkDuplicate2" value="중복검사2"/>
					</td>
				</tr>
			
				<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="userPwd" id="pass1" required/>
					</td>
				</tr>
				<tr>
					<th>패스워드 확인</th>
					<td>
						<input type="password" id="pass2" required/>
					</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" required/></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="phone" maxlength="11"
										placeholder="(-없이)01012345678"/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email"	name="email" placeholder="test@email.com">
					</td>
				</tr>
				
				<tr>
					<th>주소</th>
					<td>
						<input type="text" name="address">
					</td>
				</tr>
				
				<tr>
					<th>취미</th>
					<td>
						<label><input type="checkbox" name="hobby" value="운동"/>운동</label>
						<label><input type="checkbox" name="hobby" value="등산"/>등산</label>
						<label><input type="checkbox" name="hobby" value="독서"/>독서</label>
						<label><input type="checkbox" name="hobby" value="게임"/>게임</label>
						<label><input type="checkbox" name="hobby" value="여행"/>여행</label>
					</td>
				</tr>
			</table>
			<input type="submit" value="가입" />
			<input type="reset" value="취소" />
		</form>
		
		<form name="checkIdForm">
			<input type="hidden" name="userId">
		</form>
	</div>
</section>
<%@include file="/views/common/footer.jsp"%>

<script type="text/javascript">
	/*
	ES6에서 도입 함수형 언어 문법, Arrow Function 기법
	 -> ES6 주요 문법 : class(이미 해봄), 상속, 생성자 / 함수형 언어 문법 = 익명클래스
	 
	       기존 문법(ES5)    |     Arrow Function   
	  xx.(function(){...})	     xx.(()=>{...})   // 인자가 없는 경우
	  xx.(function(e){...})	    xx.((e)=>{...})   // 인자가 1개
	 xx.(function(x,y){...})   xx.((x,y)=>{...})   // 인자가 2개
	-----------------------------------------------------------------
	리턴값이 있는 경우
	 xx.(function(x,y){return x+y;}})   xx.((x,y)=>{return x+y})  
	 xx.(function(x,y){return x+y;}})   xx.((x,y)=> x + y;)  
	
	*/
	
	// JQuery 고전문법
	/*	$("#checkDuplicate1").click(function(){
	});
	*/

	// 정적 페이징 방식 = 동기식 요청을 통해 ID 중복을 확인하는 방법
	//  -> 새로운 창으로 ID 중복을 확인하고 검사하는 방법
	//  -> 잘 안쓰이는 문법, 원인 : 새창으로 여는 경우 팝업으로 인해 사람들이 허가를 안함
	$('#checkDuplicate1').click(()=>{
		let id = $('#newId').val().trim();
		
		if(id.length < 4){
			alert('아이디는 최소 4글자 입니다.');
			return;
		}
		// id나 password 맞는지, 부적절한 문자열이 있는지 검사하는 로직
		
		// 동기식 확인하는 방법
		let url = '<%=request.getContextPath()%>/member/checkId';
		let title = 'duplicate';
		let status = 'left=500px,top=100px,width=500px,height=300px';
		
		open('',title,status); // 새로운 창으로 url을 로드하는 방법
		
//		checkIdForm : html에서 해당 네이밍으로 된 form이 존재함
		checkIdForm.target = title;
		checkIdForm.action = url;
		checkIdForm.method = 'post';
		checkIdForm.userId.value = id;
		checkIdForm.submit();
	});

	// 동적 페이지 방식 = 비동기 방식(AJAX)으로 ID 중복을 확인하는 방법, ★★★★★
	$('#checkDuplicate2').click(()=>{
		let id = $('#newId').val().trim();
		
		if(id.length < 4){
			alert('아이디는 최소 4글자 입니다.');
			return;
		}
		// id나 password 맞는지, 부적절한 문자열이 있는지 검사하는 로직
		
		// 비동기식 처리 - AJAX
		const xhr = new XMLHttpRequest();
		xhr.open('get','${pageContext.request.contextPath}/member/checkId2?userId=' + id);
		
		// callback 함수 셋팅 = 이벤트 함수, 리스너, 핸들러
		xhr.onreadystatechange = ()=>{
			if(xhr.readyState == 4 && xhr.status == 200){
				// 결과값을 받아온 상태
				if(xhr.responseText == 'used'){
					alert('중복된 아이디입니다.');
					// html 코드를 변경하면 동적페이징 된다.
				}else{
					alert('입력된 아이디는 사용가능합니다.');
					// html 코드를 변경하면 동적페이징 된다.
				}
			}
		}
		xhr.send();
	});

</script>



















