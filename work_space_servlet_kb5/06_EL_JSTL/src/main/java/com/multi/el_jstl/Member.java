package com.multi.el_jstl;

public class Member {
	private String name;
	private int age;
	private String scope;

	public Member() {
		super();
	}

	public Member(String name, int age, String scope) {
		super();
		this.name = name;
		this.age = age;
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", scope=" + scope + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
