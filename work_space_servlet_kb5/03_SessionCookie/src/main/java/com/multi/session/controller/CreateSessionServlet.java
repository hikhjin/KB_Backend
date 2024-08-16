package com.multi.session.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 세션이란?
 * - HTTP 프로토콜 중 하나로, 서버와 클라이언트에서 Session ID를 통해 사용자 정보를 저장하는 용도로 활용
 * - 저장 기간 : 클라이언트 (브라우저)가 종료되었을때 세션 해지, 서버가 지정한 시간의 time out 시
 * - 저장 위치 : 클라이언트 일부(Session ID만), 서버의 메모리 공간 / 디스크 공간 (임시적으로 생성됨)
 * - 특징 : 쿠키로 Session ID만 저장함으로 쿠키 보단 보안적으로 안전하다.
 * - 단점 : 서버에 데이터를 저장함으로 용량을 많이 차지하고 다중 서버 구성시 Session 관련 추가 설계 필요
 */

@WebServlet("/createsession.do")
public class CreateSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 생성하는 방법

        // 1. req 객체에서 session 가져오기
        HttpSession session = req.getSession(); // 세션이 없으면 새로운 세션을 만들어 반환하는 방법
//        HttpSession session = req.getSession(false); // 세션이 없으면 null을 반환하는 메소드
//        if(session == null){
//            // 최초로 접근했을때 ....
//        }

        String sessionId = session.getId(); // 쿠키로 저장될 클라이언트의 고유 ID
        System.out.println("sessionId : " + sessionId);

        // 2. 세션에 데이터 넣는 방법 (객체로 저장함으로, 숫자 문자 객체 모두 OK)
        session.setAttribute("SESSION_ID", "test12345");
        session.setAttribute("SESSION_NAME", "최길동");
        session.setAttribute("SESSION_DATE", new Date());
        
        // 3. 세션 시간 설정하기 (옵션, default : 30분)
        session.setMaxInactiveInterval(60); // 초단위, 60초 셋팅


        // 응답페이지 작성하기
        resp.setContentType("text/html;charset=utf-8");
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append("<body>");
        sb.append("<p>세션이 생성했습니다.</p>");
        sb.append("<p>ID : "+sessionId+"</p>");
        sb.append("<a href = 'checksession.do'>저장된 세션 확인</a>");
        sb.append("</body>");
        sb.append("</html>");

        resp.getWriter().append(sb.toString());
    }
}












