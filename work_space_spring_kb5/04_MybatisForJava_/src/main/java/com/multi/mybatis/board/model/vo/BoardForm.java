package com.multi.mybatis.board.model.vo;

//BoardParamDTO, BoardForm, BoardParam

import com.multi.mybatis.common.PageInfo;
import lombok.Data;

// 게시판 검색 시 사용하는 변수를 정리한 객체 (HTML-Form에 있는 인자들)\
@Data
public class BoardForm {
    private String title;
    private String content;
    private String writer;
    private PageInfo pageInfo;
}
