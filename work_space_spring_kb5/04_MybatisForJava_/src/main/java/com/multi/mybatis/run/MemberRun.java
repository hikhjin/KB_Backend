package com.multi.mybatis.run;

import com.multi.mybatis.member.model.service.MemberService;
import com.multi.mybatis.member.model.vo.Member;

import java.util.List;
import java.util.Random;

public class MemberRun {

	public static void main(String[] args) {
		MemberService service = new MemberService();

		// 1. 가입된 member 갯수 구하기
		System.out.println("1. 가입된 멤버 수 : " + service.getMemberCount());
		System.out.println("---------------------------------------------\n");

		// 2. 가입된 모든 멤버 조회하기
		System.out.println("2. 가입된 멤버 조회");
		List<Member> list = service.findAll();
		for (Member m : list) {
			System.out.println(m);
		}
		System.out.println("---------------------------------------------\n");

		// 3. 가입된 한명의 멤버 조회하기
		System.out.println("3. 가입 된 한명의 멤버 조회하기 - admin");
		Member member = service.findMemberById("admin");
		System.out.println(member);
		System.out.println("---------------------------------------------\n");

		// 4. 가입된 한명의 멤버 조회하기
		System.out.println("4. 가입 된 여러 멤버 조회하기");
		Member param = new Member();
		param.setName("길동");
		param.setAge(30);
		List<Member> resultList = service.findMemberByMember(param);
		System.out.println(resultList);
		System.out.println("---------------------------------------------\n");

		// 5. 회원 가입하기
		System.out.println("5. 회원 가입하기");
		String id = member.getId() + new Random().nextInt(2000);
		member.setMno(0);
		member.setId(id);
		member.setName("김길동");
		int result = service.insertMember(member);
		System.out.println("결과 : " + result);
		member = service.findMemberById(id);
		System.out.println("조회 : " + member);
		System.out.println("---------------------------------------------\n");

		// 6. 가입 된 모든 멤버 조회하기
		System.out.println("6.  가입 된 모든 멤버 조회하기 ");
		list = service.findAll();
		for (Member m : list) {
			System.out.println(m);
		}
		System.out.println("----------------------------------------------\n");

		// 7. 회원 정보 수정하기
		System.out.println("7. 회원 정보 수정하기");
		member.setName("박길동");
		member.setAddress("경기도 안양시 동안구 평촌동");
		member.setPhone("010-4321-2222");

		result = service.updateMember(member);
		System.out.println("결과 : " + result);
		member = service.findMemberById(id);
		System.out.println("조회 : " + member);
		System.out.println("---------------------------------------------\n");

		// 8. 회원 탈퇴
		System.out.println("8. 회원 탈퇴");
		result = service.deleteMember(member.getMno());
		System.out.println("결과 : " + result);
		member = service.findMemberById(id);
		System.out.println("조회 : " + member);
		System.out.println("---------------------------------------------\n");
	}
}
