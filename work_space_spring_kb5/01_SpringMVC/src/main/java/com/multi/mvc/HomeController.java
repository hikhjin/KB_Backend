package com.multi.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */


/**
 * ■ Spring MVC 개념 정리
 *  - Spring은 서블릿 대비 어노테이션(@Annotation)의 활용도 높아졌다.
 *  - Spring 프레임워크는 상속 설계를 지양하고, interface 기반 설계가 권장된다.
 *    -> Dependency injection(의존성 주입)을 통해 객체를 직접 생성하지 않고 Spring에서 생성 주입시키기 편하기 위해
 *    -> new 지양하고 프레임워크에서 대신 생성하는데, 장점 : 의존성이 줄고, 메모리 관리가 우수하다.
 *  - Spring MVC에서는 철저하게 MVC2 패턴을 활용하도록 설계 되어있다.
 *    -> Request(URL+파라미터) -> Controller(Java객체) + Model -> View(JSP or Thymleaf)
 *  - 서블릿 Class 단위로 URL을 맵핑 했던 기능이 @Controller에 핸들러 메소드(Handler method)로 이관
 *    -> Spring은 핸들러 메소드로 URL 맵핑 가능, 상위 패키지(ex: Member, Board)의 세부 기능을 하나의 Class에서 설계
 *  - WEB-INF구조(보안 기능)으로 인해 URL로 JSP, HTML에 직접 접근이 불가하고, 반드시 Controller->View 접근이 필요
 *  - ViewResolver를 통해 View로 접근하는 URL 단축 시킬수 있다.
 *    -> ex) return "home" -> "/WEB-INF/views/home.jsp"로 jsp 페이지가 호출 됨
 *  - return의 경우 (=View name을 문자열 지정), forward로 전달 되도록 설계됨 -> 파라미터 물고 전달됨!
 *  - 만일 redirect로 다른 핸들러 메소드를 호출하기 위해선 return 값 앞에 'redirect:' + URL을 붙이면 된다.
 *    -> ex) home2.do redirect로 전달하는 방법 : "redirect:home2.do"
 */

// @Controller: 컨트롤러임을 알리는 어노테이션, Servlet Class를 대체하고, Bean으로 관리하는 대상
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// Logger : log4j에서 활용되는 로그  객체, 사용하지 않으면 지워도 무관!
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// URL을 맵핑시키는 가장 간단한 방법

//	@RequestMapping("home2.do") // 핸들러 메소드로 맵핑하는 방법
//	public String home2() {
//		return "home2";
//	}

	// 메소드 이름으로 유사한 jsp로 맵핑시켜주는 방법
	@RequestMapping("home2.do")
	public void home2() {
	}

	// Redirect로 home3.do -> home2.do 로 연결하는 방법
	@RequestMapping("home3.do")
	public String home3() {
		return "redirect:home2.do"; // redirect 명령이 실행됨
	}
	
}
