package com.multi.mybatis.member.model.vo;

import lombok.*;

import java.time.LocalDateTime;


@Data // get/set/toString/hash/equals
@NoArgsConstructor  // 인자 없는 생성자
@AllArgsConstructor // 전체 인자가 있는 생성자
public class Member {
    private long mno; // BIGINT PRIMARY KEY AUTO_INCREMENT,
    private String id; // VARCHAR(30) NOT NULL UNIQUE,
    private String password; // VARCHAR(100) NOT NULL,
    private String role; // VARCHAR(10) DEFAULT 'ROLE_USER',
    private String name; // VARCHAR(15) NOT NULL,
    private int age;  // INT,
    private String phone; // VARCHAR(13),
    private String email; // VARCHAR(100),
    private String address; // VARCHAR(100),
    private String hobby; // VARCHAR(100),
    private String status; // VARCHAR(1) DEFAULT 'Y' CHECK(status IN('Y', 'N')),
    private LocalDateTime enrollDate; // DATETIME DEFAULT CURRENT_TIMESTAMP,
    private LocalDateTime modifyDate; // DATETIME DEFAULT CURRENT_TIMESTAMP
}
