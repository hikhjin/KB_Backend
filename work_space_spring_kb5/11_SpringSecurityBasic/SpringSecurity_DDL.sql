-- DROP SCHEMA security;
CREATE SCHEMA security;
USE security;


CREATE TABLE member (
    mno 	    BIGINT PRIMARY KEY AUTO_INCREMENT,
    id 		    VARCHAR(30) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    name 	    VARCHAR(15) NOT NULL, -- 사용자의 이름
    age 	    INT ,
    enabled     INT default 1, -- 활성화여부 1(활성화), 0(비활성화)
    enroll_date DATETIME  DEFAULT CURRENT_TIMESTAMP
);

insert into member values(default, 'test1', '1212', '홍길동', 21, 1, default);
insert into member values(default, 'test2', '1212', '박길동', 21, 1, default);
insert into member values(default, 'admin', '1212', '이길동', 21, 1, default);
insert into member values(default, 'admin2', '1212', '최길동', 21, 1, default);
commit;

update member set password = '$2a$10$/Sf746ViAD0YVHKtkRAY/OQWl.a1ApeBDWNtFMEBZugBn7Il6p31a' where id = 'test1';
update member set password = '$2a$10$/Sf746ViAD0YVHKtkRAY/OQWl.a1ApeBDWNtFMEBZugBn7Il6p31a' where id = 'test2';
update member set password = '$2a$10$/Sf746ViAD0YVHKtkRAY/OQWl.a1ApeBDWNtFMEBZugBn7Il6p31a' where id = 'admin';
update member set password = '$2a$10$/Sf746ViAD0YVHKtkRAY/OQWl.a1ApeBDWNtFMEBZugBn7Il6p31a' where id = 'admin2';

commit;


select * from member;

CREATE TABLE authority (
    id          VARCHAR(30) NOT NULL,    -- 회원아이디
    authority   VARCHAR(20) NOT NULL,     -- 권한
    primary key(id, authority),
    foreign key(id) references member(id)
);

insert into authority values('test1', ' ROLE_USER');
insert into authority values('test2', 'ROLE_USER');
insert into authority values('admin', 'ROLE_USER');
insert into authority values('admin', 'ROLE_ADMIN');
insert into authority values('admin2', 'ROLE_USER');
insert into authority values('admin2', 'ROLE_ADMIN');

commit;

select * from authority;

-- 회원정보 조회
select * from member where id = 'admin';

-- 조회
select * from authority where id = 'admin';


-- security의 remember-me 사용을 위한  persistent_logins 테이블생성
create table persistent_logins (
    username VARCHAR(100) NOT NULL,
    series  VARCHAR(100) PRIMARY KEY,
    token   VARCHAR(100) NOT NULL, -- username, password, expiry time에 대한  hashing값
    last_used DATETIME  NOT NULL
);

select * from persistent_logins;
    
