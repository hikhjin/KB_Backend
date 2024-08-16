package com.multi.mvc.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat implements Pet {
	private String name;
	private int age;
	
	@Override
	public String speak() {
		return "왜용~~~?";
	}
}
