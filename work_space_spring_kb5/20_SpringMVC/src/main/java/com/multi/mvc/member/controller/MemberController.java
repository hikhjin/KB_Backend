package com.multi.mvc.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.multi.mvc.member.model.service.MemberService;
import com.multi.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log4j 선언 대신에 작성해주는 lombok 어노테이션
@Controller
@SessionAttributes("loginMember") // loginMember를 model에서 취급할때 세션으로 처리하는 어노테이션
public class MemberController {
	
	@Autowired
	private MemberService service;
	

	// action : /login, method : POST
	// 파라메터 : memberId, memberPwd
//	@RequestMapping(name="/login", method = RequestMethod.POST)
	@PostMapping("/login") // POST임을 알리는 어노테이션
	public String login(Model model, String memberId, String memberPwd) {
		log.debug("@@@@@ Login : " + memberId + ", " + memberPwd);
		
		Member loginMember = service.login(memberId, memberPwd);
		
		if(loginMember != null) { // 로그인이 성공한 경우
			model.addAttribute("loginMember", loginMember); // 세션에 저장하는 로직 @SessionAttributes 사용
			return "redirect:/"; // index로 보내는 리다이렉트문
		} else { // 로그인이 실패한 경우
			model.addAttribute("msg", "아이디와 패스워드를 확인해주세요.");
			model.addAttribute("location", "/");
			return "common/msg";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(SessionStatus status) { // status: 세션의 상태를 확인하는 인자
		log.debug("status : " + status.isComplete()); // isComplete : 세션이 완료 되었는지
		status.setComplete(); // 세션을 종료시키는 메소드
		log.debug("status : " + status.isComplete()); 
		return "redirect:/";
	}
	
	// 회원가입 페이지를 연결 시켜주는 핸들러 메소드
	@GetMapping("/member/enroll")
	public String enrollPage() { // xxxPage = 단순 html/jsp view로 연결하는 핸들러 메소드 패턴
		log.debug("회원 가입 페이지 요청");
		return "member/memberEnroll";
	}
	
	@PostMapping("/member/enroll")
	public String enroll(Model model, @ModelAttribute Member member) {  // @ModelAttribute 생략가능!
		log.debug("회원가입 요청 member : " + member.toString());

		int result = 0;
		try {
			result = service.save(member);
		} catch (Exception e) {}
		
		if(result > 0) {
			model.addAttribute("msg", "회원가입에 성공하였습니다.");
			model.addAttribute("location", "/");
		} else {
			model.addAttribute("msg", "회원가입에 실패하였습니다. 입력정보를 확인하세요.");
			model.addAttribute("location", "/member/enroll"); // 리다이렉트 설계
		}
		
		return "common/msg";
	}
	
	// url 검사 : http://localhost/mvc/member/idCheck?id=test2
	
	// AJAX로 ID 중복검사하는 핸들러
	@RequestMapping("/member/idCheck")
	public ResponseEntity<Map<String, Object>> idCheck(String id){
		log.debug("아이디 중복 확인 : " + id);
		
		boolean validate = service.validate(id);
		Map<String, Object> map = new HashMap<>();
		map.put("validate", validate);
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	

	// 회원정보 보기 + update
	@GetMapping("/member/view")
	public String memberViewPage() {
		log.debug("회원정보 보기 페이지 요청");
		return "member/memberView";
	}
	
	// http://localhost/mvc/member/update
	@PostMapping("/member/update")
	public String update(Model model, 
			@ModelAttribute Member member,
			@SessionAttribute(name="loginMember", required = false) Member loginMember // 세션 값
			) {  
		log.debug("업데이트 요청 member : " + member.toString());
		log.debug("업데이트 요청 loginMember : " + loginMember.toString());
		
		// 보안 코드부 예시
		if(loginMember == null || loginMember.getId().equals(member.getId()) == false) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			model.addAttribute("location", "/");
			return "common/msg";
		}
		
		int result = 0;
		try {
			member.setMno(loginMember.getMno()); // update를 위한 mno 셋팅
			result = service.save(member); // update 요청
		} catch (Exception e) {}
		
		if(result > 0) {
			loginMember = service.findByNo(loginMember.getMno()); // db로부터 다시 select 데이터 업데이트
			model.addAttribute("loginMember", loginMember); // 세션에 있는 loginMember update
			model.addAttribute("msg", "회원정보 수정에 성공하였습니다.");
			model.addAttribute("location", "/member/view");
		} else {
			model.addAttribute("msg", "회원정보 수정에 실패하였습니다. 입력정보를 확인하세요.");
			model.addAttribute("location", "/member/view"); // 리다이렉트 설계
		}
		return "common/msg";
	}
	
	@GetMapping("/member/updatePwd")
	public String updatePwdPage() {
		return "/member/updatePwd";
	}
	
	@PostMapping("/member/updatePwd")
	public String updatePwd(Model model, String password,
			@SessionAttribute(name="loginMember", required = true) Member loginMember) {
		int result = 0;
		try {
			result = service.updatePwd(loginMember.getMno(), password);
		} catch (Exception e) {}
		
		if(result > 0) {
			model.addAttribute("msg", "비밀번호 변경에 성공하였습니다.");
		} else {
			model.addAttribute("msg", "비밀번호를 변경 할 수 없습니다.");
		}
		model.addAttribute("script", "self.close()");
		
		return "/common/msg";
	}
	
	
	
	@RequestMapping("/member/delete")
	public String deleteMember(Model model,
			@SessionAttribute(name="loginMember", required = true) Member loginMember) {
		int result = 0;
		try {
			result = service.delete(loginMember.getMno());
		} catch (Exception e) {}
		
		if(result > 0) {
			model.addAttribute("msg", "회원탈퇴에 성공하였습니다.");
			model.addAttribute("location", "/logout");
		} else {
			model.addAttribute("msg", "회원탈퇴를 할 수 없습니다. 관리자 문의 바랍니다.");
			model.addAttribute("location", "/member/view");
		}
		return "/common/msg";
	}
	
	
	// contoller에서 전체 Error 처리하는 핸들러 
//	@ExceptionHandler(Exception.class)
//	public String error() {
//		return "/common/error";
//	}
	
}










