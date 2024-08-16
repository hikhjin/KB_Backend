package com.multi.data.controller.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/useData.do")
public class UseDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UseDataServlet - doGet 호출됨");
		ServletContext context = getServletContext();
		HttpSession session = req.getSession();
		
		String contextMsg = (String) context.getAttribute(ShareDataServlet.CONTEXT_ID1);
		String sessionMsg = (String) session.getAttribute(ShareDataServlet.SESSION_ID);
		String requestMsg = (String) req.getAttribute(ShareDataServlet.MSG_ID);

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String html = "<html>";
		html += "<body>";
		html += "<ul>";
		html += "<li>contextData : " + contextMsg + "</li>";
		html += "<li>sessionData : " + sessionMsg + "</li>";
		html += "<li>requestData : " + requestMsg + "</li>";
		html += "</ul>";
		html += "<button onclick='location.assign(\"./sessiondel.do\");'>session삭제</button>";
		html += "</body>";
		html += "</html>";
		out.append(html);
	}
}







