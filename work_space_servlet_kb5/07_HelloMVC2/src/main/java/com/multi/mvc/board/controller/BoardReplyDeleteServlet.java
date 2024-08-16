package com.multi.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.member.model.vo.Member;

@WebServlet("/board/replydel")
public class BoardReplyDeleteServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member loginMember = getSessionMember(req);
			int replyNo = Integer.parseInt(req.getParameter("replyNo"));
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));

			if(loginMember == null) {
				sendCommonPage("리플을 삭제 할수 없습니다.", "/board/view?boardNo=" + boardNo, req, resp);
				return;
			}
			
			int result = service.deleteReply(replyNo);
			
			if(result > 0) {
				sendCommonPage("리플 삭제에 성공하였습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			}else {
				sendCommonPage("리플을 삭제 할수 없습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			}
		} catch (Exception e) {
			sendCommonPage("리플을 등록에 실패하였습니다.", "/", req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
