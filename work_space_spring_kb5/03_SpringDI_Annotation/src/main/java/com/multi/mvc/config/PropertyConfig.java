package com.multi.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Spring 시작시의 각종 프로퍼티 및 bean에 사용할 Class를 맵핑하는 역할
 */
@Configuration // Resource 및 bean을 호출하기 위한 class를 알려주는 어노테이션
@ComponentScan("com.multi.mvc") // BasePackage를 지정하는 명령어, 설정하지 않으면 현재 클래스의 패키지가 지정됨
public class PropertyConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		
		Resource[] resources = new ClassPathResource[] { 
				new ClassPathResource("common.properties"),
				new ClassPathResource("db.properties") };

		configurer.setLocations(resources);
		
		return configurer;
	}
}
