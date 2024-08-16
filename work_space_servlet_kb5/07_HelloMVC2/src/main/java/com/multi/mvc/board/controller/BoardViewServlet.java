package com.multi.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.board.model.vo.Board;

@WebServlet("/board/view")
public class BoardViewServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		Board board = service.findBoardByNo(boardNo, true);
		if(board == null) {
			sendCommonPage("정상적인 접근이 아닙니다.", "/", req, resp);
			return;
		}
		req.setAttribute("board", board);
		req.getRequestDispatcher("/views/board/view.jsp").forward(req, resp);
	}

}
