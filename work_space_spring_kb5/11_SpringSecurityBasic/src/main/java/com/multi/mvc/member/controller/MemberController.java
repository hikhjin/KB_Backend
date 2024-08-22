package com.multi.mvc.member.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.multi.mvc.member.model.vo.Member;
import com.multi.mvc.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member/memberLogin.do")
    public void memberLogin() {}

    /**
     * Authentication
     * 	- Principal : 인증된 사용자객체
     *  - Credentials : 인증에 필요한 비밀번호
     * 	- Authorities : 인증된 사용자가 가진 권한
     */
    // 1. SecurityContextHolder로 부터 authentication 객체 가져오기
//	@GetMapping("/member/memberDetail.do")
//	public String memberDetail(Model model) {
//
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Member principal = (Member) authentication.getPrincipal();
//		model.addAttribute("loginMember", principal);
//		
//		log.debug("authentication = {}", authentication);
//		// authentication = org.springframework.security.authentication.UsernamePasswordAuthenticationToken@23abe407: Principal: Member(id=honggd, password=$2a$10$qHHeJGgQ9teamJyIJFXbyOBtl7nIsQ37VP2jrz89dnDA7LgzS.nYi, name=카길동, gender=M, birthday=2021-05-04, email=honggd@naver.com, phone=01012341234, address=서울시 강남구, hobby=[운동,  등산], enrollDate=2021-05-20, authorities=[ROLE_USER], enabled=true); Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@166c8: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: B95C1041773474D93729781512D4490A; Granted Authorities: ROLE_USER
//		log.debug("principal = {}", principal);
//       return "member/memberDetail";
//	}

//    // 2. HandlerMapping에 security인증된 사용자 Authentication 요청하기
//    @GetMapping("/member/memberDetail.do")
//    public String memberDetail(Authentication authentication, Model model) {
//        Member principal = (Member) authentication.getPrincipal();
//        model.addAttribute("loginMember", principal);
//        System.out.println("authentication : " + authentication);
//        log.debug("authentication = {}", authentication);
//        // authentication = org.springframework.security.authentication.UsernamePasswordAuthenticationToken@23abe407: Principal: Member(id=honggd, password=$2a$10$qHHeJGgQ9teamJyIJFXbyOBtl7nIsQ37VP2jrz89dnDA7LgzS.nYi, name=카길동, gender=M, birthday=2021-05-04, email=honggd@naver.com, phone=01012341234, address=서울시 강남구, hobby=[운동,  등산], enrollDate=2021-05-20, authorities=[ROLE_USER], enabled=true); Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@166c8: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: B95C1041773474D93729781512D4490A; Granted Authorities: ROLE_USER
//        log.debug("principal = {}", principal);
//        return "member/memberDetail";
//    }

    // 3. 어노테이션을 통해 AuthenticationPrincipal 가져오기
    @GetMapping("/member/memberDetail.do")
    public String memberDetail(@AuthenticationPrincipal Member principal, Model model) {
        model.addAttribute("loginMember", principal);
        log.debug("principal = {}", principal);
        return "member/memberDetail";
    }

    // CSRF 방지를 위한 Authentication 기능 활용 메소드
    @PostMapping("/member/memberUpdate.do")
    public String memberUpdate(
            Member updateMember,
            Authentication oldAuthentication,
            RedirectAttributes redirectAttr) {
        log.debug("updateMember = {}", updateMember); // password, authorites누락

        // 1. 비니지스 로직 : db의 member객체를 수정

        // 2. SecurityContext의 authentication객체를 수정
        // updateMember에 누락된 정보 password, authorities 추가
        updateMember.setPassword(((Member) oldAuthentication.getPrincipal()).getPassword());

        Collection<? extends GrantedAuthority> oldAuthorities = oldAuthentication.getAuthorities();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (GrantedAuthority auth : oldAuthorities) {
            SimpleGrantedAuthority simpleAuth = new SimpleGrantedAuthority(auth.getAuthority()); // 문자열을 인자로 auth객체생성
            authorities.add(simpleAuth);
        }
        updateMember.setAuthorities(authorities);

        //새로운 authentication객체 생성
        Authentication newAuthentication =
                new UsernamePasswordAuthenticationToken(
                        updateMember,
                        oldAuthentication.getCredentials(),
                        oldAuthentication.getAuthorities()
                );
        //SecurityContextHolder - SecurityContext 하위에 설정
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        //3. 사용자피드백 & 리다이렉트
        return "redirect:/member/memberDetail.do";
    }

    @GetMapping("/service/allAllowService.do")
    public String allAllowService(Model model) {
        String msg = memberService.allAllowService();
        model.addAttribute("msg", msg);
        return "home";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/service/userNotAllowService.do")
    public String userNotAllowService(Model model) {
        String msg = memberService.userNotAllowService();
        model.addAttribute("msg", msg);
        return "home";
    }

}
