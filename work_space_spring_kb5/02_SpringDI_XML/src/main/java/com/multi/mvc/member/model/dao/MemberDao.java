package com.multi.mvc.member.model.dao;

import com.multi.mvc.member.model.dto.MemberDTO;
import java.util.List;

public interface MemberDao {
    int insertMember(MemberDTO memberDTO);
    List<MemberDTO> selectAll();
    MemberDTO selectById(String id);

}
