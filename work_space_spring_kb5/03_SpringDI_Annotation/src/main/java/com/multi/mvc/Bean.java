package com.multi.mvc;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("bean")
@Data
public class Bean {
    public String value1 = "bean component value";
    public String value2 = "test2";
}
