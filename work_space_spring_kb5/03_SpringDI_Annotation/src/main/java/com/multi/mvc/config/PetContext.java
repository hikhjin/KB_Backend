package com.multi.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.multi.mvc.model.vo.Cat;
import com.multi.mvc.model.vo.Dog;


// @Configuration : xml로 설정하던 bean을 java로 생성할때 쓰이는 문법
@Configuration
public class PetContext {

//	@Bean("dog") // 네이밍을 지정하지 않으면 메소드이름이 ID로 지정된다.
	@Bean // 자동으로 bean을 생성할때 쓰이는 문법
	public Dog dog() {
		return new Dog("진돌이",2);
	}
	
	@Bean("defaultCat")
	public Cat cat() {
		return new Cat("나비",4);
	}
	
}
