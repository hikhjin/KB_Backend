package com.multi.mvc;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ■ 리소스에 있는 프로퍼티 값을 읽어와 java에 주입 시키는 방법 (Spring Boot 스타일)
 *  1. root-context.xml 또는 다른 context.xml 파일에 <context:property-placeholder /> 태그 추가 필요
 *         -> root-context.xml 태그 참조할것
 *  2. PropertySourcesPlaceholderConfigurer Class를 생성해서 Class path를 지정하는 방법
 *         -> RootConfig.java 파일 참고할 것
 *
 * ■ Java로 프로퍼티 값을 주입 시키기 위한 주요 어노테이션
// *  @Component("ID 생략 가능")
 *      : Bean을 생성하기 위한 기본 어노테이션, ID 생략 가능, 만일 ID가 생략 되면 class 앞글자가 소문자로 바뀌어 사용된다.
 *        ex) TestBean Class에 id 생략하면 testBean으로 접근하면 된다.
// *  @Value ("#{EL 표기}")
 *      : 프로퍼티에서 읽어온 값을 주입시키기 위한 어노테이션, EL 표기법을 사용한다.
 */

@Component
@Data
public class TestBean {
    @Value("#{properties['common.name'] != null ? properties['common.name'] : 'null!!!'}")
    private String name;


    @Value("#{properties['common.value']}")
    private String value;

    @Value("#{properties['db.driver']}")
    private String driver;

    @Value("#{properties['db.url']}")
    private String url;
}
