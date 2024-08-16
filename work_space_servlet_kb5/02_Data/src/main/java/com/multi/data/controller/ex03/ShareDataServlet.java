package com.multi.data.controller.ex03;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//  Servlet 혹은 서버 레벨에서 데이터를 공유하는 방법 tip! 아래로 내려 갈수록 공유 범위가 작아짐!
//
//  1. ServletContext 활용
//  - 서버 전역 간 공유 가능
//  - Context란? Tomcat에서 관리는 컨테이너의 객체 이름으로 서버 자원 관리 및 호출을 담당하는 주요한 객체
//  	-> 실제 서버 프로그램 같은 생명주기를 가짐.
//  - 해당 객체에 setAttribute를 통해 전역으로 데이터를 공유할 수 있으나...
//  - ※주의 : 메모리 접근 이슈 및 소멸 기간 산정의 어려움이 있음으로 잘 사용되지 않는다.
//  - 저장 기간 : 서버 실행 (runtime)부터 종료(shutdown)까지 저장됨
//
//  2. HttpSession
//  - 브라우저 레벨에서 활용됨
//  - 사용자 브라우저 환경 별로 ID를 지정하여 서버에서 데이터가 저장되는 메커니즘
//  - Timeout 시간을 지정하면 저장한 시간만큼 사용자 정보를 서버에 유지 시킬수 있음 = 기본 180분
//  - 서블릿이 달라져서도 session 객체에 데이터를 공유할수 있음 (redirect 영향 없음)
//  - 데이터 저장기간 : timeout, 사용자의 브라우저 환경 변경(프로그램 종료 후 재실행), invalidate 함수호출
//  - 사용 예시) 사용자 로그인 기능, 권한 정보, 사용자 고유 정보 저장.
//
//  3. HttpServletRequest
//   - setAttribute
//   - 페이지간 이동에서 공유 방법
//   - 서블릿 간, 서블릿 - jsp 간 이동 될때 객체 레벨로 데이터가 저장 가능
//   - ex) 서블릿 (Controller) -> JSP(View) - MVC2패턴 서블릿을 통해 DB정보를 흭득,
//         객체화 이후 JSP로 보내 View(HTML) 작성하여 사용자에게 보내줌.
//   - redirect로 전달시 저장되지 않음.
//     ★★★★★forward 활용 필요!!



@WebServlet("/share.do")
public class ShareDataServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public static final String CONTEXT_ID1 = "CONTEXT_ID1";
	public static final String CONTEXT_ID2 = "CONTEXT_ID2";
	public static final String SESSION_ID = "SESSION_ID";
	public static final String MSG_ID = "MSG_ID";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ShareDataServlet - doGet 호출!");
		
		// 1. ServletContext 활용 - 서버 전역, 서버 셧다운까지 저장
		ServletContext context1 = req.getServletContext();
		ServletContext context2 = getServletContext(); // 둘다 동일한 표현
		context1.setAttribute(CONTEXT_ID1, "context 메세지1 입니다.");
		context2.setAttribute(CONTEXT_ID2, "context 메세지2 입니다.");
		String msg1 = (String) context1.getAttribute(CONTEXT_ID2);
		String msg2 = (String) context1.getAttribute(CONTEXT_ID2);
		System.out.println(msg1);
		System.out.println(msg2);
		
		
		// 2. HttpSession - 사용자 브라우저 ID를 통해 저장되는 데이터, 서버에서 저장됨, time out 기간까지
		HttpSession session = req.getSession(); // Client의 고유 세션을 가져오는 방법
		System.out.println(session.getId()); // session의 고유 id이다.
		session.setAttribute(SESSION_ID, "session Message입니다. " + session.getId());
		String msg3 = (String)session.getAttribute(SESSION_ID);
		System.out.println(msg3);
		
		
		// 3. HttpSevletRequest - page(Servlet/JSP)간 데이터를 공유
		req.setAttribute(MSG_ID, "reqeust에서 전달될 메세지");
		String msg4 = (String) req.getAttribute(MSG_ID);
		System.out.println(msg4);
		
		// 페이지 이동하는 방법
		// 1. forward 이동 - 3개 데이터 살아있음
//		req.getRequestDispatcher("useData.do").forward(req, resp);
		
		// 2. redirect 이동 - 2개 데이터 살아있음(HttpSession, ServletContext)
		resp.sendRedirect("useData.do");
	}
}








