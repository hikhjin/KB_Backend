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

// 게시글 작성을 위한 첨부파일 처리가 가능한 버전
@WebServlet("/board/write")
public class BoardWriteServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;

	private BoardService service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member loginMember = getSessionMember(req);
		if(loginMember == null) {
			sendCommonPage("로그인 되지 않았습니다.", "/", req, resp);
			return;
		}
		req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
	}
	
	
	// 사용자 post 요청 처리 패턴
	// 1. 파라메터를 읽어온다.
	// 2. 파라메터를 정리하고, 서비스에 요청하여 결과를 받아온다.
	// 3. 결과에 따라 성공, 실패, 기타 페이지로 연결해준다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 파일과 멀티 파라메터 처리를 위한 코드를 추가해야한다.
			// 1. 파일 저장 경로 설정
			String path = getServletContext().getRealPath("/resources/upload/board");
			// 2. 파일 최대 사이즈 지정
			int maxSize = 104857600; // 100MB
			// 3. 문자열 인코딩 셋팅
			String encoding = "UTF-8";
			// 4. 멀티파라메터 처리 객체 생성 - cos.jar 활용
//			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new DefaultFileRenamePolicy());
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			// 멀티 파라메터 처리 객체 선언 끝!
			
			System.out.println("저장 경로 : " + path);
			Member loginMember = getSessionMember(req);
			if(loginMember == null) {
				sendCommonPage("로그인 되지 않았습니다.", "/board/list", req, resp);
				return;
			}
			
			Board board = new Board();
			board.setTitle(mr.getParameter("title").trim());
			board.setWriterId(loginMember.getId());
			board.setWriterNo(loginMember.getNo());
			board.setContent(mr.getParameter("content").trim());
			board.setOriginalFilename(mr.getOriginalFileName("upfile"));
			board.setRenamedFilename(mr.getFilesystemName("upfile"));
			System.out.println(board);
			
			int result = service.save(board); 
			
			if(result > 0) {
				sendCommonPage("게시글이 정상적으로 등록되었습니다.", "/board/list", req, resp); 
			}else {
				sendCommonPage("게시글 등록에 실패하였습니다. (code:102)", "/board/list", req, resp); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("게시글이 등록되지 않았습니다. (code:103)", "/board/list", req, resp);
		}
	}
}





