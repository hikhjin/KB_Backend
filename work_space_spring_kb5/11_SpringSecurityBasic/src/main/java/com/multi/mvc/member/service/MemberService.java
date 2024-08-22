package com.multi.mvc.member.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    // 메소드 접근제한 거는 방법
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String allAllowService(){
        return "모든 사용자가 접근할수 있는 서비스";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String userNotAllowService(){
        return "사용자는 접근할수 없는 서비스";
    }
}
