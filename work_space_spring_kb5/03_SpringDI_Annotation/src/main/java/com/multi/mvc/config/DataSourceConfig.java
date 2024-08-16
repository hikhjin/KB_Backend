package com.multi.mvc.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration // Resource 및 bean을 호출하기 위한 class를 알려주는 어노테이션
@Import(value = {PropertyConfig.class}) // PropertyConfig Bean을 미리 호출하기 위한 방법
@ComponentScan("com.multi.mvc")
public class DataSourceConfig {

    @Bean("dataSource")
    public BasicDataSource dataSourceConfigurer(
            @Value("${db.driver}") String driver,
            @Value("${db.url}") String url,
            @Value("${db.username}") String name,
            @Value("${db.password}") String password
            ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setMaxTotal(10);
        return dataSource;
    }

}
