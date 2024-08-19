package com.multi.mybatis.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.mybatis.member.model.vo.Member;

@Repository("memberDao") // 일반 member의 dao
public class MemberDaoImpl implements MemberDao{
	public int insertMember(SqlSessionTemplate session, Member member) {
		return session.insert("memberMapper.insertMember", member);
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
