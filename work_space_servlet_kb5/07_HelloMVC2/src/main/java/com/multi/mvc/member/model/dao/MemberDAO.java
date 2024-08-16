package com.multi.mvc.member.model.dao;

import static com.multi.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.multi.mvc.member.model.vo.Member;

public class MemberDAO {

	public Member selectMemberById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
		
		try {
			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
			pstmt.setString(1, id);					// ? 키워드에 특정 문자열로 채워주는 코드
			rs = pstmt.executeQuery();				// 실제 DB에 쿼리를 요청하여 실행하고, 결과값을 가져오는 코드
			
			if(rs.next()) { // 결과값에 다음 값이 있는 경우
				Member m = new Member();
				m.setNo(rs.getInt("no"));
				m.setId(rs.getString("id"));
				m.setPassword(rs.getString("password"));
				m.setRole(rs.getString("role"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setStatus(rs.getString("status"));
				m.setEnrollDate(rs.getDate("enroll_date"));
				m.setModifyDate(rs.getDate("modify_date"));
				return m;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);	// rs 닫는 코드
			close(pstmt); // 쿼리 닫는 코드
		}
		return null;
	}
	
	// 회원 가입
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(0,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	// pstmt에 쿼리 셋팅하는 코드
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone()); 
			pstmt.setString(5, member.getEmail()); 
			pstmt.setString(6, member.getAddress()); 
			pstmt.setString(7, member.getHobby()); // "게임,야구,축구" 
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 사용자 정보 수정
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET NAME=?,PHONE=?,EMAIL=?,"
							+ "ADDRESS=?,HOBBY=?,MODIFY_DATE=CURRENT_TIMESTAMP WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone()); 
			pstmt.setString(3, member.getEmail()); 
			pstmt.setString(4, member.getAddress()); 
			pstmt.setString(5, member.getHobby()); 
			pstmt.setInt(6, member.getNo()); 
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 탙퇴하기
	public int updateStatus(Connection conn, int no, String status) {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET STATUS=? WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 패스워드 변경하기
	public int updatePassword(Connection conn, int no, String password) {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET PASSWORD=? WHERE NO=?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, password);
			pstmt.setInt(2, no);
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
//		MemberDAO dao = new MemberDAO();
//		
//		// 조회하기
//		Member member = dao.selectMemberById(conn, "admin");
//		System.out.println(member.toString().replace(",", ",\n"));
//		System.out.println("-----------------------------------------------------\n");
//		
//		// 회원 가입 테스트
//		Member member2 = new Member();
//		member2.setId("test12");
//		member2.setPassword("1234");
//		member2.setName("최길동");
//		member2.setPhone("010-1234-5678");
//		member2.setEmail("test2@email.com");
//		member2.setAddress("서울시 강남구 도곡동");
//		member2.setHobby("게임,야구,농구");
//		
//		int result = dao.insertMember(conn, member2);
//		System.out.println("회원 가입 결과 : " + result);
//		Member member3 = null;
//		if(result > 0) {
//			System.out.println("회원 가입 성공!!");
//			// 가입 결과 조회
//			member3 = dao.selectMemberById(conn, "test12");
//			System.out.println(member3.toString().replace(",", ",\n"));
//		}
//		System.out.println("-----------------------------------------------------\n");
//		
//		// 회원 업데이트 테스트
//		member3.setName("차은우");
//		member3.setPhone("010-3214-2323");
//		member3.setAddress("경기도 안양시 동안구 평촌동");
//		result = dao.updateMember(conn, member3);
//		if(result > 0) {
//			System.out.println("회원정보 수정 성공");
//			member3 = dao.selectMemberById(conn, "test12");
//			System.out.println(member3.toString().replace(",", ",\n"));
//		}
//		System.out.println("-----------------------------------------------------\n");
//
//		
//		result = dao.updatePassword(conn, member3.getNo(), "test1234");
//		if(result > 0) {
//			System.out.println("비밀번호 수정 성공");
//			member3 = dao.selectMemberById(conn, "test12");
//			System.out.println(member3.toString().replace(",", ",\n"));
//		}
//		System.out.println("-----------------------------------------------------\n");
//		
//	
//		result = dao.updateStatus(conn, member3.getNo(), "N");
//		if(result > 0) {
//			System.out.println("회원 탈퇴 성공");
//			member3 = dao.selectMemberById(conn, "test12");
//			System.out.println(member3.toString().replace(",", ",\n"));
//		}
//		System.out.println("-----------------------------------------------------\n");
//	}
}
