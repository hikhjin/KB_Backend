package com.multi.mvc.board.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.multi.mvc.board.model.service.BoardService;
import com.multi.mvc.board.model.vo.AttachFile;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.board.model.vo.BoardCategory;
import com.multi.mvc.board.model.vo.BoardParam;
import com.multi.mvc.board.model.vo.BoardReply;
import com.multi.mvc.common.util.PageInfo;
import com.multi.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/board") // 상위 borad로 시작하는 url을 생략해서 사용할 수 있음.
public class BoardController {

	@Autowired
	private BoardService service;

	@Autowired
	private ResourceLoader resourceLoader; // 파일 업로드 기능시 활용하는 loader
	
	
	// 카테고리를 공용적으로 사용할때 사용할 Map, List 멤버변수
	// -> 혹시모를 병행처리를 위해 Threadsafe한 클래스로 정리
	private static Vector<BoardCategory> categoryList;
	private static ConcurrentHashMap<String, String> typeMap; 
	
	// Controller가 실행될때 한번만 초기화하는 메소드
	@Bean(initMethod = "init")
	public void init() {
		categoryList = service.getBoardCategory();
		typeMap = new ConcurrentHashMap<String, String>();
		for(BoardCategory item : categoryList) {
			typeMap.put(item.getType(), item.getName());
		}
	}

	
	//http://localhost/mvc/board/list?page=1&searchType=title&searchValue=%EB%85%B8%ED%8A%B8%EB%B6%81
	@RequestMapping("/board/list")
	public String list(Model model, BoardParam param) {
		log.debug("@@ board list 요청 param : " + param);
		
		int boardCount = service.getBoardCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 10, boardCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		List<Board> list = service.getBoardList(param);
		
		System.out.println(categoryList);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("param", param);
		model.addAttribute("typeList", param.getTypeList());
		
		// 공지사항 분류하는 법
		if(param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("NBOARD")) {
			return "/board/noticeList";
		}
		
		return "/board/boardList";
	}
	
	// board/view?no=1
	@RequestMapping("/board/view")
	public String view(Model model, @RequestParam("no") int no) {
		Board board = null;
		try {
			board = service.findBoardByBNo(no);
		} catch (Exception e) {}
		if(board == null) {
			return "redirect:error";
		}
		
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("board", board);
		
		return "board/boardView";
	}
	
	
	@GetMapping("/board/write")
	public String writeView(Model model) {
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("categoryList", categoryList);
		return "board/boardWrite";
	}
	
	@PostMapping("/board/write")
	public String write(Model model, HttpSession session,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@ModelAttribute Board board,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles
			) {
		log.debug("board write 요청, board : " + board +", upfiles : " + upfiles +", " + upfiles.size());
		
		// 보안코드나 파라메터 검사 예시
		if(loginMember == null || loginMember.getId().equals(board.getMemberId()) == false) {
			model.addAttribute("msg","잘못된 접근 입니다.");
			model.addAttribute("location", "/");
			return "common/msg";
		}
		board.setMno(loginMember.getMno());
		
		// 파일처리부
		String rootPath = session.getServletContext().getRealPath("resources");
		String savePath = rootPath + "/upload/board"; // 파일 저장 경로
		log.debug("savePath : " + savePath);
		
		List<AttachFile> attachFileList = new ArrayList<AttachFile>();
		
		// 파일 저장 로직
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장되는 로직
			if(renamedFileName != null) {
				AttachFile file = new AttachFile();
				file.setOriginalFilename(upfile.getOriginalFilename());
				file.setRenamedFilename(renamedFileName);
				attachFileList.add(file);
			}
		}
		board.setAttachFiles(attachFileList);
		
		int result = 0;
		try {
			result = service.saveBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			model.addAttribute("msg", "게시글이 등록 되었습니다.");
			model.addAttribute("location", "/board/view?no=" + board.getBno());
		}else {
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.");
			model.addAttribute("location", "/board/list");
		}
		return "common/msg";
	}
	
	// http://localhost/mvc/board/update?no=157
	@GetMapping("/board/update")
	public String updateView(Model model,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@RequestParam int no
			) {
		Board board = service.findBoardByBNo(no);
		model.addAttribute("board", board);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("categoryList", categoryList);
		return "/board/boardUpdate";
	}
	
	@PostMapping("/board/update")
	public String update(Model model, HttpSession session,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@ModelAttribute Board board,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles
			) {
		log.debug("board update 요청, board : " + board +", upfiles : " + upfiles +", " + upfiles.size());
		
		// 보안코드나 파라메터 검사 예시 -> 프로젝트 할때는 지우고 활용
		if(loginMember == null || loginMember.getId().equals(board.getMemberId()) == false) {
			model.addAttribute("msg","잘못된 접근 입니다.");
			model.addAttribute("location", "/");
			return "common/msg";
		}
		
		Board prevBoard = service.findBoardByBNo(board.getBno());
		board.setMno(loginMember.getMno());
		
		List<AttachFile> attachFileList = new ArrayList<AttachFile>();
		String rootPath = session.getServletContext().getRealPath("resources");
		String savePath = rootPath + "/upload/board";
		System.out.println("savePath : " + savePath);
		
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath);
			
			if(renamedFileName != null) {
				AttachFile file = new AttachFile();
				file.setOriginalFilename(upfile.getOriginalFilename());
				file.setRenamedFilename(renamedFileName);
				attachFileList.add(file);
			}
		}
		
		if(attachFileList.size() != 0) {
			// 기존 파일 삭제
			List<AttachFile> prevAttachFileList = prevBoard.getAttachFiles();
			for(AttachFile file : prevAttachFileList) {
				service.deleteFile(savePath, file);
				service.deleteAttachFile(file);
			}
		}
		board.setAttachFiles(attachFileList);
		
		int result = 0;
		try {
			result = service.saveBoard(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result > 0) {
			model.addAttribute("msg", "게시글이 수정이 완료 되었습니다.");
			model.addAttribute("location", "/board/view?no=" + board.getBno());
		}else {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다.");
			model.addAttribute("location", "/board/list");
		}
		return "common/msg";
	}
	
	
	
	
	// http://localhost/mvc/board/delete?no=162
	@RequestMapping("/board/delete")
	public String deleteBoard(Model model, HttpSession session,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@RequestParam int no) {
		log.debug("게시글 삭제 요청 no : " + no);
		
		String rootPath = session.getServletContext().getRealPath("resources");
		String savePath = rootPath +"/upload/board";
		
		int result = 0;
		try {
			result = service.deleteBoard(no, savePath);
		} catch (Exception e) {}
		
		if(result > 0 ) {
			model.addAttribute("msg", "게시글이 삭제 되었습니다.");
		} else {
			model.addAttribute("msg", "게시글이 삭제에 실패하였습니다.");
		}
		model.addAttribute("location", "/board/list");
		return "/common/msg";
	}
	
//	<form action="${path}/board/reply" method="post">
//	<input type="hidden" name="bno" value="${board.bno}" />
//	<input type="hidden" name="memberId" value="${loginMember.id}" />
//	<textarea name="content" id="replyContent" cols="55" rows="3"></textarea>
//	<button type="submit" id="btn-insert">등록</button>	  	
//	</form>
	
	
	@PostMapping("/board/reply")
	public String writeReply(Model model,
			@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@ModelAttribute BoardReply reply) {
		log.debug("writeReply : " + reply);
		
		reply.setMno(loginMember.getMno());
		int result = service.saveBoardReply(reply);
		
		if(result > 0 ) {
			model.addAttribute("msg", "리플 등록이 되었습니다.");
		} else {
			model.addAttribute("msg", "리플 등록에 실패하였습니다.");
		}
		model.addAttribute("location", "/board/view?no="+reply.getBno());
		return "/common/msg";
	}
	
	@RequestMapping("board/replyDel")
	public String deleteReply(Model model, 
			@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int rno, int bno
			){
		log.info("리플 삭제 요청");
		int result = service.deleteBoardReply(rno);
		
		if(result > 0) {
			model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
		}else {
			model.addAttribute("msg", "리플 삭제에 실패하였습니다.");
		}
		model.addAttribute("location", "/board/view?no=" + bno);
		return "/common/msg";
	}
	
	
	// 파일 다운로드 하는 코드
	// board/fileDown?fno=14
	@RequestMapping("board/fileDown")
	public ResponseEntity<Resource> fileDown(
			@RequestParam("fno") int fno,
			@RequestHeader(name="user-agent") String userAgent
			){
		try {
			AttachFile file = service.findAttachFile(fno);

			Resource resource = resourceLoader.getResource("resources/upload/board/" + file.getRenamedFilename());
			String downName = null;
			
			// 인터넷 익스플로러 인 경우
			boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;

			if (isMSIE) { // 익스플로러 처리하는 방법
				downName = URLEncoder.encode(file.getOriginalFilename(), "UTF-8").replaceAll("\\+", "%20");
			} else {    		
				downName = new String(file.getOriginalFilename().getBytes("UTF-8"), "ISO-8859-1"); // 크롬
			}
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + downName + "\"")
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())
					.body(resource);
		} catch (Exception e) {}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 실패했을 경우
	}
	

	@GetMapping("/error")
	public String error() {
		return "/common/error";
	}
	
}










