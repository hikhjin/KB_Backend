package com.multi.mvc.board.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int bno;
	private int mno;
	private String type;
	private String title;
	private String content;
	private String memberId;
	private String memberName;
	private int attachCount;
	private int readCount;
	private String status;
	private Date createDate;
	private Date modifyDate;
	private List<BoardReply> replies;
	private List<AttachFile> attachFiles;
}
