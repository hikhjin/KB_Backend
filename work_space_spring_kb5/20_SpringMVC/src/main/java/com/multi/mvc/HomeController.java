package com.multi.mvc;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.mvc.member.model.mapper.MemberMapper;
import com.multi.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	// 테스트 코드 예시
	@Autowired
	MemberMapper memberMapper;
	
	// 객체가 생성되고 초기화를 돌려주는 메소드
//	@Bean(initMethod = "test")
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces="text/html; charset=utf8" )
	@ResponseBody
	public String test() {
		StringBuffer sb = new StringBuffer();
		int count = memberMapper.selectCount();
		sb.append("count : " + count);
		sb.append("<br><br>");
		List<Member> list = memberMapper.selectAll();
		sb.append("list : " + list);
		sb.append("<br><br>");
		Member member = memberMapper.selectMember(1);
		sb.append("member : " + member);
		sb.append("<br><br>");
		Member member2 = memberMapper.selectMemberById("admin");
		sb.append("member2 : " + member2);
		sb.append("<br><br>");
		return sb.toString();
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("home 요청");
		return "home";
	}
	
}
