package com.multi.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletecookie.do")
public class DeleteCookieSetvlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 쿠키를 삭제하는 방법 : 삭제 메소드는 존재하지 않는다.
		// -> 덮어쓰기와 setAge(0)을 통해 시간으로 초기화 시킨다.
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			c.setValue(""); // 데이터를 비우고
			c.setMaxAge(0); // 살아있는 시간을 0으로 설정
			resp.addCookie(c);
		}
		
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().append("<script> alert('쿠키가 삭제되었습니다.'); "
				+ "location.href='" + req.getContextPath()+ "' </script>");
	}

}
