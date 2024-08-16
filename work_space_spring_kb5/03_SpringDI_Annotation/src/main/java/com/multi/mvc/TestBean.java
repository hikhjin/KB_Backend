package com.multi.mvc;

import com.multi.mvc.config.PropertyConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * ■ 리소스에 있는 프로퍼티 값을 읽어와 java에 주입 시키는 방법 (Spring Boot 스타일)
 *  1. root-context.xml 또는 다른 context.xml 파일에 <context:property-placeholder /> 태그 추가 필요
 *         -> root-context.xml 태그 참조할것
 *  2. PropertySourcesPlaceholderConfigurer Class를 생성해서 Class path를 지정하는 방법
 *         -> RootConfig.java 파일 참고할 것
 *         
 * ■ Java로 프로퍼티 값을 주입 시키기 위한 주요 어노테이션 
 *  @Component("ID 생략 가능") 
 *  	: Bean을 생성하기 위한 기본 어노테이션, ID 생략 가능, 만일 ID가 생략 되면 class 앞글자가 소문자로 바뀌어 사용된다.
 *        ex) TestBean Class에 id 생략하면 testBean으로 접근하면 된다.
 *  @Value ("#{property.value}") or  ("${bean.value}")
 *      : 프로퍼티나 Bean으로 부터 읽어온 값을 주입시키기 위한 어노테이션, EL 표기법을 사용한다.
 */

@Data
@Import(value = {Bean.class})
@Component
@ComponentScan("com.multi.mvc")
public class TestBean {
	// value로 주입시킬때 Bean의 경우 #{...}을 활용하여 주입시킴, #{...}은 EL과 비슷한 표현법
	@Value("#{bean.value1}")
	private String valueTest;

	// ${...}는 프로퍼티에서 값을 가져올때 활용하는 방법
	@Value("${common.name:defaultName}") // ':' 오른쪽 영역은 만일 프로퍼티가 없는 경우 default로 들어갈 값
	private String name;

	@Value("${common.value}")
	private String value;

	@Value("${db.driver}")
	private String driver;

	@Value("${db.url}")
	private String url;
}






