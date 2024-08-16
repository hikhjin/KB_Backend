package com.multi.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyFileRenamePolicy;
import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/update")
public class BoardUpdateServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		Board board = service.findBoardByNo(boardNo, false);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/views/board/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = getServletContext().getRealPath("/resources/upload/board");
			int maxSize = 104857600; // 100MB;
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			Member loginMember = getSessionMember(req);
			
			if(loginMember == null
					|| loginMember.getId().equals(mr.getParameter("writer")) == false) {
				if(loginMember.getRole().equals("ROLE_ADMIN") == false) {
					sendCommonPage("잘못된 접근입니다. (code=201)", "/board/list", req, resp);
					return;
				}
			}
			
			int boardNo = Integer.parseInt(mr.getParameter("boardNo"));
			Board board = service.findBoardByNo(boardNo, false);
			
			board.setTitle(mr.getParameter("title").trim());
			board.setContent(mr.getParameter("content"));
			
			// 기존 파일 이름 -> 삭제
			String renamedFilename = mr.getParameter("renamed_filename");
			
			// 재업로드 된 파일 이름
			String originalReloadFileName = mr.getOriginalFileName("upfile");
			String renamedReloadFileName = mr.getFilesystemName("upfile");
			
			if(originalReloadFileName != null && originalReloadFileName.length() > 0) {
				// 파일 수정 된 상태.
				deleteFile(renamedFilename);
				board.setOriginalFilename(originalReloadFileName);
				board.setRenamedFilename(renamedReloadFileName);
			}
			
			int result = service.save(board); 
			if(result > 0) {
				sendCommonPage("게시글이 정상적으로 수정되었습니다.", "/board/view?boardNo="+boardNo, req, resp);
			}else {
				sendCommonPage("게시글 수정에 실패하였습니다.", "/board/list", req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("잘못된 요청입니다.", "/board/list", req, resp);
		}
	}
}
