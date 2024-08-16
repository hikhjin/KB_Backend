package com.multi.session.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/checksession.do")
public class CheckSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session이 있을때 확인하는 방법
		// getSession(true) : session이 있으면 세션을 가져오고, 없으면 세션을 생성함 (default)
		// getSession(false) : session이 있으면 세션을 가져오고, 없으면 null로 반환됨
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			System.out.println("세션이 없습니다!");
			resp.getWriter().append("No Session!");
			return;
		}
		
		// 세션값 확인하는 방법
		String userID = (String) session.getAttribute("SESSION_ID");
		String userName = (String) session.getAttribute("SESSION_NAME");
		Date date = (Date) session.getAttribute("SESSION_DATE");
		String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		
		// 2. 응답 페이지 작성
		StringBuffer sb = new StringBuffer();
		
		sb.append("Session ID : " + session.getId() +"<br>");
		sb.append("User ID : " + userID +"<br>");
		sb.append("User Name : " + userName +"<br>");
		sb.append("date : " + dateStr +"<br>");
		sb.append("<a href='deletesession.do'>세션 삭제</a><br>");
		sb.append("<a href='"+req.getContextPath()+"'>메인 페이지 이동</a>");
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(sb.toString());
	}

}




















