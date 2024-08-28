package com.multi.mvc.board.model.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.multi.mvc.board.model.mapper.BoardMapper;
import com.multi.mvc.board.model.vo.AttachFile;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.board.model.vo.BoardCategory;
import com.multi.mvc.board.model.vo.BoardParam;
import com.multi.mvc.board.model.vo.BoardReply;

@Service
public class BoardService {
	// 1. DAO 사용시 선언
	//	@Autowired
	//	private BoardDao dao;
	//	@Autowired
	//	private SqlSessionTemplate sqlSession;
	
	// 2. Mapper 사용시 선언
	@Autowired
	private BoardMapper mapper;

	public Vector<BoardCategory> getBoardCategory() {
		return mapper.selectBoardCategory();
	}

	public int getBoardCount(BoardParam param) {
		return mapper.selectBoardCount(param);
	}
	
	public List<Board> getBoardList(BoardParam param) {
		return mapper.selectBoardList(param);
	}


	@Transactional(rollbackFor = Exception.class)
	public Board findBoardByBNo(int bno) {
		Board board = mapper.selectBoardByNo(bno);

		// 댓글 리스트 가져오기
		List<BoardReply> rList = mapper.selectReplyByBno(bno);
		board.setReplies(rList);
		
		// 파일 리스트 가져오기
		List<AttachFile> fList = mapper.selectAttachFileByBno(bno);
		board.setAttachFiles(fList);
		
		// 게시글 조회수 업데이트
		board.setReadCount(board.getReadCount() + 1);
		mapper.updateReadCount(board);
		
		return board;
	}

	private static int count = 0; // 파일 식별을 위한 count 값
	public String saveFile(MultipartFile upfile, String savePath) {
		File folder = new File(savePath);
		
		// 폴더가 존재하지 않을때 해당 경로를 만드는 코드
		if(folder.exists() == false) {
			folder.mkdirs();
		}
		System.out.println(savePath);
		
		// 파일 이름을 날짜시간 + count를 더해서 만드는 코드, test.jpg -> 20240112_1044232_1.jpg
		String originalFilename = upfile.getOriginalFilename();
		String renamedFilename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmssSSS"));
		renamedFilename += "_" + (count++);
		renamedFilename += originalFilename.substring(originalFilename.lastIndexOf(".")); // .jpg
		String renamedSavePath = savePath + "/" + renamedFilename;
		
		try {
			// 실제 파일을 디스크에 쓰는 로직
			upfile.transferTo(new File(renamedSavePath));
		} catch (Exception e) {
			return null;
		}
		return renamedFilename;
	}

	@Transactional(rollbackFor = Exception.class)
	public int saveBoard(Board board) throws Exception {
		
		int result = 0;
		if(board.getBno() == 0) { // insert
			result = mapper.insertBoard(board);
			if(result <= 0) {
				throw new Exception("Board insert error!");
			}
		} else { // update
			result = mapper.updateBoard(board);
			if(result <= 0) {
				throw new Exception("Board update error!");
			}
		}
		
		if(board.getAttachFiles() != null) {
			for(AttachFile file : board.getAttachFiles()) {
				file.setBno(board.getBno());
				result = mapper.insertAttachFile(file);
				if(result <= 0) {
					throw new Exception("AttachFile insert error!");
				}
			}
			result = mapper.updateAttachFileCounter(board.getBno());
			if(result <= 0) {
				throw new Exception("AttachFile update error!");
			}
		}
		
		return result;
	}

	public AttachFile findAttachFile(int fno) {
		return mapper.selectAttachFileByFno(fno);
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteBoard(int bno, String savePath) throws Exception {
		Board board = this.findBoardByBNo(bno);
		int result = 0;
		
		if(board.getAttachFiles() != null) {
			for(AttachFile file : board.getAttachFiles()) {
				deleteFile(savePath, file);
				result = mapper.deleteAttachFile(file.getFno());
				if(result <= 0) {
					throw new Exception("file delete error!");
				}
			}
		}
		return mapper.deleteBoard(bno);
	}

	public void deleteFile(String savePath, AttachFile arrachFile) {
		File file = new File(savePath +"/" + arrachFile.getRenamedFilename());
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int deleteAttachFile(AttachFile arrachFile) {
		return mapper.deleteAttachFile(arrachFile.getFno());
	}

	@Transactional(rollbackFor = Exception.class)
	public int saveBoardReply(BoardReply reply) {
		return mapper.insertReply(reply);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int deleteBoardReply(int rno) {
		return mapper.deleteReply(rno);
	}
}

















