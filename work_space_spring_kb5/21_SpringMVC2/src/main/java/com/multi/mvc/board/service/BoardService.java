package com.multi.mvc.board.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import com.multi.mvc.board.vo.Board;
import com.multi.mvc.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class BoardService{

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    public List<Board> getAllList() {
        return mapper.selectAll();
    }

    public Board getBoard(long bno) {
        return mapper.selectByBno(bno);

    }

    public Board write(Board board) {
        int result = mapper.insertBoard(board);
        if(result > 0){
            return mapper.selectByBno(board.getBno());
        }else{
            return null;
        }
    }

    public Board update(Board board) {
        int result = mapper.updateBoard(board);
        if(result > 0){
            return mapper.selectByBno(board.getBno());
        }else{
            return null;
        }
    }

    public Board delete(long bno) {
        Board board = getBoard(bno);
        int result = mapper.deleteBoard(bno);
        if(result > 0){
            return board;
        }else{
            return null;
        }
    }

}
