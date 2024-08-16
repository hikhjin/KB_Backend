package com.multi.mybatis.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.multi.mybatis.board.model.service.BoardService;
import com.multi.mybatis.board.model.vo.Board;
import com.multi.mybatis.board.model.vo.Reply;
import com.multi.mybatis.board.model.vo.BoardForm;
import com.multi.mybatis.common.PageInfo;

public class BoardRun {
	public static void main(String[] args) {
		BoardService service = new BoardService();
		Map<String, String> map = null;
		
		System.out.println("1. 게시글 조회");
		List<Board> list = service.getBoardList();
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("2. 게시글 조회 - writer : admin");
		map = new HashMap<String, String>();
		map.put("writer", "admin");
		list = service.getBoardList(map);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("3. 게시글 조회 - 제목 : 아이폰");
		map = new HashMap<String, String>();
		map.put("title", "아이폰");
		list = service.getBoardList(map);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("4. 게시글 조회 - 내용 : 갤럭시");
		map = new HashMap<String, String>();
		map.put("content", "갤럭시");
		list = service.getBoardList(map);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("5. 게시글 조회 - 제목 : 구매 / 내용 : 맥북");
		BoardForm boardForm = new BoardForm();
		boardForm.setTitle("구매");
		boardForm.setContent("맥북");
		list = service.getBoardListByDTO(boardForm);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("6. 게시글 조회 - Pageing 처리");
		int count = service.getBoardCount(new BoardForm());
		PageInfo pageInfo = new PageInfo(2, 10, count, 10);
		BoardForm boardForm2 = new BoardForm();
		boardForm2.setPageInfo(pageInfo);
		list = service.getBoardListForPage(boardForm2);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("7. 게시글 조회 - Pageing 처리 + 검색, 검색어 : 갤럭시");
		BoardForm boardForm3 = new BoardForm();
		boardForm3.setTitle("갤럭시");
		count = service.getBoardCount(boardForm3);
		pageInfo = new PageInfo(1, 10, count, 10);
		boardForm3.setPageInfo(pageInfo);
		list = service.getBoardListForPage(boardForm3);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");

		
		System.out.println("8. 게시글 조회 - Pageing 처리 + TypeFilter 'B1, B2' ");
		List<String> filters = new ArrayList<>();
		filters.add("B1");
		filters.add("B2");
		count = service.getBoardCountByTypeFilter(filters);
		pageInfo = new PageInfo(1, 10, count, 10);
		list = service.getBoardListByTypeFilter(filters, pageInfo);
		for(Board b : list) {
			System.out.println(b);
		}
		System.out.println("------------------------------------------\n");
		
		
		System.out.println("9. 게시글 상세 조회");
		Board board = service.findBoardByNo(1);
		System.out.println(board);
		List<Reply> list2 = board.getReplies();
		for(Reply r : list2) {
			System.out.println("리플 : " + r);
		}
		System.out.println("------------------------------------------\n");
	}
}
