package com.multi.mybatis;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multi.mybatis.member.model.vo.Member;

/**
 *  ■ Dependency injection(의존성 주입)의 개념
 *   - 등장 배경 : 서블릿과 같이 복잡한 상속으로 설계된 코드는 결합도가 높아져 복잡하고 코드 유지보수의 어려움이 발생
 *               강한 결합도를 갖는 경우 코드부 수정이 다른 코드에 영향을 미치는 경우가 빈번해짐 
 *   - but, 상속 개념 완전 배제 불가. 상속으로 설계된 이유는 사용자의 로직부를 프레임워크에서 로드하기 위함
 *   - 결합도를 낮추기 위해 IoC 개념을 활용. 
 *     -> 직접 상속을 배제하고, 간접적인 Interface 사용, 생성(new 사용)을 배제하고 어노테이션 또는 xml을 활용
 *   - DI의 개념은 사용자 직접 객체를 생성하지 않고, 프레임워크에서 사용자가 만든 객체를 직접 생성하여 프로그램에 주입시킴
 *   - 또한 상속과 사용자에 의한 객체 생성을 제한하여 의존성을 최대한 낮추는 방향으로 설계됨
 *   -> 장점 : 느슨한 결합구조로 코드의 간결성, 유지보수성 증가, 상속에 의한 복잡성 감소
 *   -> 단점 : DI 사용을 위해 java 외의 어노테이션 표기와 xml의 코드부가 증가되었음.
 *     -> 단점을 극복하고자... xml를 걷어내는 추세 (Spring Boot)
 *   
 *  ■ DI를 활용하기 위한 MVC 주요 레이어에 붙는 어노테이션
 *  @Controller : URL처리가 가능한 Controller에 붙는 어노테이션
 *  @Service : 비지니스 로직 처리를 위한 서비스 계층에 붙는 어노테이션
 *  @Repository : DB나 Data처리가 가능한 계층(주로 DAO)에 붙는 어노테이션
 *  @Component : 계층이 아닌 일반 객체를 생성할때 사용하는 어노테이션
 *  
 *  ■ DI를 통한 객체 생성시 활용 어노테이션 
 *  @Autowired : new 키워드 개신 객체를 생성할때 사용하는 어노테이션
 *  @Qualifier : @Autowired과 같이 사용 가능하며, 의존성 주입하는 객체가 여러개인 경우 name으로 구분
 *  @Resource : 자원 연결시 사용, 주로 프로퍼티에 사용됨 
 *  @Value : 단순 값을 주입할때 어노테이션
 */

@Controller
public class HomeController {
	// DI(Bean) 객체 생성하는 방법
	// 1. bean이 단일 ID로 선언 된 경우
	//  : @Autowired만으로 연결하면 된다.
	//
	// 2. bean이 다중 ID로 여러개가 선언 된 경우
	//  : @Autowired + @Qualifier("ID명")으로 사용하면 된다.
	//    @Qualifier의 id는 java 변수명과 일치하지 않아도 된다.

	@Autowired
	@Qualifier("testMember1")
	Member member1;

	@Autowired
	@Qualifier("testMember2")
	Member member2;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("member1", member1);
		model.addAttribute("member2", member2);
		return "home";
	}
}









