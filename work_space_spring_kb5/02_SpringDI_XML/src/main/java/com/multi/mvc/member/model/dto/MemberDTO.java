package com.multi.mvc.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address; // Web에서는 인자로 받지 않을 객체
    private List<String> devLang;// 개발 가능한 언어, params(배열)로 처리
    // private String[] debLang; // 고전 spring 권장 타입





}
