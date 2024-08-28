package com.multi.mvc.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachFile {
	private int fno;
	private int bno;
	private String originalFilename;
	private String renamedFilename;
	private Date createDate;
}
