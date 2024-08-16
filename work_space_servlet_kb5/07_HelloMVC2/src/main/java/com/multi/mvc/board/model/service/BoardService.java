package com.multi.mvc.board.model.service;

import static com.multi.common.jdbc.JDBCTemplate.close;
import static com.multi.common.jdbc.JDBCTemplate.commit;
import static com.multi.common.jdbc.JDBCTemplate.getConnection;
import static com.multi.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.multi.common.util.PageInfo;
import com.multi.mvc.board.model.dao.BoardDAO;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.board.model.vo.Reply;

public class BoardService {
	private BoardDAO dao = new BoardDAO();
	
	public int getBoardCount(Map<String, String> searchMap) {
		Connection conn = getConnection();
		int result = dao.getBoardCount(conn, searchMap);
		close(conn);
		return result;
	}
	
	public List<Board> getBoardList(PageInfo pageInfo, Map<String, String> searchMap){
		Connection conn = getConnection();
		List<Board> list = dao.findAll(conn, pageInfo, searchMap);
		close(conn);
		return list;
	}
	
	public int save(Board board) {
		int result = 0;
		Connection conn = getConnection();
		
		if(board.getNo() != 0) {
			result = dao.updateBoard(conn, board);
		}else {
			result = dao.insertBoard(conn, board);
		}
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Board findBoardByNo(int no, boolean hasRead) {
		Connection conn = getConnection();
		Board board = dao.findBoardByNo(conn, no);
		
		// 조회수 증가 로직
		if(hasRead == true && board != null) {
			int result = dao.updateReadCount(conn, board);
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			board.setReadcount(board.getReadcount() + 1);
		}
		
		close(conn);
		return board;
	}
	

	public int delete(int no) {
		Connection conn = getConnection();
		int result = dao.updateStatus(conn, no, "N");
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int saveReply(Reply reply) {
		Connection conn = getConnection();
		int result = dao.insertReply(conn, reply);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteReply(int replyNo) {
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
}






