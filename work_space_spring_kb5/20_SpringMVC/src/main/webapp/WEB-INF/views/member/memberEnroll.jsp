<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원가입" name="title"/>
</jsp:include>

<style>
	section #enroll-container {text-align: center;}
	section #enroll-container input {margin: 3px;}
	section #enroll-container table {margin: 0 auto;}
	section #enroll-container table th {padding: 0 10px; text-align: right;}
	section #enroll-container table td {padding: 0 10px;text-align: left;}
</style>

<section id="content">
	<h3 align="center">회원가입</h3>
	
	<form id="enroll-container" name="form1" action="${path}/member/enroll" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="id" name="id" placeholder="아이디 4글자 이상" required>
					<input type="button" id="checkDuplicate" value="중복검사" >
				</td>
			</tr>		
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" id="password1" name="password" required>
				</td>
			</tr>
			<tr>
				<th>패스워드 확인</th>
				<td>
					<input type="password" id="password2" required>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" id="name" name="name" required>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="tel" id="phone" name="phone" maxlength="13" placeholder="010-1234-5678">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email" name="email" id="email">
				</td>
			</tr>		
			<tr>
				<th>주소</th>
				<td>
					<input type="text" id="address" name="address">
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<label><input type="checkbox" name="hobby" value="여행">여행</label>
					<label><input type="checkbox" name="hobby" value="운동">운동</label>
					<label><input type="checkbox" name="hobby" value="수영">수영</label>
					<label><input type="checkbox" name="hobby" value="게임">게임</label>
					<label><input type="checkbox" name="hobby" value="독서">독서</label>
				</td>
			</tr>
		</table>
		<input type="submit" id="enrollSubmit" value="가입">
		<input type="reset" value="취소">
	</form>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>


<script type="text/javascript">
	$(()=>{
		$('#password2').blur((e)=>{
			let password1 = $('#password1').val();
			let password2 = $('#password2').val();
			
			if(password1.trim() != password2.trim()){
				alert('패스워드가 일치하지 않습니다.');
				$('#password1').val('');
				$('#password2').val('');
				$('#password1').focus();
				
			}
		});
		
		$('#enrollSubmit').click((e)=>{
			// 서버에 보내기 전에 유효성 확인하는 로직부
			let id = $('#id').val();
			if(id.length < 4){
				alert('아이디가 4글자 보다 적습니다.');
				$('#id').focus();
				return false; // submit을 취소하는 방법, 실제 서버로 전달되지 않음
			}
			
			let password1 = $('#password1').val();
			if(password1.length < 4){
				alert('비밀번호가 4글자 보다 적습니다.');
				$('#password1').focus();
				return false; // submit을 취소하는 방법, 실제 서버로 전달되지 않음
			}
			
			// 유효성 검사로직을 추가하는 부분
			
			return true; // submit이 전송되는 부분			
		});
		
		$('#checkDuplicate').click((e)=>{
			let id = $('#id').val().trim();
			
			if(id.length < 4){
				alert('아이디가 4글자보다 적습니다.');
				return;
			}
			
			$.ajax({
				type:'get',
				url:'${path}/member/idCheck',
				data:{id},
				success:(result)=>{
						if(result.validate === true){
							alert('이미 사용중인 아이디 입니다.');
							$('#id').focus();
						}else{
							alert('사용 가능한 아이디 입니다.');
						}
					},
				error:(e)=>{
					alert('중복을 확인할수 없습니다.');
					console.log(e);
				},
			});
		});
		
	});
</script>











