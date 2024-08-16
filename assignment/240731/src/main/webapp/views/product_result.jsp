<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>product 정보 처리 결과를 담는 페이지</title>
</head>
<body>
<br>
<hr color="blue">

<h3>insert 정보</h3>
insertion product : <%= request.getAttribute("product")%>

<hr color="red">

<h3>delete 정보</h3>
deletion product_id : <%= request.getAttribute("product_id")%>
</body>
</html>
