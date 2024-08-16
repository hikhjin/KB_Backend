package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;


// 필터 때문에 name 선언함
@WebServlet(name="login", urlPatterns="/login")
public class MemberLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() +"/"); // get요청 index로 보내는 코드!
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 패턴 1. 파라메터 읽어오기 -> id/pw, saveId
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		String saveId = req.getParameter("saveId");
		System.out.println(userId + ", "+ userPwd + ", " + saveId);
		
		// 패턴 2. service에서 비지니스 로직(로직) 처리하기 -> 로그인
		Member loginMember = service.login(userId, userPwd);
		// 쿠키로 사용자 아이디 저장하는 기능
		saveCookie(resp, saveId, userId);
		
		// 패턴3. 응답할 페이지를 정하고, 파라메터 셋팅해서 넘긴다.
		if(loginMember != null) { // 로그인 성공한 경우
			// 세션에 로그인 정보를 저장하고, 브라우저가 종료 될때 까지 로그인 상태를 유지
			HttpSession session = req.getSession();
			session.setAttribute("loginMember", loginMember); // 로그인 정보를 세션에 저장하는 코드
			resp.sendRedirect(req.getContextPath() +"/"); // 처음 페이지로 다시 이동
			// 세션에 멤버가 있는 경우는 알아서 로그인 성공 창으로 바뀐다.
		}else { // 로그인 실패했을 경우
			req.setAttribute("msg", "사용자 아이디나 패스워드가 틀렸습니다.");
			req.setAttribute("location", "/");
			
			// 공통 페이지 중 메세지를 알리고 페이지 이동하는 jsp 활용
			// 메세지를 forward로 넘겨야 jsp에서도 속성값을 볼수 있음
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		}
	}
	
	private void saveCookie(HttpServletResponse resp, String saveId, String userId) {
		// 사용자의 요청으로 id를 브라우저 레벨에 저장하는 기능
		if(saveId != null) { // 저장 요청
			Cookie c = new Cookie("saveId", userId); // 사용자 id를 쿠키로 구움
			c.setMaxAge(60 * 60); // 1시간
			resp.addCookie(c);
		}else { // 저장 요청 안함
			Cookie c = new Cookie("saveId", ""); // 아이디 초기화
			c.setMaxAge(0); // 시간도 초기화
			resp.addCookie(c);
		}
	}
	
	

}

















