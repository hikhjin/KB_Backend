package com.multi.mvc.board.model.dao;

import static com.multi.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.multi.common.util.PageInfo;
import com.multi.mvc.board.model.vo.Board;
import com.multi.mvc.board.model.vo.Reply;

public class BoardDAO {

//	public Member selectMemberById(Connection conn, String id) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
//		
//		try {
//			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
//			pstmt.setString(1, id);					// ? 키워드에 특정 문자열로 채워주는 코드
//			rs = pstmt.executeQuery();				// 실제 DB에 쿼리를 요청하여 실행하고, 결과값을 가져오는 코드
//			
//			if(rs.next()) { // 결과값에 다음 값이 있는 경우
//				Member m = new Member();
//				m.setNo(rs.getInt("no"));
//				m.setId(rs.getString("id"));
//				m.setPassword(rs.getString("password"));
//				m.setRole(rs.getString("role"));
//				m.setName(rs.getString("name"));
//				m.setPhone(rs.getString("phone"));
//				m.setEmail(rs.getString("email"));
//				m.setAddress(rs.getString("address"));
//				m.setHobby(rs.getString("hobby"));
//				m.setStatus(rs.getString("status"));
//				m.setEnrollDate(rs.getDate("enroll_date"));
//				m.setModifyDate(rs.getDate("modify_date"));
//				return m;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);	// rs 닫는 코드
//			close(pstmt); // 쿼리 닫는 코드
//		}
//		return null;
//	}
//	
//	// 회원 가입
//	public int insertMember(Connection conn, Member member) {
//		PreparedStatement pstmt = null;
//		String query = "INSERT INTO MEMBER VALUES(0,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
//		int result = 0;
//		
//		try {
//			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
//			pstmt.setString(1, member.getId());
//			pstmt.setString(2, member.getPassword());
//			pstmt.setString(3, member.getName());
//			pstmt.setString(4, member.getPhone()); 
//			pstmt.setString(5, member.getEmail()); 
//			pstmt.setString(6, member.getAddress()); 
//			pstmt.setString(7, member.getHobby()); // "게임,야구,축구" 
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	// 게시물 갯수 가져오는 쿼리문
	public int getBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM BOARD WHERE STATUS='Y'";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);	
			rs = pstmt.executeQuery();			
			
			if(rs.next()) { // 결과값에 다음 값이 있는 경우
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	// rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return result;
	}
	
	// 게시글의 리스트를 가져오는 메소드 -> 동적 쿼리!!
	// PageInfo : 페이징 처리가 가능하도록 만든 객체
	// searchMap : 게시글 검색을 위한 정보 
	public List<Board> findAll(Connection conn, PageInfo pageInfo, Map<String, String> searchMap) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		String queryBefore = "SELECT  B.NO, B.TITLE, M.ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS "
				+ "FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "WHERE 1 = 1 "
				+ "AND B.STATUS = 'Y' ";
		//※주의1 : 쿼리가 길어질 경우 여러라인이라면 반드시 끝에 ' '(공백)을 둬야한다.
		//※주의2 : ;(세미콜론) 제거 필수!!
		
		// 동적 쿼리 셋팅하는 코드, 각각 검색이 필요한 경우에만 쿼리를 변경하여 작성한다.
		if(searchMap.containsKey("title")) {
			queryBefore += "AND B.TITLE LIKE ? ";
		}
		if(searchMap.containsKey("content")) {
			queryBefore += "AND B.CONTENT LIKE ? ";
		}
		if(searchMap.containsKey("writer")) {
			queryBefore += "AND M.ID LIKE ? ";
		}
		String queryAfter = "ORDER BY B.NO DESC LIMIT ? OFFSET ? ";
		String query = queryBefore + queryAfter;
		
		try {
			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
			int count = 1;
			if(searchMap.containsKey("title")) {
				pstmt.setString(count++, "%" + searchMap.get("title") + "%");
			}
			if(searchMap.containsKey("content")) {
				pstmt.setString(count++, "%" + searchMap.get("content") + "%");
			}
			if(searchMap.containsKey("writer")) {
				pstmt.setString(count++, "%" + searchMap.get("writer") + "%");
			}
			pstmt.setInt(count++, pageInfo.getListLimit()); 
			pstmt.setInt(count++, pageInfo.getStartList() - 1);
			
			rs = pstmt.executeQuery();				
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFilename(rs.getString("ORIGINAL_FILENAME"));
				board.setReadcount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	// rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return list;
	}
	
	
	// 게시글의 갯수 가져오는 메소드 (검색 된 수 만큼)
	public int getBoardCount(Connection conn, Map<String, String> searchMap) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String query= "SELECT  COUNT(*) "
				+ "FROM BOARD B "
				+ "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "WHERE 1=1 "
				+ "AND B.STATUS = 'Y' ";
		
		if(searchMap.containsKey("title")) {
			query+= "AND B.TITLE LIKE ? ";
		}
		if(searchMap.containsKey("content")) {
			query+= "AND B.CONTENT LIKE ? ";
		}
		if(searchMap.containsKey("writer")) {
			query+= "AND M.ID LIKE ? ";
		}
		try {
			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
			int count = 1;
			if(searchMap.containsKey("title")) {
				pstmt.setString(count++, "%" + searchMap.get("title") + "%");
			}
			if(searchMap.containsKey("content")) {
				pstmt.setString(count++, "%" + searchMap.get("content") + "%");
			}
			if(searchMap.containsKey("writer")) {
				pstmt.setString(count++, "%" + searchMap.get("writer") + "%");
			}
			
			rs = pstmt.executeQuery();				
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	// rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return result;
	}
	
	
	// 게시글 작성
	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO BOARD VALUES(0,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
			pstmt.setInt(1, board.getWriterNo()); 
			pstmt.setString(2, board.getTitle()); 
			pstmt.setString(3, board.getContent()); 
			pstmt.setString(4, ""); 
			pstmt.setString(5, board.getOriginalFilename()); 
			pstmt.setString(6, board.getRenamedFilename()); 
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 게시글 상세보기
	public Board findBoardByNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.NO, B.TITLE, M.ID, B.READCOUNT, "
				+ "B.ORIGINAL_FILENAME, B.RENAMED_FILENAME, B.CONTENT, B.CREATE_DATE, B.MODIFY_DATE "
				+ "FROM BOARD B "
				+ "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "WHERE B.STATUS = 'Y' AND B.NO = ? ";
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, boardNo);					
			rs = pstmt.executeQuery();				
			
			if(rs.next()) { // 결과값에 다음 값이 있는 경우
				Board b = new Board();
				b.setNo(rs.getInt("NO"));
				b.setTitle(rs.getString("TITLE"));
				b.setWriterId(rs.getString("ID"));
				b.setReadcount(rs.getInt("READCOUNT"));
				b.setOriginalFilename(rs.getString("ORIGINAL_FILENAME"));
				b.setRenamedFilename(rs.getString("RENAMED_FILENAME"));
				b.setContent(rs.getString("CONTENT"));
				b.setCreateDate(rs.getDate("CREATE_DATE"));
				b.setModifyDate(rs.getDate("MODIFY_DATE"));
				b.setReplies(this.getRepliesByNo(conn, boardNo));
				return b;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	// rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return null;
	}
	
	// 게시글 조회수 올려주기
	public int updateReadCount(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET READCOUNT=? WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, board.getReadcount() + 1); 
			pstmt.setInt(2, board.getNo()); 
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 게시글 삭제하기 (실제로는 안보여주는 기능)
	public int updateStatus(Connection conn, int boardNo, String status) {
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET STATUS=? WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, status); 
			pstmt.setInt(2, boardNo); 
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	// 게시글 수정
	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET TITLE=?,"
				+ " CONTENT=?,ORIGINAL_FILENAME=?,"
				+ " RENAMED_FILENAME=?,MODIFY_DATE=CURRENT_TIMESTAMP WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, board.getTitle()); 
			pstmt.setString(2, board.getContent()); 
			pstmt.setString(3, board.getOriginalFilename()); 
			pstmt.setString(4, board.getRenamedFilename()); 
			pstmt.setInt(5,board.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	// 리플을 가져오는 메소드
	public List<Reply> getRepliesByNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<>();
		String query = "SELECT R.NO, R.BOARD_NO, R.CONTENT, M.ID, R.CREATE_DATE, R.MODIFY_DATE "
				+ "FROM REPLY R "
				+ "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
				+ "WHERE R.STATUS='Y' AND BOARD_NO= ?  "
				+ "ORDER BY R.NO DESC ";
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, boardNo);				
			rs = pstmt.executeQuery();			
			
			while(rs.next()) { 
				Reply r = new Reply();
				r.setNo(rs.getInt("NO"));
				r.setBoardNo(rs.getInt("BOARD_NO"));
				r.setContent(rs.getString("CONTENT"));
				r.setWriterId(rs.getString("ID"));
				r.setCreateDate(rs.getDate("CREATE_DATE"));
				r.setModifyDate(rs.getDate("MODIFY_DATE"));
				list.add(r);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	  // rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return list;
	}
	
	

	// 리플 쓰기 기능
	public int insertReply(Connection conn, Reply reply) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO REPLY VALUES(0, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 리플 삭제 기능
	public int deleteReply(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM REPLY WHERE NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
//	public static void main(String[] args) {
//		Connection conn = getConnection();
//		BoardDAO dao = new BoardDAO();
//		
//		// 게시물 갯수
//		int count = dao.getBoardCount(conn);
//		System.out.println("총 게시물 수 : " + count);
//		System.out.println("-----------------------------------------------");
//		
//		
//		// 게시글 가져오기 (검색X)
//		/**
//		 * @param currentPage 현재 페이지
//		 * @param pageLimit 한 페이지에 보여질 페이지의 수  = 페이지 기준으로 보여질 숫자
//		 * @param listCount 전체 리스트의 수 = 총게시물 숫자 
//		 * @param listLimit 한 페이지에 표시될 리스트의 수  = 한페이지에 보여질 게시물 수
//		 */
//		PageInfo pageInfo = new PageInfo(1, 10, count, 12);
//		Map<String, String> searchMap = new HashMap<>();
//		List<Board> list = dao.findAll(conn, pageInfo, searchMap);
//		for(Board b : list) {
//			System.out.println(b.toString());
//		}
//		System.out.println("-----------------------------------------------");
//		
//		// 게시글 검색 + 페이지
//		searchMap = new HashMap<>();
//		searchMap.put("title", "아이폰");
//		count = dao.getBoardCount(conn, searchMap);
//		pageInfo = new PageInfo(1, 10, count, 10);
//		list = dao.findAll(conn, pageInfo, searchMap);
//		System.out.println("검색 결과 갯수 : " + count);
//		System.out.println(searchMap);
//		for(Board b : list) {
//			System.out.println(b.toString());
//		}
//		System.out.println("-----------------------------------------------");
//		
//		// 게시글 작성 테스트
//		System.out.println("게시글 작성!!");
//		Board board = new Board();
//		board.setWriterNo(1);
//		board.setTitle("자바에서 올린 타이틀");
//		board.setContent("자바에서 올린 내용물");
//		board.setOriginalFilename("");
//		board.setRenamedFilename("");
//		int result = dao.insertBoard(conn, board);
//		System.out.println("작성 결과 : " + result);
//		System.out.println("-----------------------------------------------");
//		
//		// 게시글 상세 조회
//		System.out.println("상세 조회");
//		board = dao.findBoardByNo(conn, 1);
//		System.out.println(board.toString());
//		System.out.println("-----------------------------------------------");
//		
//	}
	
}
