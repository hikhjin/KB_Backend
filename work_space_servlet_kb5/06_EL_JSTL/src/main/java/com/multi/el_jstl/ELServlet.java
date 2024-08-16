package com.multi.el_jstl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/el.do")
public class ELServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Scope (Page, Request, Session, Application) 별로 객체를 만들어 전달할 예정
		
		// Page Scope : 현재 페이지(서블릿)에서만 활용됨으로 전달되지 않음
		// -> 선언 하지 않음
		
		// Request Scope -> 가장 일반적으로 JSP페이지로 파라메터를 넘길때 사용됨
		// - 생명주기 : Client 요청 -> Servlet -> JSP
		// - 단, forward로 전달할 경우만 JSP로 전달됨
		req.setAttribute("member", new Member("홍길동", 31, "Request"));
		req.setAttribute("scope", "Request 영역");
		
		// Session Scope 
		// - 생명주기 : Client의 Session ID가 생성되고 소멸될때 까지 (임의 제거, Time out)
		// - 특징은 SendRedirect를 전달해도 데이터가 유지된다.
		HttpSession session = req.getSession();
		session.setAttribute("member", new Member("최길동", 21, "Session"));
		session.setAttribute("scope", "Session 영역");
		
		// Application Scope
		// - 생명주기 : 서버(Tomcat)이 살아 있을동안 유지 된다.
		ServletContext context = getServletContext();
		context.setAttribute("member", new Member("박길동", 61, "Application"));
		context.setAttribute("scope", "Application 영역");
		
		// sendRedirect 사용법 : request 전달되지 않고 페이지 재요청 메커니즘
//		resp.sendRedirect(req.getContextPath() + "/views/01_EL/01_el.jsp");
		
		// forward 사용법 : 서블릿에서 JSP로 넘길때 추로 사용되는 방식
		// 앞에 '/'가 붙으면 절대 경로로 지정됨, root = webapp 폴더가 됨
		req.getRequestDispatcher("/views/01_EL/01_el.jsp").forward(req, resp);
	}

}








