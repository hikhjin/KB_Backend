package com.multi.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	
	private MemberService service = new MemberService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member loginMember = getSessionMember(req);
			if(loginMember == null) {
				sendCommonPage("잘못된 접근입니다.", "/", req, resp);
				return;
			}
			
			Member newMember = new Member();
			newMember.setNo(loginMember.getNo());
			newMember.setId(req.getParameter("userId").trim());
			newMember.setName(req.getParameter("userName").trim());
			newMember.setPhone(req.getParameter("phone").trim());
			newMember.setEmail(req.getParameter("email").trim());
			newMember.setAddress(req.getParameter("address").trim());

			if(req.getParameter("hobby") != null) {
				// "운동,영화보기,스포츠"
				newMember.setHobby(String.join(",", req.getParameterValues("hobby")));
			}
			
			int result = service.update(newMember);
			
			if(result > 0) { // 업데이트 성공
				newMember = service.findMemberById(newMember.getId());
				setSessionMember(req, newMember);
				sendCommonPage("회원정보를 수정하였습니다.", "/member/view", req, resp);
			}else {
				sendCommonPage("회원정보를 수정에 실패하였습니다.", "/member/view", req, resp);
			}
		} catch (Exception e) {
			e.getStackTrace();
			sendCommonPage("인자가 잘못되었습니다.", "/member/view", req, resp);
		}
	}
}
