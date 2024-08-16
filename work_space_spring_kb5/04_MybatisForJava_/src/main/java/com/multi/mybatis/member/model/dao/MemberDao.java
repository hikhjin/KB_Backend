package com.multi.mybatis.member.model.dao;

import com.multi.mybatis.member.model.vo.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemberDao {
    public long getMemberCount(SqlSession session) {
        return session.selectOne("memberMapper.selectCount");
    }

    public List<Member> findAll(SqlSession session) {
        return session.selectList("memberMapper.selectAll");
    }

    public Member findMemberById(SqlSession session, String id) {
        return session.selectOne("memberMapper.selectMember", id);
    }

    public List<Member> findMemberByParam(SqlSession session, Member member) {
        return session.selectList("memberMapper.selectMemberByParam", member);
    }

    // id와 메소드명이 일치하는걸 권장!
    public int insertMember(SqlSession session, Member member) {
        return session.insert("memberMapper.insertMember", member);
    }

    public int updateMember(SqlSession session, Member member) {
        return session.update("memberMapper.updateMember", member);
    }

    public int deleteMember(SqlSession session, long no) {
        return session.delete("memberMapper.deleteMember", no);
    }

}
