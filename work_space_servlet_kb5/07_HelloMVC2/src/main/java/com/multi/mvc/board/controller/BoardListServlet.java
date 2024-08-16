package com.multi.mvc.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.common.util.PageInfo;
import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.board.model.vo.Board;

@WebServlet("/board/list")
public class BoardListServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		Map<String, String> searchMap = new HashMap<>();
		try {
			String searchValue = req.getParameter("searchValue");
			if(searchValue != null && searchValue.length() > 0) {
				String searchType = req.getParameter("searchType");
				searchMap.put(searchType, searchValue);
			}
			
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {}
		
		int boardCount = service.getBoardCount(searchMap);
		
		PageInfo pageInfo = new PageInfo(page, 10, boardCount, 10);
//		PageInfo pageInfo = new PageInfo(page, 10, boardCount, 20); // 게시글 수 20개, 페이지 최대 숫자 10개
//		PageInfo pageInfo = new PageInfo(page, 4, boardCount, 10); // 게시글 수 10개, 페이지 최대 숫자 4개
		
		List<Board> list = service.getBoardList(pageInfo, searchMap);
		
		req.setAttribute("list", list);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/board/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
