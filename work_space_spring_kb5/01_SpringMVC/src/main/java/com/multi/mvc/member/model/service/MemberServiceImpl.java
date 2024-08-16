package com.multi.mvc.member.model.service;

import com.multi.mvc.member.model.dao.MemberDao;
import com.multi.mvc.member.model.dto.MemberDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service : 서비스임을 알리는 어노테이션 Bean 자동으로 생성되는 객체, new 안쓸예정!
@Service
public class MemberServiceImpl implements MemberService {

    // 기존 서블릿에서 사용하던 방법, -> 매우 강한 결합, Spring에서는 지양
//    private MemberDao dao = new MemberDaoImpl();

    //  @Autowired : 사용자가 별도로 객체를 만들지 않고 BeanFactory로 부터 객체의 관리를 위임하는 어노테이션
    // type은 MemberDao가 되고, 실제 생성되는 객체 MemberDaoImpl가 된다.
    //    -> MemberDaoImpl에 @Repository 어노테이션으로 인해 자동으로 맵핑된다.
    // Spring 5.3버전부터 ※Field injection is not recommended 라고함
//    @Autowired

    // @Setter : Lombok의 Setter로 Bean을 주입하는 방법, 스프링 5.3부터 권장됨
    @Setter(onMethod_ = @Autowired)
    private MemberDao dao;

    @Override
    public int memberEnroll(MemberDTO memberDTO) {
        return dao.insertMember(memberDTO);
    }

    @Override
    public List<MemberDTO> getAllList() {
        return dao.selectAll();
    }

    @Override
    public MemberDTO login(String id) {
        return dao.selectById(id);
    }
}
