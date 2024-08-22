package com.multi.mvc.security.model.service;

import com.multi.mvc.security.model.mapper.SecurityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityService")
@Slf4j
public class SecurityService implements UserDetailsService {

	@Autowired
	private SecurityMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		UserDetails member = mapper.loadUserByUsername(id);
		log.debug("member = {}", member);
		if(member == null)
			throw new UsernameNotFoundException(id);
		return member;
	}

}
