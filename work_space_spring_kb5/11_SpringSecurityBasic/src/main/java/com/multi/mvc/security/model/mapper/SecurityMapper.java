package com.multi.mvc.security.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface SecurityMapper {
    UserDetails loadUserByUsername(String id);
}
