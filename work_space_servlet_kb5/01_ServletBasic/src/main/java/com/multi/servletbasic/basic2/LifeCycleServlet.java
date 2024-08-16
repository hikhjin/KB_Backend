package com.multi.servletbasic.basic2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * LifeCycle이란?
 * - JVM내에서 Class(객체, 인스턴스)가 어떻게 생성되고 소멸하는지에 대한 흐름
 * - 일정한 생명주기에 따라 객체가 생성되고 특정 메소드가 호출되는 것이 특징
 */

@WebServlet("/life.do")
public class LifeCycleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	static {
		System.out.println("0. static 초기화문");
	}
	
	
	public LifeCycleServlet() {
		System.out.println("1. LifeCycleServlet 생성자 호출!");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("2. init() : 최초 한번만 서블릿이 실행되는 시점에 호출");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("3. service() : doGet, doPost가 호출되기 직전에 한번 호출됨");
		super.service(req, resp); // 해당 코드가 없는 경우 doGet, doPost가 호출되지 않음!
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("4. doGet() : 사용자가 get으로 요청할때 실행되는 메소드");
		resp.setContentType("text/html;charset=UTF-8"); // 응답페이지 컨텐츠를 설정
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		pw.append("<html>");
		pw.append("<body>");
		pw.append("<h1> LifeCycleServlet </h1>");
		pw.append("</body>");
		pw.append("</html>");
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("4. doPost() : 사용자가 post으로 요청할때 실행되는 메소드");
	}
	
	@Override
	public void destroy() {
		System.out.println("5. destroy : 서블릿이 소멸되기 직전에 호출됨 (실제 호출 타이밍 알수 없음)" );
	}
	

}

// 실행 순서
// 0. static 초기화문
// 1. LifeCycleServlet 생성자 호출!
// 2. init() : 최초 한번만 서블릿이 실행되는 시점에 호출
// 3. service() : doGet, doPost가 호출되기 직전에 한번 호출됨
// 4. doGet() : 사용자가 get으로 요청할때 실행되는 메소드
// 3. service() : doGet, doPost가 호출되기 직전에 한번 호출됨
// 4. doGet() : 사용자가 get으로 요청할때 실행되는 메소드
// 3. service() : doGet, doPost가 호출되기 직전에 한번 호출됨
// 4. doPost() : 사용자가 get으로 요청할때 실행되는 메소드
// ... 서비스 지속
// ...... tomcat이 죽을때 쯤
// 5. destroy : 서블릿이 소멸되기 직전에 호출됨 (실제 호출 타이밍 알수 없음)
















