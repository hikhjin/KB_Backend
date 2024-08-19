package com.multi.mybatis.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String 	 id; 
	private String 	 password;  
	private String 	 name;  
	private int 	 age; 
	private String 	 gender;
	private String 	 phone; 
	private String 	 address; 
	private String 	 email;  
	private String[] hobby; // 축구,야구,영화보기
	private Date 	 enrollDate; 
}
