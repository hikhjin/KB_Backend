package com.multi.mvc.member.model.service;

import com.multi.mvc.member.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    // 회원가입
    int memberEnroll(MemberDTO memberDTO);

    // 전체 멤버 가져오기
    List<MemberDTO> getAllList();

    // 로그인 기능
    MemberDTO login(String id);
}