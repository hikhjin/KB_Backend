<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-latest.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>AJAX 실습</title>
	<style>
        div {
            height: 200px;
            width: 100%;
            border: 1px solid black;
            margin-top: 3px;
        }
    </style>
</head>
<body>
	<h2>AJAX 실습</h2>
    <hr>
    <h3>JavaScript를 활용한 AJAX 실습</h3>
    <h3>1. GET 방식을 통한 서버 송수신 방법</h3>
    <button onclick="ajaxTest1()">get 방식 전송</button>
    <div id="div1">
    </div>
    <script>
		function ajaxTest1(){
			
			// 1. xhr 객체 생성
			let xhr = new XMLHttpRequest();

			// 2. onreadystatechange 이벤트 리스너 함수 생성(콜백 함수)
			//   -> 서버로 요청이 가고, 응답을 전달 받은 경우 호출되는 함수 -> 성공인지 실패인지는 안에서 다시 판단
			xhr.onreadystatechange = function(){
				console.log('readyState : ' + xhr.readyState);
				console.log('status : ' + xhr.status);

				if(xhr.readyState == 4){ // 4 : 요청은 성공한 상태
					if(xhr.status == 200){ // 200 : HTTP 응답코드로 응답이 성공한 경우
						let str = xhr.responseText; // 응답 결과
						document.getElementById('div1').innerHTML += '응답 결과<br>' + str + '<br>';
					} else{
						 // 응답 결과가 좋지 않을때, 
                        // 404 : not found, 500 : 서버에러, 403 : forbidden= get/post
                        document.getElementById('div1').innerHTML += '에러 발생!' + '<br>';
					}
				} else{
					// 요청 자체가 실패한 경우! ex) Client 네트워크 에러, URL이 잘못된 경우 등
					// alert('인터넷 오류 발생!');
				}
			}

			// 3. xhr open 시 필요한 정보 셋팅
			let url = '${path}/jsAjax.do';
			let getParameter = '?name=홍길동&age=31';
			url += getParameter;

			// 4. xhr open 시도, 연결이 실패하면 에러 발생!
			//open(method: string, url: string, async(비동기 여부): boolean)
			xhr.open('get', url, true);

			// 5. xhr 전송
			xhr.send(); // 실제 get요청이 날라가는 시점
		}
	</script>
    <hr><br>

	<h3>2. POST 방식을 통한 서버 송수신 방법</h3>
    <button onclick="ajaxTest2();">Post 방식 전송</button>
    <div id="div2"></div>
	<script>
		function ajaxTest2(){
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				document.getElementById('div2').innerHTML = 'status : ' + xhr.status + '<br>';
				// 정상 응답인 경우
				if(xhr.readyState == 4 && xhr.status == 200){
					let str = xhr.responseText; // 응답 결과
					document.getElementById('div2').innerHTML += '응답 결과<br>' + str + '<br>';
				}
			}
			let url = '${path}/jsAjax.do';
			let parameter = 'name=최길동&age=21'; // get하고 다른점은 반드시 '?' 제거 할 것!
			xhr.open('post', url, true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send(parameter);
		}
	</script>
	<hr><br>
	
	
    <h2>Jquery 방식의 AJAX 실습</h2>

    <h3>1. get 방식 서버 요청</h3>
    <button id="ajaxSend1"> GET방식 전송</button>
    <p> 메세지 : <input type="text" id="input1" /></p>
    <div id="div3"></div>
	<script>
		$('#ajaxSend1').click(
			()=>{
				let inputValue = $('#input1').val();

				// ajax 전송부
				$.ajax({
					// 객체로 값들이 들아가면 된다.
					// 1. 전송 type : get(default), post
					type:'get',
					// 2. URL(서버 주소)
					url:'${path}/jqAjax1.do',
					// 3. 파라메터 = data, 서버로 전송될 데이터
					data:{
						inputValue : inputValue
					},
					// 4. success 리스너(이벤트 핸들러) 등록, 데이터 전달 및 응답이 성공하였을 때
					success: (result)=>{
						$('#div3').html(result);
					},
					// 5. error 리스너 등록, 에러 발생했을대 호출되는 함수를 미리 정의
					error:(e)=>{
						$('#div3').html('에러 발생!');
						console.log(e);
					}
				});
			}
		);
	</script>
	<hr><br>

	<h3>2. POST 방식을 통한 서버 송수신 방법</h3>
    <button id="ajaxSend2"> POST 방식 전송</button>
    <p> 이름 : <input type="text" id="input2_name" /></p>
    <p> 나이 : <input type="text" id="input2_age" /></p>
    <div id="div4"></div>
	<script>
		$('#ajaxSend2').click(()=>{
			let name = $('#input2_name').val(); 
			let age = $('#input2_age').val(); 

			$.ajax({
				type:'post',
				url:'${path}/jqAjax2.do',
				data :{
					// name:name,
					// age:age,
					name, // key-value가 같으면 한번만 쓰면 된다. (이름이나 데이터 영역이 생략)
					age,
				},
				success:(result)=>{
					$('#div4').html(result);
				},
				error:(e)=>{
					$('#div4').html('에러 발생!');
					console.log(e);
				}
			});
		});
	</script>
	<hr><br>

	<h3>3. JSON 객체로 응답 받는 방법 - simplejson parser</h3>
    <button id="ajaxSend3"> 객체 응답받기</button>
    <div id="div5"></div>
	<script>
		$('#ajaxSend3').click(()=>{
			$.ajax({
				type:'get',
				url:'${path}/jqAjax3.do',
				dataType:'json', // 실제 전달받을 데이터의 양식을 정의, default : text, json -> text를 json로 parse준다.
				success:(obj)=>{
					// {"no":100,"gender":남,"name":"홍길동","age":25}
					str = '<h3>회원정보</h3>'
						+ '번호 : ' + obj.no  +'<br>'
						+ '이름 : ' + obj.name  +'<br>'
						+ '나이 : ' + obj.age  +'<br>'
						+ '성별 : ' + obj.gender  +'<br>';
					$('#div5').html(str);
				},
				error:(e)=>{
					$('#div5').html('에러 발생!');
					console.log(e);
				}
			});
		});
	</script>
	<hr><br>
	
	<h3>4. JSON 객체 '리스트'로 응답 받는 방법</h3>
    <button id="ajaxSend4"> 객체 응답받기</button>
    <p id="div6"></p>
	<script>
		$('#ajaxSend4').click(()=>{
			$.ajax({
				type:'get',
				url:'${path}/jqAjax4.do',
				dataType:'json',
				success : (list)=>{
					str = '';
					$.each(list, (i, obj)=>{
						str += (i+1) + '번째 회원정보 <br>'
							+ '번호 : ' + obj.no +'<br>'
							+ '이름 : ' + obj.name +'<br>'
							+ '나이 : ' + obj.age +'<br>'
							+ '성별 : ' + obj.gender+'<br><br>'
					});
					$('#div6').html(str);
				},
				error:(e)=>{
					alert('에러 발생!!');
				}
			});
		});
	</script>
	<hr><br>

    <h3>5. 'GSON' 객체 '리스트' 수신하는 방법 - 테이블로 만들어보기</h3>
    <p> count : <input type="text" id="count" value="5" /></p>
    <button id="ajaxSend5"> page로 요청하기</button>
    <p id="div7"></p>
	<script>
		$('#ajaxSend5').click(()=>{
			let count = $('#count').val();
			
			$.ajax({
				type:'get',
				url:'${path}/jqAjax5.do',
				dataType:'json',
				data : {count},
				success : (list) =>{
					str = ''
					    + '<table border="1">   '
					    + '<tr>	 					    '
					    + '	<th>No</th>'
					    + '	<th>회원번호</th>'
					    + '	<th>이름</th>'
					    + '	<th>나이</th>'
					    + '	<th>성별</th>'
					    + '</tr>';
					$.each(list, (i, obj)=>{
						str += ''
							+ '<tr>'
							+ '	<td>' + (i + 1) + '</td>'
							+ '	<td>' + obj.no + '</td>'
							+ '	<td>' + obj.name + '</td>'
							+ '	<td>' + obj.age + '</td>'
							+ '	<td>' + obj.gender + '</td>'
							+'</tr>';
					});

					str += '</table>';
					$('#div7').html(str);
				},
				error:(e)=>{
					alert('에러 발생!');
				}
			});
		});
	</script>
	<hr><br>

	<h3>6. Spring 내장 jackson을 활용 (ResponseEntity 활용) - Member 테이블로 만들어보기</h3>
	Count : <input type="text" id="count2" value="5" /> <br>
	Name : <input type="text" id="name" value="최길동" /><br>
	age : <input type="text" id="age" value="20" /><br>
	<button id="ajaxSend6"> 요청하기</button>
	<p id="div8"></p>
	<script>
		$('#ajaxSend6').click(()=>{
			let count = $('#count2').val();
			let name = $('#name').val();
			let age = $('#age').val();

			$.ajax({
				type:'get',
				url:'${path}/jqAjax6.do',
				dataType:'json',
				data:{count, name, age},
				success:(result)=>{
					str = '<h3>' + result.title + '</h3>'
					    + '<table border="1">'
					    + '<tr>'
					    + '	<th>No</th>'
					    + '	<th>회원번호</th>'
					    + '	<th>이름</th>'
					    + '	<th>나이</th>'
					    + '	<th>성별</th>'
					    + '</tr>';
					$.each(result.list, (i, obj)=>{
						str += ''
							+ '<tr>'
							+ '	<td>' + (i + 1) + '</td>'
							+ '	<td>' + obj.no + '</td>'
							+ '	<td>' + obj.name + '</td>'
							+ '	<td>' + obj.age + '</td>'
							+ '	<td>' + obj.gender + '</td>'
							+'</tr>';
					});

					str += '</table>';
					$('#div8').html(str);
				},
				error:(e)=>{
					alert('에러 발생!');
				}
			});
		});


	</script>
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>

