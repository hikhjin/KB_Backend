package com.multi.mvc;

import java.util.Locale;

import lombok.Setter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multi.mvc.model.vo.Dog;
import com.multi.mvc.model.vo.User;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HomeController {

	@Setter(onMethod_ = @Autowired)
	@Qualifier("user")
	private User user;

	@Setter(onMethod_ = @Autowired)
	@Qualifier("dog")
	private Dog dog;

	@Setter(onMethod_ = @Autowired)
	private TestBean testBean;

	@Setter(onMethod_ = @Autowired)
	@Qualifier("dataSource")
	private BasicDataSource myDataSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// 1. xml에서 로드한 사용자/펫 정보 출력
		model.addAttribute("user", user);
		model.addAttribute("dog", dog);

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
