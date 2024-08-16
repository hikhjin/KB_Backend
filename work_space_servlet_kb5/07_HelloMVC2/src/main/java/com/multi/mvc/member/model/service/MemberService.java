package com.multi.mvc.member.model.service;


import static com.multi.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;

import com.multi.mvc.member.model.dao.MemberDAO;
import com.multi.mvc.member.model.vo.Member;

/** 
 * 멤버에 관련된 기능 구현
 * 기능 : 로그인, 회원가입, 회원 탈퇴, 정보수정
 */
public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	public Member findMemberById(String id) {
		Connection conn = getConnection();
		Member member = dao.selectMemberById(conn, id);
		close(conn);
		return member;
	}
	
	// 로그인 기능, id pw를 DB에서 대조하여 맞으면 Member 리턴, 실패는 null return
	public Member login(String id, String pw) {
		Member member = findMemberById(id);

		// 꼼수 기능, 향후 pw를 hash 코드로 대처할건데, admin은 기존대로 사용하기 위한 코드
		if(member != null && member.getId().equals("admin")) {
			return member;
		}
		
		if(member != null && member.getPassword().equals(pw)) {
			return member; // 성공!!
		}{
			return null; // 실패
		}
	}
	
	// 회원가입
	public int join(Member member) {
		Connection conn = getConnection();
		int result = dao.insertMember(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 회원가입 정보 수정
	public int update(Member member) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
//	join + update 하나에 메소드로 만들때 사용하는 방법
//	public int save(Member member) {
//		int result = 0;
//		Connection conn = getConnection();
//		
//		if(member.getNo() != 0) {
//			result = dao.updateMember(conn, member);
//		}else {
//			result = dao.insertMember(conn, member);
//		}
//		
//		if(result > 0) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		close(conn);
//		return result;
//	}
	
	// 회원 탈퇴
	public int delete(int no) {
		Connection conn = getConnection();
		int result = dao.updateStatus(conn, no, "N");
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 비밀번호 업데이트
	public int updatePassword(int no, String password) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn, no, password);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 중복 가입 확인
	public boolean isDuplicated(String id) {
		Connection conn = getConnection();
		Member member = dao.selectMemberById(conn, id);
		close(conn);
		
		if(member != null) {
			return true; // id 중복됨!!
		}else {
			return false; // id 중복안됨!! -> 회원가입 가능
		}
	}
	
}






