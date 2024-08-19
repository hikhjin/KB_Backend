package com.multi.mybatis.member.controller;

import java.util.List;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.mybatis.member.model.service.MemberService;
import com.multi.mybatis.member.model.vo.Member;

@Controller("memberController")
public class MemberController {
	

	@Setter(onMethod_ = {@Autowired, @Qualifier("memberService")})
	private MemberService service;
	
	@RequestMapping("/member/index.do")
	public String index() {
		return "member/index";
	}
	
	// 회원가입
	@RequestMapping("member/memberEnroll.do")
	public String memberEnroll(Model model, Member member) {
		System.out.println("회원 가입 요청");
		int result = service.joinMember(member);
		
		if(result > 0) {
			model.addAttribute("msg", "회원가입 성공하였습니다.");
			return "/member/index";
		}else {
			return "redirect:/member/error.do";
		}
	}

	@RequestMapping("member/memberList.do")
	public String memberList(Model model) {
		List<Member> list = service.getMemberList();
		model.addAttribute("list", list);
		return "member/memberList";
	}
	
	// http://localhost/mybatis/member/memberSearch.do?id=test2
	@RequestMapping("member/memberSearch.do")
	public String memberSearch(Model model, String id) {
		Member member = service.searchMember(id);
		model.addAttribute("member", member);
		return "member/memberView";
	}
	
	//http://localhost/mybatis/member/memberDel.do
	@RequestMapping("/member/memberDel.do")
	public String memberDel(Model model, String id) {
		int result = service.deleteMember(id);
		if(result > 0) {
			model.addAttribute("msg","회원탈퇴에 성공하였습니다.");
			return "member/index";
		} else {
			return "redirect:/member/error.do";
		}
	}
	
	// 에러 페이지
	@RequestMapping("/member/error.do")
	public String erorr() {
		return "/common/error";
	}
}

















