package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;

@WebServlet("/member/view")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			String userId = member.getId();
			
			Member memberInfo = service.findMemberById(userId);
			System.out.println(memberInfo);
			
			if(memberInfo != null) {
				req.setAttribute("member", memberInfo);
				req.getRequestDispatcher("/views/member/view.jsp").forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/");
			}
		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath()+"/");
		}
	}
}









