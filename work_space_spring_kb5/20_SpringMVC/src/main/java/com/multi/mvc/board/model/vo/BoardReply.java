package com.multi.mvc.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReply {
	private int rno;
	private int bno;
	private int mno;
	private String memberId;
	private String memberName;
	private String content;
	private String status;
	private Date createDate;
	private Date modifyDate;
}
