package com.multi.mvc.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.mvc.member.model.mapper.MemberMapper;
import com.multi.mvc.member.model.vo.Member;

@Service
public class MemberService {

	// 테스트 코드임을 알리는 부분 -> 원래는 공통부나 다른곳에서 만들어야한다.
	boolean isTest = true;
	
	@Autowired
	private MemberMapper mapper; // dao를 대신해서 mybatis와 연동하는 클래스
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder; //  SHA-256 hash code로 패스워드 일방향 암호 지원 모듈
	// 1234 -> nsikldvnisoldjhv2423jo23 (평문 -> hashCode)
	
	public Member login(String id, String pwd) {
		Member member = mapper.selectMemberById(id);
		if(member == null) {
			return null;
		}

		// 테스트 모드 일 때
		if(isTest == true) {
			if(member.getPassword().equals(pwd)) {
				return member;
			}
		}
		
		// 정상 로직일때
		// BCryptPasswordEncoder 사용법
		System.out.println(member.getPassword()); // 정상적인 회원가입 된 경우 DB에는 암호화(hash)된 PWD가 저장되어 있음
		System.out.println(pwEncoder.encode(pwd)); // 사용자의 입력값을 hash로 바꿔주는 코드
		System.out.println(pwEncoder.matches(pwd, member.getPassword())); // hash 변환 및 비교까지 해주는 코드
		
		// pw 확인하는 로직
		if(pwEncoder.matches(pwd, member.getPassword())) {
			// 로그인 성공한 경우
			return member;
		}else {
			// 실패
			return null;
		}
	}

	// 서비스의 save : mno(key)가 없으면 insert, key가 있으면 update가 되는 로직
	// @Transactional : 트랜잭션을 관리하는 어노테이션, commit, rollback을 자동으로 지원
	// rollbackFor : 어떤상황에서 rollback을 해야하는지 확인하는 옵션, 없으면 rollback이 없다.
	// https://data-make.tistory.com/738
	@Transactional(rollbackFor = Exception.class)
	public int save(Member member) {
		int result = 0;
		
		if(member.getMno() == 0) { // insert
			String encodePwd = pwEncoder.encode(member.getPassword()); // 패스워드 해쉬코드로 변환하는 과정(암호화)
			member.setPassword(encodePwd);
			result = mapper.insertMember(member);
		} else { // update
			result = mapper.updateMember(member);
		}
		return result;
	}

	public boolean validate(String id) {
		return mapper.selectMemberById(id) != null;
	}

	public Member findByNo(int mno) {
		return mapper.selectMember(mno);
	}
	
	public Member findById(String id) {
		return mapper.selectMemberById(id);
	}
	
	public List<Member> findAll() {
		return mapper.selectAll();
	}

	@Transactional(rollbackFor = Exception.class)
	public int updatePwd(int mno, String password) {
		Map<String, String> map = new HashMap<>();
		//map : password, mno 필수
		map.put("mno", "" + mno);
		map.put("password", pwEncoder.encode(password)); // 암호화해서 저장하는 로직
		return mapper.updatePwd(map);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int delete(int mno) {
		return mapper.deleteMember(mno);
	}
	
}



