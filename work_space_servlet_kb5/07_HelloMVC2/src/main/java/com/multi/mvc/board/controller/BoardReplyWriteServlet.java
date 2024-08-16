package com.multi.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.multi.common.util.MyHttpServlet;
import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.board.model.vo.Reply;
import com.multi.mvc.member.model.vo.Member;

@WebServlet("/board/reply")
public class BoardReplyWriteServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Member loginMember = getSessionMember(req);
			String writer = req.getParameter("writer");
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			String content = req.getParameter("content");

			if(loginMember == null || writer == null || writer.equals(loginMember.getId()) == false) {
				sendCommonPage("리플을 등록 할수 없습니다.", "/board/view?boardNo=" + boardNo, req, resp);
				return;
			}
			
			Reply reply = new Reply();
			reply.setBoardNo(boardNo);
			reply.setWriterId(writer);
			reply.setWriterNo(loginMember.getNo());
			reply.setContent(content);
			
			int result = service.saveReply(reply);	
			
			if(result > 0) {
				sendCommonPage("리플 등록에 성공하였습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			}else {
				sendCommonPage("리플을 등록 할수 없습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			}
		} catch (Exception e) {
			sendCommonPage("리플을 등록에 실패하였습니다.", "/", req, resp);
		}
	}
}
