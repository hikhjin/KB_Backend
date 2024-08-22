package com.multi.websocket.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.websocket.member.model.service.MemberService;
import com.multi.websocket.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	@Qualifier("memberService")
	private MemberService service;
	
	@RequestMapping(value = "/login")
	public String loginMember(HttpSession session, Member m) {
		Member member =service.selectOneMember(m);
		if (member != null) {
			session.setAttribute("member", member);
			return "member/loginSuccess";
		} else {
			return "member/loginFailed";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/error")
	public String error() {
		return "common/error";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String errorHandler() {
		return "redirect:error.do";
	}

}
