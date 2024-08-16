package com.multi.mybatis.board.model.service;

import static com.multi.mybatis.common.SqlSessionTemplate.getSession;

import java.util.List;
import java.util.Map;

import com.multi.mybatis.board.model.vo.BoardForm;
import org.apache.ibatis.session.SqlSession;

import com.multi.mybatis.board.model.dao.BoardDao;
import com.multi.mybatis.board.model.vo.Board;
import com.multi.mybatis.common.PageInfo;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public List<Board> getBoardList() {
		return getBoardListByDTO(null);
		
	}
	public List<Board> getBoardList(Map<String, String> map) {
		SqlSession session = getSession();
		List<Board> result = dao.getBoardList(session, map);
		session.close();
		return result;
	}

	public List<Board> getBoardListByDTO(BoardForm boardForm) {
		SqlSession session = getSession();
		List<Board> result = dao.getBoardList(session, boardForm);
		session.close();
		return result;
	}

	public List<Board> getBoardListForPage(BoardForm boardForm) {
		SqlSession session = getSession();
		List<Board> result = dao.getBoardListForPage(session, boardForm);
		session.close();
		return result;
	}

	public int getBoardCount(BoardForm searchInfo) {
		SqlSession session = getSession();
		int result = dao.getBoardCount(session, searchInfo);
		session.close();
		return result;
	}

	public int getBoardCount(Map<String, String> map) {
		SqlSession session = getSession();
		int result = dao.getBoardCount(session, map);
		session.close();
		return result;
	}


	public List<Board> getBoardListByTypeFilter(List<String> filterList) {
		SqlSession session = getSession();
		List<Board> result = dao.getBoardListByTypeFilter(session, filterList);
		session.close();
		return result;
	}

	public int getBoardCountByTypeFilter(List<String> filterList) {
		SqlSession session = getSession();
		int result = dao.getBoardCountByTypeFilter(session, filterList);
		session.close();
		return result;
	}

	public List<Board> getBoardListByTypeFilter(List<String> filterList, PageInfo pageInfo) {
		SqlSession session = getSession();
		List<Board> result = dao.getBoardListByTypeFilter(session, filterList, pageInfo);
		session.close();
		return result;
	}

	public Board findBoardByNo(int boardNo) {
		SqlSession session = getSession();
		Board result = dao.findBoardByNo(session, boardNo);
		session.close();
		return result;
	}
}
