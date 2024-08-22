package com.multi.websocket.member.model.service;

import org.springframework.stereotype.Service;

import com.multi.websocket.member.model.vo.Member;

@Service("memberService")
public class MemberService {

	public Member selectOneMember(Member m) throws IllegalArgumentException {
		System.out.println("입력 아이디 : " + m.getMemberId());
		return m;
	}

}
