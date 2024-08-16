package com.multi.mvc.board.model.vo;

import java.util.Date;
import java.util.List;

public class Board {
	private int no;  // INT AUTO_INCREMENT,
	private int writerNo; // INT,
	private String writerId;
	private String title; // VARCHAR(1000),
	private String content; // VARCHAR(2000),
	private String type; // VARCHAR(100),
	private String originalFilename; // VARCHAR(100),
	private String renamedFilename; // VARCHAR(100),
	private int readcount;// INT DEFAULT 0,
	private String status;// VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
	private Date createDate;// DATETIME DEFAULT CURRENT_TIMESTAMP,
	private Date modifyDate;// DATETIME DEFAULT CURRENT_TIMESTAMP,
	private List<Reply> replies; // 리플 담을 list
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int no, int writerNo, String writerId, String title, String content, String type,
			String originalFilename, String renamedFilename, int readcount, String status, Date createDate,
			Date modifyDate, List<Reply> replies) {
		super();
		this.no = no;
		this.writerNo = writerNo;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.type = type;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.readcount = readcount;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.replies = replies;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", writerNo=" + writerNo + ", writerId=" + writerId + ", title=" + title
				+ ", content=" + content + ", type=" + type + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", readcount=" + readcount + ", status=" + status
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", replies=" + replies + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(int writerNo) {
		this.writerNo = writerNo;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	
}
