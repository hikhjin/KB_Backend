package com.multi.websocket.member.model.vo;

public class Member {

	private int no;
	private String id;
	private String password;
	private String name;
	private int age;
	private String enrollDate;

	public Member() {
		super();
	}

	public Member(int memberNo, String memberId, String memberPw, String memberName, int age, String enrollDate) {
		super();
		this.no = memberNo;
		this.id = memberId;
		this.password = memberPw;
		this.name = memberName;
		this.age = age;
		this.enrollDate = enrollDate;
	}

	public int getMemberNo() {
		return no;
	}

	public void setMemberNo(int memberNo) {
		this.no = memberNo;
	}

	public String getMemberId() {
		return id;
	}

	public void setMemberId(String memberId) {
		this.id = memberId;
	}

	public String getMemberPw() {
		return password;
	}

	public void setMemberPw(String memberPw) {
		this.password = memberPw;
	}

	public String getMemberName() {
		return name;
	}

	public void setMemberName(String memberName) {
		this.name = memberName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

}
