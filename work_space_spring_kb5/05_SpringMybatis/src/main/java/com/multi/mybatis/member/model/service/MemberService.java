package com.multi.mybatis.member.model.service;

import java.util.List;

import com.multi.mybatis.member.model.vo.Member;

public interface MemberService {
	int joinMember(Member member);
	Member searchMember(String id);
	List<Member> getMemberList();
	int deleteMember(String id);
}
