package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;

/*
 * 1. get 요청이 오는 경우 -> 회원가입 페이지로 이동
 * 2. post 요청이 오는 경우 -> 회원가입 요청 처리
 */

@WebServlet(name = "enroll", urlPatterns = "/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/enroll.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member member = new Member();
			member.setId(req.getParameter("newId").trim());
			member.setPassword(req.getParameter("userPwd").trim());
			member.setName(req.getParameter("userName").trim());
			member.setPhone(req.getParameter("phone").trim());
			member.setEmail(req.getParameter("email").trim());
			member.setAddress(req.getParameter("address").trim());
			if(req.getParameter("hobby") != null) {
				String hobby = String.join(",", req.getParameterValues("hobby"));
				member.setHobby(hobby);
			}
			
			System.out.println("가입 요청 : " + member);
			
			int result = service.join(member);
			
			if(result > 0) { // 가입 성공
				req.setAttribute("msg", "회원가입에 성공 하였습니다.");
				req.setAttribute("location", "/");
			}else { // 가입 실패
				req.setAttribute("msg", "회원가입에 실패 했습니다. (code:101=db이슈)");
				req.setAttribute("location", "/member/enroll");
			}
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "회원가입 실패!! (code:100 = 입력값 문제)");
			req.setAttribute("location", "/member/enroll");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
		}
	}
}






