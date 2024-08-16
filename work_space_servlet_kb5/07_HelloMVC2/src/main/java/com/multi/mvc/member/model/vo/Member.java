package com.multi.mvc.member.model.vo;

import java.util.Date;

public class Member {
	private int no; // INT PRIMARY KEY AUTO_INCREMENT,
	private String id; // VARCHAR(30) NOT NULL UNIQUE,
	private String password; // VARCHAR(100) NOT NULL,
	private String role; // VARCHAR(10) DEFAULT 'ROLE_USER',
	private String name; // VARCHAR(15) NOT NULL,
	private String phone; // VARCHAR(13),
	private String email; // VARCHAR(100),
	private String address; // VARCHAR(100),
	private String hobby; // VARCHAR(100),
	private String status; // VARCHAR(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')),
	private Date enrollDate; // DATETIME DEFAULT CURRENT_TIMESTAMP,
	private Date modifyDate; // DATETIME DEFAULT CURRENT_TIMESTAMP

	public Member() {
		super();
	}

	public Member(int no, String id, String password, String role, String name, String phone, String email,
			String address, String hobby, String status, Date enrollDate, Date modifyDate) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.hobby = hobby;
		this.status = status;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", password=" + password + ", role=" + role + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", hobby=" + hobby + ", status="
				+ status + ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
