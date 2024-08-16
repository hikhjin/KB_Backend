package com.multi.mvc;


import com.multi.mvc.model.vo.User;
import lombok.Setter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// xml로 선언한 bean을 활용하기 - @Autowired

	// @Autowired 팁: 객체이름과 동일한 bean이 있는 경우 자동으로 맵핑 지원, user1 -> user1
	@Autowired // bean을 자신의 객체로 주입하는 방법
	private User user1;

	@Autowired
	private User user2;

	// 에러가 발생하는 코드
	// 원인: bean id와 객체의 이름이 일치하지 않아 자동으로 맵핑할 수 없음
//	@Autowired
//	private User testUser3;

	// Qualifier를 통해 bean의 이름을 지정하는 문법
	@Autowired
	@Qualifier("user3")
	private User testUser3;

	//Field injection is not recommended warning 해결하기
	// -> spring 5.x부터 발생한 warning으로 field 주입이 안전하지 않다는 경고
	// 해결방법 2가지: setter로 주입, 생성자로 주입

	//1. set을 손으로 작성하여 주입하는 방법

//	private User testUser4;
//	@Autowired
//	@Qualifier("user4")
//	public void setTestUser4(User testUser4) {
//		this.testUser4 = testUser4;
//	}

	// 2. lombok을 통한 setter 단축 표현
	@Setter(onMethod_ = {@Autowired, @Qualifier("user4")})
	private User testUser4;


	// Bean을 setter로 가져오는 방식
	private TestBean testBean;
	@Autowired
	@Qualifier("testBean")
	public void setTestBean( TestBean testBean){
		this.testBean = testBean;
	}

	// Bean을 setter로 가져오는 방식2 : lombok 단축표현!
	@Setter(onMethod_ = {@Autowired, @Qualifier("dataSource")})
	private BasicDataSource myDataSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		//1. xml로 선언한 bean을 model로 담아 view로 전당
		model.addAttribute("user1", user1);
		model.addAttribute("user2", user2);
		model.addAttribute("testUser3", testUser3);
		model.addAttribute("testUser4", testUser4);


// 2. Properties에서 읽어온 사용자 bean 출력
		model.addAttribute("testBean", testBean);

// 3. bean으로 생성한 dataSource 가져오기
		String className = myDataSource.getDriverClassName();
		String userName = myDataSource.getUserName();
		model.addAttribute("className", className);
		model.addAttribute("userName", userName);

		return "home";
	}
	
}
