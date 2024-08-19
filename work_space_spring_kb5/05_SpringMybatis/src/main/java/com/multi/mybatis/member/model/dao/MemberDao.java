package com.multi.mybatis.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.multi.mybatis.member.model.vo.Member;

public interface MemberDao {
	int insertMember(SqlSessionTemplate session, Member member);
	List<Member> selectMemberAll(SqlSessionTemplate session);
	Member selectMemberById(SqlSessionTemplate session, String id);
	int deleteMember(SqlSessionTemplate session, String id);
}
