package com.multi.mvc;

import com.multi.mvc.member.service.MemberService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		// Spring security 암호화 모듈 사용 예제
		// password 암호화 -> security hash(hash + solt)// https://st-lab.tistory.com/100
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode("1212");
		boolean matchResult = encoder.matches("1212", encodedPassword);
		model.addAttribute("password", encodedPassword);
		model.addAttribute("matchResult", matchResult);

		// 일반 텍스트 암호화
		TextEncryptor encryptor = Encryptors.text("password", KeyGenerators.string().generateKey());
		String encodeValue = encryptor.encrypt("안녕하세요?");
		String decodeValue = encryptor.decrypt(encodeValue);
		model.addAttribute("encodeValue", encodeValue);
		model.addAttribute("decodeValue", decodeValue);

		return "home";
	}
	
	@GetMapping("/board/boardList.do")
	public void boardList() {}

	@GetMapping("/admin/memberList.do")
	public void memberList() {}

	@GetMapping("/error/accessDenied.do")
	public void accesDenied() {}

}
