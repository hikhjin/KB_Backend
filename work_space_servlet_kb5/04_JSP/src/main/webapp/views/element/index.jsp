<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>JSP 스크립트 요소(element) 실습</h1>
    <!-- HTML 주석 : 일반 브라우저에서 코드보기로 노출되는 주석 -->
    <%-- JSP 주석 : JSP에서만 보여지고, java코드나 html로는 노출되지 않는 주석 --%>

    <!-- 선언부 사용법 -->
    <%! // 멤버 변수나 멤버 메소드 영역을 선언하여 사용할수 있다. -> 권장하지 않는다.
        public static final String TEST_ID = "test12345";
        public String name = "홍길동";
        public String address = "서울시 강남구 역삼동";

        public String getName(){
            return name;
        }
    %>
    <hr><br>
    <%-- 출력부  --%>
    <%-- 1. 변수 이름으로 출력(가장 많이 활용) --%>
    이름 : <%=name%> <br>
    주소 : <%=address%> <br><br>

    <%-- 2. 표현식을 통해 문자열을 결합하는 방법 --%>
    <%="이름 : " + getName() + "<br>"%>
    <%="주소 : " + address + "<br><br>"%>

    <%-- 3. 스크립트식 + out 객체를 통해 출력하는 방법 --%>
    <% out.println("이름 : " + name +"<br>"); %>

    <%-- 4. java문자열 변수에 출력할 내용을 넣고 출력하는 방법 --%>
    <% String printStr = "이름 : " + name + "<br>"+"주소 : " + address+"<br>"; %>
    <div>
        <%=printStr%>
    </div>
    <hr><br>

    <!--일반 스크립트식(스크립틀릿식) 활용법-->
    <h3>스크립트식 연습</h3>
    <%
        // 이 공간은 java 문법이 먹히는 구간 -> 최근에는 권장하지 않는 문법!!
        if(name.equals("홍길동")){
            out.println("<h3>홍길동이 맞습니다.</h3>");
        } else{
            out.println("<h3>홍길동이 아닙니다.</h3>");
        }
    %>

    <!-- JSP 스타일 1. 정적 HTMl 표현식 활용 -->
    <%
        if(name.equals("홍길동")){
    %>
            <h3>홍길동이 맞습니다.</h3>
    <%
        } else{
    %>
            <h3>홍길동이 아닙니다.</h3>
    <%
        }
    %>

    <!-- JSP 스타일 2. 동적 HTML 표현식 사용 -->
    <%if(name.equals("홍길동")) { %>
    <h3><%=name%> 운영자님 환영합니다.</h3>
    <%} else { %>
    <h3><%=name%>님 환영합니다. 홍길동님이 아닙니다.</h3>
    <%} %>
    <hr><br>

    <!-- 게시판 예시 -->
    <style>
        td{
            text-align: center;
        }
    </style>
    <h3>게시판</h3>
    <table border="1">
        <!-- 헤더부 -->
        <tr>
            <th width="50px">번호</th>
            <th width="300px">게시글 제목</th>
            <th width="100px">작성자</th>
            <th width="100px">작성일</th>
            <th width="50px">조회수</th>
        </tr>
        <!-- 헤더부 끝 -->
        <%for(int i = 0; i < 10; i++){%>
            <!-- 반복문 영역! -->
            <tr>
                <td><%=i + 1%></td>
                <td><a href="board?no=<%=i%>"><%="게시글 제목 " + (i + 1)%></a></td>
                <td><%="홍길동" + (i + 1)%></td>
                <td><%="24-07-" + (i + 1)%></td>
                <td><%=(i + 100)%></td>
            </tr>
        <%}%>
    </table>

    <!-- 페이징 처리 -> 반복문 a태그로 '1 2 3 4 5 ...' -->
    <div style="width:100%; text-align: center;" >
        <a href="board?page=<%=1%>">&lt;</a>
        <%for(int i = 0; i < 5; i++){ %>
        <a href="board?page=<%=i+1%>"><%=i+1%></a>
        <%} %>
        <a href="board?page=<%=6%>">&gt;</a>
    </div>

    <br><br><br><br><br><br>
    <br><br><br><br><br><br>


</body>
</html>
