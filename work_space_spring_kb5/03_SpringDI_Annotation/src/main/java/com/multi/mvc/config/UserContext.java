package com.multi.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.multi.mvc.model.vo.Pet;
import com.multi.mvc.model.vo.User;

@Configuration
public class UserContext {

    @Bean("user")
    public User user(@Autowired @Qualifier("dog") Pet pet) {
        return new User("홍길동", 31, pet);
    }

    @Bean("default")
    public User defaultUser(@Autowired @Qualifier("defaultCat") Pet pet) {
        return new User("박길동", 31, pet);
    }

}
