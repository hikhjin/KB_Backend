package com.multi.mvc.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int    mno;         // mNo라던가 mNO 표기 하지 말고, mno와 같이 소문자로 통일 할 것! -> jsp에서 el 호환을 위해
	private String id;
	private String password;
	private String role;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String hobby;
	private String status;
	private Date   enrollDate;
	private Date   modifyDate;
}
