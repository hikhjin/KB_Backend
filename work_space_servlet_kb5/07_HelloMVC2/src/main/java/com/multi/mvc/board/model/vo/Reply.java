package com.multi.mvc.board.model.vo;

import java.util.Date;

public class Reply {
	private int no; // INT PRIMARY KEY AUTO_INCREMENT,
	private int boardNo; // INT,
	private int writerNo; // INT,
	private String writerId; 
	private String content; // VARCHAR(400),
	private String status; // VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
	private Date createDate; // DATETIME DEFAULT CURRENT_TIMESTAMP,
	private Date modifyDate; // DATETIME DEFAULT CURRENT_TIMESTAMP,

	public Reply() {
		super();
	}

	public Reply(int no, int boardNo, int writerNo, String writerId, String content, String status, Date createDate,
			Date modifyDate) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.writerNo = writerNo;
		this.writerId = writerId;
		this.content = content;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Reply [no=" + no + ", boardNo=" + boardNo + ", writerNo=" + writerNo + ", writerId=" + writerId
				+ ", content=" + content + ", status=" + status + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}
