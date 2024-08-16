package com.multi.cookie.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkcookie.do")
public class CheckCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 쿠키 읽어오는 법

        // 1. request 객체로 부터 쿠키 배열 읽어오기
        Cookie[] cookies = req.getCookies();
        Map<String, Cookie> map = new HashMap<>();

        // 2. 쿠키 탐색하기
        for(Cookie cookie : cookies) {
            System.out.println("name : " + cookie.getName() + ", value : " + cookie.getValue());
            map.put(cookie.getName(), cookie);
        }
        
        // 3. 응답페이지 구성
        String userId = null;
        String userName = null;
        try {
            userId = map.get("COOKIE_ID").getValue();
            userName = map.get("COOKIE_NAME").getValue();
        } catch (Exception e) {}

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();
        out.append("<html><body>");
        out.append("User ID : " + userId +"<br>");
        out.append("User Name : " + userName +"<br>");
        out.printf("<a href='%s'>쿠키 삭제</a><br>", "deletecookie.do");
        out.printf("<a href='%s'>메인 페이지</a><br>", req.getContextPath());
        out.append("</body></html>");
    }
}

