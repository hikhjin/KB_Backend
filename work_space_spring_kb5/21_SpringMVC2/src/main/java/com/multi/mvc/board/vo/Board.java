package com.multi.mvc.board.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private long bno;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
    private Date modifyDate;
}
