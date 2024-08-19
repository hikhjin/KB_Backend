package com.multi.ajax.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;
	private String name; // "남" 4byte? 남\0
	private int age;
	private char gender; // '남' 2byte? // java에서 char 설계 지양할 것.
}
