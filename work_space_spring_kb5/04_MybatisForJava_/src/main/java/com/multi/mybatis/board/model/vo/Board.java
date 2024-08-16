package com.multi.mybatis.board.model.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private long bno;// LONG AUTO_INCREMENT,
//	private long mno;// LONG,
	private String id; // Join으로 가져올 문장
	private String title;// VARCHAR(50),
	private String content;// VARCHAR(2000),
	private String type;// VARCHAR(100),
	private String originalFilename; // VARCHAR(100),
	private String renamedFilename;// VARCHAR(100),
	private int readCount;// INT DEFAULT 0,
	private String status;// VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
	private LocalDateTime createDate;// DATETIME DEFAULT CURRENT_TIMESTAMP,
	private LocalDateTime modifyDate;// DATETIME DEFAULT CURRENT_TIMESTAMP,
	private List<Reply> replies; // 상세보기에서 리플을 한번에 보여주기 위한 객체
}
