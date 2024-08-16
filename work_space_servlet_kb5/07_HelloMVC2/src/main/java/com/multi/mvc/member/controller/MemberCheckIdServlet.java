package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.mvc.member.model.service.MemberService;

// 정적 페이지 중복검사 확인하는 코드
@WebServlet("/member/checkId")
public class MemberCheckIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		boolean isDuplicated = service.isDuplicated(userId);
		System.out.println("중복 확인 결과 : " + isDuplicated);
		
		req.setAttribute("isDuplicated", isDuplicated);
		req.getRequestDispatcher("/views/member/checkId.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}





