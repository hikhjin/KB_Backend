package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;


@WebServlet(name="updatePwd", urlPatterns = "/member/updatePwd")
public class MemberUpdatePwdServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/updatePwd.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = getSessionMember(req);
		String newPwd = req.getParameter("userPwd");
		
		if(member == null || newPwd == null || newPwd.isEmpty()) {
			sendCommonPage("잘못된 접근입니다.", "/member/updatePwd", req, resp);
			return;
		}
		
		int result = service.updatePassword(member.getNo(), newPwd);
		if(result > 0) {
			sendCommonPage("비밀번호 수정이 완료되었습니다.", "/", "self.close()", req, resp);
		}else {
			sendCommonPage("비밀번호에 실패하였습니다.", "/", "self.close()", req, resp);
		}
	}
}
