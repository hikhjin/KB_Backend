<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>파라메터 처리법</h3>
	※ 주의 : JSP에서 직접 파라메터를 받아 처리하는 설계를 만들면 안된다!! (MVC2 패턴 위배) <br>
	 -> Controller(Servlet)에서 처리하고, View(JSP)는 데이터만 받아서 출력하는 설계 필요 = MVC2 패턴<br>
	 -> 단, 선택된 파라메터를 다시 출력하는 페이지를 만들때 param을 사용해야 함 <br>
	 - param : 해당 페이지 요청시 전달된 파라메터를 가져오는 객체 이름 <br>
	 - paramValues : 해당 페이지 요청시 전달된 [여러] 파라메터들을 가져오는 객체 이름 <br>
	 <hr><br>
	 
	 <h3>주문 내역</h3>
	 - 상품명 : ${pName} <br> <!--그냥은 안된다!!-->
	 - 상품명 : ${param.pName} <br> <!-- input 태그에 name속성 값 가져오기 -->
	 - 수량 : ${param.pNumber} <br>
	 - 옵션 : ${param.option} <br> <!-- 여러개가 있어도 하나의 속성값만 가져옴 -->
	 
	 <!-- paramValues로 옵션값 가져오기 -->
	 - 옵션1 : ${paramValues.option[0]} <br>
	 - 옵션2 : ${paramValues.option[1]} <br>
	 - 옵션3 : ${paramValues.option[2]} <br>
	 - 옵션4 : ${paramValues.option[3]} <br>
	 - 옵션5 : ${paramValues.option[4]} <br>

</body>
</html>