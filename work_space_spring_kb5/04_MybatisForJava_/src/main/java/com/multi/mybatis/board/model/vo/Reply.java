package com.multi.mybatis.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int rno;
	private int bno;
//	private int mno;
	private String id;
	private String content;	
	private Date createDate;
	private Date modifyDate;
}