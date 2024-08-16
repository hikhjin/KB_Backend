<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="../error/error.jsp"%>

    <%
        // 에러 테스트
//        String str = null;
//        str.length();
    %>

    <%@include file="/views/common/header.jsp"%>

    <section>
        <h4>회원가입</h4>
        <form action="<%=request.getContextPath()%>/views/directive/index.jsp" method="POST">
            <label>아이디 : <input type="text" name=id size="10"/></label><br>
            <label>비번 : <input type="text" name="pwd" size="10"/></label><br>
            <label>이름 : <input type="text" name="name" size="10"/></label><br>
            <label>나이 : <input type="text" name="age" size="10"/></label><br>
            <label>성별 : </label>
            <label><input type="radio" name="gender" value="남자"/>남자</label>
            <label><input type="radio" name="gender" value="여자"/>여자</label><br>
            <label>키 : <input type="range" name="height" min="140" max="190" step="1"/></label><br>

            <label>취미 : </label>
            <label><input type="checkbox" name="hobby" value="축구"/>축구</label>
            <label><input type="checkbox" name="hobby" value="야구"/>야구</label>
            <label><input type="checkbox" name="hobby" value="게임"/>게임</label>
            <label><input type="checkbox" name="hobby" value="영화보기"/>영화보기</label>
            <label><input type="checkbox" name="hobby" value="음악감상"/>음악감상</label>
            <br><br>
            <input type="submit" value="확인">
            <input type="reset" value="취소">
        </form>
    </section>

    <%@include file="/views/common/footer.jsp"%>
