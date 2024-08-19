package com.multi.mybatis.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.mybatis.member.model.vo.Member;

@Repository("adminMemberDao") // 관리자의 dao
public class MemberAdminDaoImpl implements MemberDao{

	public int insertMember(SqlSessionTemplate session, Member member) {
		// 관리자임으로 무언가 특별한 처리가 필요할때 DAO를 분리한다.
		return session.insert("memberMapper.insertMember");
	}

	public List<Member> selectMemberAll(SqlSessionTemplate session) {
		return session.selectList("memberMapper.selectMemberAll");
	}

	public Member selectMemberById(SqlSessionTemplate session, String id) {
		return session.selectOne("memberMapper.selectMemberById", id);
	}

	public int deleteMember(SqlSessionTemplate session, String id) {
		return session.delete("memberMapper.deleteMember", id);
	}
}
