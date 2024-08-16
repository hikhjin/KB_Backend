<%@ page import="com.multi.el_jstl.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 커스텀 라이브러리 사용하기 위한 선언문 시작 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <c:set var="path" value="${pageContext.request.contextPath}"/> --%>
<!-- prefix : 접두어, 태그를 호출시킬 이름 -->
<!-- uri : 태그 정보를 식별하기 위한 식별자, uri를 통해 jar 파일과 링크 가능 -->
<!-- 커스텀 라이브러리 사용하기 위한 선언문 종료 -->

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>JSTL Core Library</h2>
    <hr><br>

    <h3>1. 변수</h3>
    <h3>1.1 변수 선언 태그</h3>
    - 변수를 선언하고 초기값을 대입할수 있는 기능 제공<br>
    - 변수 선언시 scope를 지정할수 있으며, 지정하지 않은 경우 page scope로 지정된다. (page scope : 일회용) <br><br>

    ■ 사용법 <br>
    - 변수의 타입은 별도로 선언 할 수 없다. (문자열이 기본)<br>
    - 변수의 초기값은 반드시 선언이 필요 (안하면 에러 발생)<br>
    - c:set 태그를 사용하고, 선언된 변수는 EL로 사용 가능하다. <br>
    ※ JSTL 선언 한 변수는 스클립틀릿으로 그냥 사용할수 없다. (방법은 있는데 하지 말 것!)<br><br>

    ■ 실습부 <br>
    <c:set var="num1" value="100"/> <!-- 범위를 생략하는 경우 page 영역 -->
    <c:set var="num2" value="200" scope="request"/>
    <c:set var="num3" value="${num1 + num2}" scope="session"/>
    <c:set var="num4" value="300.12345"/>
    <c:set var="str" value="안녕하세요"/>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <!-- 배열 선언법 -->
    <c:set var="arr" scope="request">
        red, green, blue, yellow, pink
    </c:set>

    1) JSTL로 선언된 변수는 EL로 활용하기 <br>
    num1 : ${num1}, ${pageScope.num1} <br>
    num2 : ${num2}, ${requestScope.num2} <br>
    num3 : ${num3}, ${sessionScope.num3} <br>
    str : ${str}, ${pageScope.num1} <br>
<%--    str+num1 : ${str + num1} 문자열+숫자 결합하면 에러발생 <br>--%>

    2) 스클립틀릿으로 가져오기 (권장하지 않는다!!) <br>
    num1 : <%=pageContext.getAttribute("num1")%> <br>
    num2 : <%=request.getAttribute("num2")%> <br>
    num3 : <%=session.getAttribute("num3")%> <br>
    str : <%=pageContext.getAttribute("str")%> <br><br>


    3) 응용 예제 <br>
    ${num1} + ${num2} = ${num3} <br>
    path : ${path} <br>
    배열 : ${arr}<br>
    <hr>

    <h3>3. 변수 삭제 (c:remove)</h3>
    - 사용된 변수를 페이지에서 삭제하는 방법 (scope 지정해서 삭제 가능)<br>
    - page에서 사용된 변수는 jsp가 렌더링 되고 메모리에서 정리됨으로 특별히 삭제할일 없음<br>
    <c:set var="result1" value="1000" scope="request"/>
    <c:set var="result2" value="2000" scope="session"/>
    삭제 전 : ${result1} <br>
    삭제 전 : ${result2} <br>

    <!-- scope를 지정하지 않으면 알아서 scope를 찾아 삭제해줌 -->
    <c:remove var="result1"/>
    <c:remove var="result2" scope="session"/>
    삭제 후 : ${result1} <br>
    삭제 후 : ${result2} <br><br>
    ※ EL의 장점 : null point exception이 발생하지 않는다!, null도 노출하지 않는다.<br><br>
    <hr><br>


    <h3>3. 변수 출력 (c:out)</h3>
    - 데이터나 특수문자가 포함된 문자열을 출력할 때 사용 <br>
    - 특수문자 '>, <, &' 등의 특수문자를 자동으로 이스케이프 시퀀스(&lt;)로 변환 <br>
    - 문법적으로 c:out을 사용해서 html 코드와 el과 섞이지 않게끔 분리하는 스타일 <br><br>

    3.1 HTML 태그를 문자열로 출력하는 방법<br>
    -> 특수문자들이 자동으로 이스케이프 시퀀스로 변환되어 원본을 볼수 있다. <br>
    ${"<b> b태그 입니다. 굵은 글씨 변환 </b>"} <br> <!-- el만 사용하면 태그가 노출된다. -->
    <c:out value="<b> b태그 입니다. 굵은 글씨 변환 </b>"/> <br>
    <c:out value="<b> b태그 입니다. 굵은 글씨 변환 </b>" escapeXml="true"/> <br><br> <!--default 옵션과 같은 값-->

    3.2 HTML 노출하는 방법<br>
     -> el로 출력하거나 escapeXml를 false로 설정한다. <br>
    <c:out value="<b> b태그 입니다. 굵은 글씨 변환 </b>" escapeXml="false"/> <br><br>

    3.3 null 값 대신 들어갈 default 텍스트를 사용하는 방법 <br>
    <c:set var="result" value="예시용 텍스트 입니다."/>
    ${result}<br>
    <c:out value="${result}"/> <br><!-- 실제 필드에서 관례적으로 사용하는 문법 -->
    <c:remove var="result"/> 삭제 이후 <br>
    <c:out value="${result}"/><br>
    <c:out value="${result}" default="빈 문자열"/><br>
    <hr><br>


    <h3>4. 조건문</h3>
    <h3>4.1 if문 (c:if)</h3>
    - 가장 기본적인 조건문, 태그 속성 값에 test를 통해 조건을 설정할수 있음 <br>
    - 조건식으로 EL 표기법을 혼합하여 사용해야한다. <br>
    - else, else if문이 없다. -> choose 문법을 쓰면 된다.<br><br>

    <!-- 문자열 값으로 대소 비교시 문자열 비교가 됨 (숫자의 대소 비교가 아님!) -->
    <c:set var="num1" value="10"/>
    <c:set var="num2" value="20"/>

    <c:if test="${num1 > num2}">
        num1이 num2보다 큽니다.<br>
    </c:if>

    <c:if test="${num1 < num2}">
        num1이 num2보다 작습니다.<br>
    </c:if>

    <c:set var="name" value="홍길동"/>
    <!-- 문자열 비교 실습 -->
    <c:if test="${name == '홍길동'}">
        ${name}은 홍길동이 맞습니다. <br>
    </c:if>

    <!-- null 체크법 -> 아래 문법처럼 권장하지 않음! -->
    <c:if test="${name != null }">
        ${name}은 널이 아닙니다!<br>
    </c:if>
    <c:if test="${name == null}">
        ${name}은 널입니다.<br>
    </c:if>

    <!-- null 체크 정석적인 문장 ★★★★★★-->
    empty를 권장, 이유 : null 체크와 동시에 length가 0인것도 같이 체크한다. <br>
    <c:if test="${not empty name}">
        ${name}은 널이 아닙니다!<br>
    </c:if>
    <c:if test="${empty name}">
        ${name}은 널입니다.<br>
    </c:if>


    <h3>4.2 Choose 문법 (c:choose, c:when, c:otherwise)</h3>
    - if/else/switch 까지 문법이 호환되는 기능 <br>
    - 사용법은 밖을 choose로 감싸고, 조건을 when, 기타(default)를 otherwise로 처리<br><br>

    <!-- 시험 성적별로 등급을 출력하는 코드, A=90점, B=80점, C=70점 이하는 F -->
    <c:set var="scoreA2" value="100"/>
    <c:set var="scoreA" value="90"/>
    <c:set var="scoreB" value="80"/>
    <c:set var="scoreC" value="70"/>
    <c:set var="inputScore" value="80"/>

    <c:choose>
        <c:when test="${inputScore >= scoreA || inputScore == scoreA2}">
            A학점입니다. <br>
        </c:when>
        <c:when test="${inputScore >= scoreB}">
            B학점입니다. <br>
        </c:when>
        <c:when test="${inputScore >= scoreC}">
            C학점입니다. <br>
        </c:when>
        <c:otherwise>
            F학점입니다. <br>
        </c:otherwise>
    </c:choose>
    <hr><br>

    <h3>5. 반복문</h3>
    <h3>5.1 forEach (c:forEach)</h3>
    - 반복할 때 사용한다. 그리고 유일한 반복 수단이다. <br>
    <%-- 문법 <c:forEach (속성값 ...) > 반복시 실행할 내용 </c:forEach> --%>
    ■ forEach의 주요 속성<br>
    1) begin : index의 시작을 의미 <br>
    2) end : index의 끝을 의미<br>
    3) step : 증감할 값<br>
    4) items : 컬랙션이나 배열의 변수명을 지정가능 <br>
    5) var : items과 짝으로 item에 대한 변수명을 지정하는 방법 (혹은 일반 index로 i로 사용 가능) <br>
    6) varStatus : 현재 반복되는 상태 값을 가져옴 (index, size, item 등등) <br>
    <hr><br>

    1) 1부터 10까지 반복하는 반복문 <br>
    <c:forEach var="i" begin="1" end="10">
        출력 : ${i}<br>
    </c:forEach>

    2) 1부터 6까지 증감하는 반복문 - html과 같이 사용할 예정... <br>
    <c:forEach var="i" begin="1" end="6">
        <h${i}> h${i}태그 : 1이 점점 작아지네... </h${i}>
    </c:forEach>
    <br><br>

    3) 6부터 1까지 감소하는 반복문 - html과 같이 사용할 예정... <br>
    <c:forEach var="i" begin="1" end="6">
        <h${7-i}> h${7-i}태그 : 1이 점점 커지네! </h${7-i}>
    </c:forEach>
    <br><br>

    4) 2부터 10까지 2씩 증감하는 반복문<br>
    <c:forEach var="i" begin="2" end="10" step="2">
        출력 : ${i} <br>
    </c:forEach>
    <br><br>


    5) Array(컬렉션)을 순회하면서 CSS 설정하기<br>
    <c:set var="array">
        red, green, blue, yellow, pink
    </c:set>
    <!-- items는 el표기법으로 표현해야 정상적으로 작동 -->
    <c:forEach var="color" items="${array}">
        <div style="background:${color}">${color}</div>
    </c:forEach>
    <br><br>

    6) 컬렉션 활용 예제 (테이블 태그 활용) <br>
    <%
        List<Member> list = new ArrayList<>();
        list.add(new Member("홍길동", 31, "서울시 강남구 역삼동"));
        list.add(new Member("김길동", 33, "서울시 강남구 도곡동"));
        list.add(new Member("최길동", 33, "서울시 강남구 논현동"));
        list.add(new Member("박길동", 45, "서울시 강남구 역삼동"));
        list.add(new Member("고길동", 22, "서울시 강남구 역삼동"));
        pageContext.setAttribute("list", list);
    %>
    <table border="1">
        <tr>
            <th>index</th>
            <th>count</th>
            <th>first</th>
            <th>last</th>
            <th>이름</th>
            <th>나이</th>
            <th>주소</th>
        </tr>
        <c:forEach var="item" items="${list}" varStatus="status">
            <!--홀짝 구분하는 코드 -->
            <c:if test="${status.count % 2 == 0}">
                <tr style="background:pink">
            </c:if>
            <c:if test="${status.count % 2 != 0}">
                <tr style="background:skyblue">
            </c:if>
                <td>${status.index}</td>
                <td>${status.count}</td>
                <td>${status.first}</td>
                <td>${status.last}</td>
                <!-- 이름이 홍길동이면 bold + color 처리 -->
                <c:if test="${item.name == '홍길동'}">
                    <td><b style="color:blue;">${item.name}</b></td>
                </c:if>
                <c:if test="${item.name != '홍길동'}">
                    <td>${item.name}</td>
                </c:if>
                <td>${item.age}</td>
                <td>${item.scope }</td>
            </tr>
        </c:forEach>
    </table>

    <h3>5.2 forTokens (c:forTokens) - ☆비추</h3>
    - 문자열의 구분자를 통해 문자를 분리하는 반복문<br>
    - items : 문자열이나 토큰을 가지고 있는 변수를 지정<br>
    - delims : 딜리미터(구분자)로 문자열을 분리할 문자나 문자열을 지정<br><br>

    1) "pink aqua tomato yellowgreen lime" 분리하여 출력하기<br>
    <c:forTokens var="color" items="pink aqua tomato yellowgreen lime" delims=" ">
        <div style="background-color: ${color}">현재 색상 : ${color}</div>
    </c:forTokens>
    <br>

    2) 문자열을 ', /.'로 분리하기<br>
    - 문자열의 공백이 있는 경우 공백을 딜리미터로 추가하면 자동으로 없애줌<br>
    -> 그럴일 없도록 미리 strip으로 공백을 제거하는 것이 상책<br>
    <c:set var="hobbies">
        게임 ,야구 , 축구 ,영화보기,음악감상,스키/보드,넷플릭스.디지니,맛집탐방
    </c:set>
    <c:forTokens var="item" items="${hobbies}" delims=",./ ">
        취미 : ${item} <br>
    </c:forTokens>
    <hr><br>

    <h3>6. URL</h3>
    - URL의 경로를 생성하고 param값(get방식 : ?id=test&pw=1234)을 생성하는 태그<br>
    <c:url var="url" value="/views/01_EL/02_elParam.jsp">
        <c:param name="pName">아이폰14</c:param>
        <c:param name="pNumber">3</c:param>
        <c:param name="option">스페이스그레이</c:param>
        <c:param name="option">256GB</c:param>
        <c:param name="option">SKT</c:param>
    </c:url>
    <a href="${url}">링크로 이동!</a>

    <br><br><br><br><br><br><br><br><br><br><br><br><br>


    <br><br><br><br><br><br>
    <br><br><br><br><br><br>
</body>
</html>















