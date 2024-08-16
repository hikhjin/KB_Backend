package com.multi.mybatis.member.model.service;

import com.multi.mybatis.member.model.dao.MemberDao;
import com.multi.mybatis.member.model.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.multi.mybatis.common.SqlSessionTemplate.getSession;

public class MemberService {
	private MemberDao dao = new MemberDao();

	private static final Logger logger = LogManager.getLogger(MemberService.class);
	
	public long getMemberCount() {
		SqlSession session = getSession();
		long result = dao.getMemberCount(session);
		session.close();
		return result;
	}
	
	public List<Member> findAll() {
		SqlSession session = getSession();
		List<Member> result = dao.findAll(session);
		session.close();
		return result;
	}
	
	public Member findMemberById(String id){
		SqlSession session = getSession();
		Member result = dao.findMemberById(session, id);
 		session.close();
 		logger.info("[Login] id : " + id +", result : " + result);
		return result;
	}

	public List<Member> findMemberByMember(Member member){
		SqlSession session = getSession();
		List<Member> result = dao.findMemberByParam(session, member);
 		session.close();
        logger.info("[Login] member : {}, result : {}", member, result);
		return result;
	}
	
	public int insertMember(Member member) {
		SqlSession session = getSession();
		int result = dao.insertMember(session, member);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		logger.info("[Join] member : " + member +", result : " + result);
		return result;
	}
	
	public int updateMember(Member member) {
		SqlSession session = getSession();
		int result = dao.updateMember(session, member);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		logger.info("[Update] member : " + member +", result : " + result);
		return result;
	}
	
	public int deleteMember(long mno) {
		SqlSession session = getSession();
		int result = dao.deleteMember(session, mno);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		logger.info("[Delete] no : " + mno +", result : " + result);
		return result;
	}

}

















