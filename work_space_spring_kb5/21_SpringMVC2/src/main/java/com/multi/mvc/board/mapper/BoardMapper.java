package com.multi.mvc.board.mapper;

import com.multi.mvc.board.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> selectAll();
    long boardCount();
    Board selectByBno(long bno);
    int insertBoard(Board board);
    int updateBoard(Board board);
    int deleteBoard(long bno);
}
