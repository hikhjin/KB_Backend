package com.multi.rest.content.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cat {
	private int id;
	private String name;
	private String info;
	private String eyeColor;
	private String hairLength;
	private String features;
	private String color;
	private String weight;
	private Date createDate;

	public Cat() {
		super();
	}

	public Cat(int id, String name, String info, String eyeColor, String hairLength, String features, String color,
               String weight, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
		this.eyeColor = eyeColor;
		this.hairLength = hairLength;
		this.features = features;
		this.color = color;
		this.weight = weight;
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", info=" + info + ", eyeColor=" + eyeColor + ", hairLength="
				+ hairLength + ", features=" + features + ", color=" + color + ", weight=" + weight + ", createDate="
				+ createDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getHairLength() {
		return hairLength;
	}

	public void setHairLength(String hairLength) {
		this.hairLength = hairLength;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	// Version1 - REST를 JAVA 객체로 당겨올쓸꺼
	// @JsonFormat: jackson에서 Json을 만들 때 정하는 포맷
	@JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}
	
	// Version2 - REST를 HTML로 보일때 쓸꺼
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	public String getCreateDate() {
//		return sdf.format(createDate);
//	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
