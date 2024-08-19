package com.multi.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.multi.ajax.model.vo.Member;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/jsAjax.do", produces = "text/html; charset=utf8" )
	@ResponseBody // @ResponseBody : 리턴으로 처리한 문자열이 실제 응답 결과가 될때 사용하는 어노테이션
	public String javascriptAjax(String name, int age) {
		String str = "서버 응답 결과<br>name : " + name + ", age : " + age;
		return str;
	}
	
	@RequestMapping(value="/jqAjax1.do", produces = "text/html; charset=utf8" )
	@ResponseBody 
	public String jQueryAjax1(String inputValue) {
		String str = "서버 응답 결과<br>inputValue : " + inputValue;
		return str;
	}
	
	
	@RequestMapping(value="/jqAjax2.do", produces = "text/html; charset=utf8" )
	@ResponseBody 
	public String jQueryAjax2(String name, int age) {
		String str = "서버 응답 결과<br> name : " + name + ", age : " + age;
		return str;
	}
	
	
	@RequestMapping(value="/jqAjax3.do", produces = "text/html; charset=utf8" )
	@ResponseBody 
	public String jQueryAjax3() {
		// java Member -> json 만들어서 전송
		Member member = new Member(100, "홍길동", 25, '남');

		// json-simple 파서로 json만드는 방법
		//  -> 사용하지 말것!!! 추천 안함!!
		JSONObject obj = new JSONObject();
		obj.put("no", member.getNo());
		obj.put("name", member.getName());
		obj.put("age", member.getAge());
//		obj.put("gender", member.getGender()); // -> char는 숫자 취급 받아서 js에서 에러 발생!!
		obj.put("gender", "" + member.getGender()); // char->문자열로 바꾸는 코드!!

		System.out.println(obj.toJSONString());
		return obj.toJSONString(); // json 문자열이 전송됨
	}
	
	@RequestMapping(value="/jqAjax4.do", produces = "text/html; charset=utf8" )
	@ResponseBody 
	public String jQueryAjax4() {
		// JSONArray로 list 만드는 방법
		List<Member> list = new ArrayList<>();
		list.add(new Member(100, "홍길동", 25, '남'));
		list.add(new Member(101, "최길순", 31, '여'));
		list.add(new Member(102, "김길동", 41, '남'));
		list.add(new Member(103, "이길순", 21, '여'));
		list.add(new Member(104, "박길동", 20, '남'));
		
		JSONArray array = new JSONArray();
		for(Member member : list) {
			// JSON 객체 생성
			JSONObject obj = new JSONObject();
			obj.put("no", member.getNo());
			obj.put("name", member.getName());
			obj.put("age", member.getAge());
			obj.put("gender", "" + member.getGender());
			array.add(obj);
		}
		System.out.println(array.toJSONString());
//		return array.toJSONString();
		return array.toJSONString();
	}
	
	@RequestMapping(value="/jqAjax5.do", produces = "text/html; charset=utf8" )
	@ResponseBody 
	public String jQueryAjax5(int count) {
		List<Member> list = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			list.add(new Member(100 + i, "홍길동" + i, 25 + i, '남'));
		}
		
		// gson 사용법
		// - gson google에서 만든 library로 simple-json 업그레이드 버전
		// - spring 버전이 3.0 버전일때는 gson을 통해서 json 만드는게 가장 빠르고 편리하다.
		
		// 1. gson.toJson()으로 객체를 만드는 방법
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list); // java객체를 json 문자열로 변경하는 방법
		System.out.println(jsonStr);
		
		// 2. GsonBuilder - 스트림을 활용해서 전달 할때
		String jsonStr2 = new GsonBuilder().create().toJson(list);
		System.out.println(jsonStr2);
		
		// setPrettyPrinting() : 사람이 보기 편하게 바꿔주는 옵션
		String jsonStr3 = new GsonBuilder().setPrettyPrinting().create().toJson(list);
		System.out.println(jsonStr3);
		
		return jsonStr3;
	}
	
	// ResponseEntity : Spring에서 jackson을 통해 JSON 응답을 위한 가장 표준적인 방법
	@RequestMapping("jqAjax6.do")
	public ResponseEntity<Map<String, Object>> jQueryAjax6(int count, String name, int age){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("title", "회원목록 정보");

		List<Member> list = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			list.add(new Member(100 + i, name + i, age + i, '남'));
		}
		jsonMap.put("list", list);
		return new ResponseEntity<Map<String,Object>>(jsonMap, HttpStatus.OK);
	}
	
}













