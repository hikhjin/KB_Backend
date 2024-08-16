package com.multi.mvc;

import lombok.Data;
import org.springframework.stereotype.Component;

//class 를 bean으로 만들어주는 어노테이션
@Component
@Data
public class Bean {
    private String value1 = "test1";
    private String value2 = "test2";
}
