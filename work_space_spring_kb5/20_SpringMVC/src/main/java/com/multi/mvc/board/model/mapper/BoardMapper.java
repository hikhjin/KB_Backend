package com.multi.mvc.board.model.mapper;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Mapper;

import com.multi.mvc.board.model.vo.AttachFile;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.board.model.vo.BoardCategory;
import com.multi.mvc.board.model.vo.BoardParam;
import com.multi.mvc.board.model.vo.BoardReply;

@Mapper
public interface BoardMapper {
	Vector<BoardCategory> selectBoardCategory();
	List<Board> selectBoardList(BoardParam param);
	int selectBoardCount(BoardParam param);
	Board selectBoardByNo(int bno);
	int insertBoard(Board board);
	int updateBoard(Board board);
	int updateReadCount(Board board);
	int deleteBoard(int bno);
	AttachFile selectAttachFileByFno(int fno);
	List<AttachFile> selectAttachFileByBno(int bno);
	int insertAttachFile(AttachFile file);
	int updateAttachFileCounter(int bno);
	int deleteAttachFile(int fno);
	int insertReply(BoardReply reply);
	List<BoardReply> selectReplyByBno(int bno);
	int deleteReply(int rno);
}
