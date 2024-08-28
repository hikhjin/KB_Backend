DROP SCHEMA SPRING;
CREATE SCHEMA SPRING;
USE SPRING;

-- ----------------------------------------------
--------------- MEMBER 관련 테이블 ----------------
------------------------------------------------

CREATE TABLE member (
    mno 	 INT  PRIMARY KEY AUTO_INCREMENT,
    id 		 VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role 	 VARCHAR(10) DEFAULT 'ROLE_USER',
    name 	 VARCHAR(15) NOT NULL,
    phone 	 VARCHAR(13),
    email 	 VARCHAR(100),
    address  VARCHAR(100),
    hobby 	 VARCHAR(100),
    status 	 VARCHAR(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')),
    enroll_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    modify_date DATETIME DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO member (
    mno, id, password,  role, name,
    phone, email, address, hobby, status,
    enroll_date, modify_date
) VALUES(
    0, 'admin', '1234', 'ROLE_ADMIN', '관리자', 
    '010-1234-4341', 'admin@test.com', '서울시 강남구 역삼동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);


INSERT INTO member (
    mno, id, password,  role, name,
    phone, email, address, hobby, status,
    enroll_date, modify_date
) VALUES(
    0, 'test5', '1234', DEFAULT, '예비개발자', 
    '010-4321-1234', 'test@test.com', '서울시 강남구 역삼동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);


INSERT INTO member (
    mno, id, password,  role, name,
    phone, email, address, hobby, status,
    enroll_date, modify_date
) VALUES(
    0, 'test6', '1234', DEFAULT, '지나가는유저', 
    '010-1111-2222', 'test2@test.com', '서울시 강남구 삼성동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);

COMMIT;

SELECT * FROM MEMBER;


---------------------------------------------------
--------------- Board 카테고리 테이블 ------------------
---------------------------------------------------

CREATE TABLE board_category (
    type VARCHAR(20),
    name VARCHAR(100),
    level INT,
    orderno INT,
    CONSTRAINT PK_BOARD_CATEGORY PRIMARY KEY(type)
);

-- 일반적으로 CODE는 약어를 사용하나 가독성을 위해 길게 표현함 NOTICE -> N,  CMM1 -> C1 
-- NOTICE, PLAIN, CMM1, CMM2, CMM3, QUESTION, TIP, BUY, SELL
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('NOTICE', '공지', 0, 1);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('PLAIN', '일반글', 3, 2);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('QUESTION', '질문', 3, 3);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('TIP', 'TIP', 3, 4);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('BUY', '삽니다', 3, 5);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('SELL', '팝니다', 3, 6);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM1', '커뮤니티A', 3, 7);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM2', '커뮤니티B', 3, 8);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('CMM3', '커뮤니티C', 3, 9);
INSERT INTO BOARD_CATEGORY (TYPE, NAME, LEVEL, ORDERNO) VALUES('NBOARD', '공지사항', 3, 10);


COMMIT;
SELECT * FROM board_category ORDER BY ORDERNO;
-------------------------------------------------
--------------- Board 관련 테이블 ------------------
-------------------------------------------------


CREATE TABLE BOARD (	
    bno INT AUTO_INCREMENT,
    mno INT,
    type VARCHAR(20) DEFAULT 'PLAIN',
	title VARCHAR(1000),
	content VARCHAR(2000),
    attachcount INT DEFAULT 0,
	readcount INT DEFAULT 0,
    status VARCHAR(1) DEFAULT 'Y' CHECK (status IN('Y', 'N')),
    create_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    modify_date DATETIME  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_BOARD_NO PRIMARY KEY(bno),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY(mno) REFERENCES MEMBER(mno) ON DELETE SET NULL,
    CONSTRAINT FK_BOARD_CATEGORY FOREIGN KEY(type) REFERENCES BOARD_CATEGORY(type) ON DELETE SET NULL
);

INSERT INTO BOARD (bno, mno, type, title, content) VALUES(0,1,'NOTICE','[공지] 클린한 게시판 환경을 만들어주세요.','깨끗한 게시판 환경 유지에 협조 바랍니다.');
INSERT INTO BOARD (bno, mno, type, title, content) VALUES(0,2,'PLAIN','안녕하세요? 처음 가입한 개발자입니다.','잘 부탁드립니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'QUESTION','[질문] 질문 있습니다.','자바 어렵나요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'PLAIN','오늘 식사메뉴 추천드립니다.','돈까스 드세요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'PLAIN','내일 식사메뉴 추천드립니다.','냉면 어떠신가요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'PLAIN','모레 식사메뉴 추천드립니다.','스파게티 좋네요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'PLAIN','다음주 식사메뉴 추천드립니다.','아무거나 드세요');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.1','공지 내용입니다.1');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.2','공지 내용입니다.2');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.3','공지 내용입니다.3');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.4','공지 내용입니다.4');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.5','공지 내용입니다.5');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.6','공지 내용입니다.6');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.7','공지 내용입니다.7');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.8','공지 내용입니다.8');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.9','공지 내용입니다.9');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.10','공지 내용입니다.10');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.11','공지 내용입니다.11');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.12','공지 내용입니다.12');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.13','공지 내용입니다.13');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.14','공지 내용입니다.14');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,1,'NBOARD','공지사항 게시글 입니다.15','공지 내용입니다.15');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 1','커뮤니티 A활동 글입니다. 1');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 2','커뮤니티 A활동 글입니다. 2');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 3','커뮤니티 A활동 글입니다. 3');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM1','커뮤니티 A글입니다. 4','커뮤니티 A활동 글입니다. 4');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM1','커뮤니티 A글입니다. 5','커뮤니티 A활동 글입니다. 5');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 1','커뮤니티 B활동 글입니다. 1');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 2','커뮤니티 B활동 글입니다. 2');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 3','커뮤니티 B활동 글입니다. 3');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 4','커뮤니티 B활동 글입니다. 4');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'CMM2','커뮤니티 B글입니다. 5','커뮤니티 B활동 글입니다. 5');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 1','커뮤니티 C활동 글입니다. 1');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 2','커뮤니티 C활동 글입니다. 2');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 3','커뮤니티 C활동 글입니다. 3');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 4','커뮤니티 C활동 글입니다. 4');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'CMM3','커뮤니티 C글입니다. 5','커뮤니티 C활동 글입니다. 5');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','java는 쉽습니다. 객체만 아세요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','c언어는 어렵습니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'TIP','[팁] 팁글입니다.','프로그래밍은 생각보다 쉽습니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'TIP','[팁] 팁글입니다.','개발일은 어렵습니다. 코딩만 안합니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'TIP','[팁] 팁글입니다.','문서작업은 귀찮습니다. 이게 개발일의 실체입니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 노트북 팔아요.','삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'SELL','[판매] 아이폰 팔아요.','아이폰15 팝니다.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 맥북 노트북 팝니다.','M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 노트북 삽니다.','맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'BUY','[구매] 삼성 노트북 삽니다','게임용으로 삽니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 애플 아이폰 삽니다','아이폰14사요. 30만원에 네고합니다. ');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,2,'BUY','[구매] 삼성 갤럭시 삽니다.','갤럭시 삽니다. 아무 기종이나 상관없어요.');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 애플 노트북 팔아요.','노트북 맥북 최신입니다. 게임하시면 안됩니다');
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0,3,'SELL','[판매] 삼성 신형 갤럭시북 노트북 팔아요','새로나온 삼성 갤럭시북입니다. 이거 가성비 괜찮습니다.');


COMMIT;
SELECT * FROM BOARD;


------------------------------------------------------------------
--------------------------- 첨부파일 관련 ----------------------------
------------------------------------------------------------------

CREATE TABLE BOARD_ATTACH_FILE (
	fno INT AUTO_INCREMENT,
    bno INT,
    original_filename VARCHAR(200),
	renamed_filename VARCHAR(200),
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_BOARD_ATTACH_FILE PRIMARY KEY(fno),
    CONSTRAINT FK_BOARD_BO FOREIGN KEY(bno) REFERENCES BOARD(bno) ON DELETE CASCADE
);

INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 1, '첨부파일2','TEST_FILE2', DEFAULT);
UPDATE BOARD SET ATTACHCOUNT =(SELECT COUNT(*) FROM BOARD_ATTACH_FILE WHERE BNO=1) WHERE BNO=1;
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);
UPDATE BOARD SET ATTACHCOUNT =(SELECT COUNT(*) FROM BOARD_ATTACH_FILE WHERE BNO=2) WHERE BNO=2;
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) VALUES(0, 3, '첨부파일','TEST_FILE', DEFAULT);
UPDATE BOARD SET ATTACHCOUNT =(SELECT COUNT(*) FROM BOARD_ATTACH_FILE WHERE BNO=3) WHERE BNO=3;

COMMIT;
SELECT * FROM BOARD_ATTACH_FILE;
SELECT * FROM BOARD;

------------------------------------------------------------------
------------------------- REPLY 관련 테이블 -------------------------
------------------------------------------------------------------

CREATE TABLE REPLY(
  rno INT PRIMARY KEY AUTO_INCREMENT,
  bno INT,
  mno INT,
  content VARCHAR(1000),
  status VARCHAR(1) DEFAULT 'Y' CHECK (status IN ('Y', 'N')),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  modify_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (bno) REFERENCES BOARD(bno) ON DELETE CASCADE,
  FOREIGN KEY (mno) REFERENCES MEMBER(mno) ON DELETE SET NULL
);


INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 1, 2, '안녕하세요.');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 1, 3, '댓글 테스트 입니다. 1');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 1, 3, '댓글 테스트 입니다. 2');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 1, 3, '댓글 테스트 입니다. 3');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 1, 3, '댓글 테스트 입니다. 4');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 2, 2, '안녕하세요.');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 2, 3, '반갑습니다.');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 3, 3, '안녕하세요.');
INSERT INTO REPLY (rNO, bNO, mNO, CONTENT) VALUES(0, 3, 2, '반갑습니다.');


COMMIT;

SELECT * FROM REPLY;

----------------------------------- DDL 끝-------------------------------------------

----------------------------------- DML 시작-------------------------------------------

-- MEMBER 전체

SELECT * FROM MEMBER;

-- 멤버 조회
SELECT * FROM MEMBER WHERE MNO = 1 AND STATUS='Y';

-- 멤버 조회
SELECT * FROM MEMBER WHERE ID='TEST1' AND STATUS='Y';

-- 멤버 추가
INSERT INTO MEMBER (
    mno, id, password,  role, name,
    phone, email, address, hobby, status,
    enroll_date, modify_date
) VALUES(
    0, 'test3', '1234', DEFAULT, '테스터', 
    '010-1234-4341', 'test@test.com', '서울시 강남구 역삼동', DEFAULT, DEFAULT,
    DEFAULT, DEFAULT
);


-- 멤버 수정               
UPDATE MEMBER SET 
	NAME = '지나가는유저', PHONE = '010-2121-2222', EMAIL = 'test22@email.com', 
    ADDRESS = '서울시 강남구 테스트', HOBBY = '게임', MODIFY_DATE=CURRENT_TIMESTAMP
WHERE mNO=3;

-- 멤버 패스워드 변경
UPDATE MEMBER SET PASSWORD='4321' WHERE MNO = 3;

-- 멤버 탈퇴 -> 삭제아님
UPDATE MEMBER SET STATUS='N' WHERE MNO=3;


-- BOARD DML

-- 카테고리 가져오기
SELECT * FROM BOARD_CATEGORY ORDER BY ORDERNO;

-- 전체 조회
SELECT * FROM BOARD;

-- 총 게시글 갯수
SELECT COUNT(*) FROM BOARD WHERE STATUS='Y';

-- 조회수 증가
UPDATE BOARD SET READCOUNT=1 WHERE BNO=1;

-- 게시글 작성
INSERT INTO BOARD (bNO, mNO, TYPE, TITLE, CONTENT) VALUES(0, 1,DEFAULT,'테스트 글 입니다.','테스트 입니다.');

-- 게시글 수정
UPDATE BOARD SET 
	TITLE='글수정 테스트', TYPE= 'PLAIN', CONTENT='테스트입니다.', MODIFY_DATE = CURRENT_TIMESTAMP
WHERE bNO=2;

-- 게시글 삭제
UPDATE BOARD SET STATUS='N' WHERE bNO=3;

-- 파일 첨부
INSERT INTO BOARD_ATTACH_FILE (fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE) 
VALUES(0, 2, '첨부파일','TEST_FILE', DEFAULT);

-- 파일 첨부된 숫자 게시판 반영
UPDATE BOARD SET ATTACHCOUNT =(SELECT COUNT(*) FROM BOARD_ATTACH_FILE WHERE BNO=2) WHERE BNO=2;

-- 파일 조회
SELECT fNO, bNO, ORIGINAL_FILENAME, RENAMED_FILENAME, CREATE_DATE FROM BOARD_ATTACH_FILE
WHERE BNO = 2;

-- 파일 삭제
DELETE FROM BOARD_ATTACH_FILE WHERE FNO = 4;

-- 글 상세 조회
SELECT  B.BNO, B.TYPE, M.ID, B.TITLE, B.CONTENT, B.READCOUNT, B.ATTACHCOUNT, B.CREATE_DATE, B.MODIFY_DATE
FROM BOARD B
JOIN MEMBER M ON (B.MNO = M.MNO)
WHERE B.STATUS = 'Y' AND B.BNO = 1;

-- 목록 조회(페이징) 쿼리 + 게시글 레벨 반영
SELECT  B.BNO, B.TYPE, B.TITLE, M.ID, B.READCOUNT, B.ATTACHCOUNT, B.CREATE_DATE
FROM BOARD B
JOIN MEMBER M ON (B.MNO = M.MNO)
JOIN BOARD_CATEGORY C ON (B.TYPE = C.TYPE)
WHERE B.STATUS = 'Y'
ORDER BY C.LEVEL, B.BNO DESC LIMIT 12 OFFSET 0;


-- Reply

-- 리플 등록
INSERT INTO REPLY (rNO, bNO, mNO, content) VALUES(0, 1, 2, '안녕하세요.');


-- 한 게시판에 해당하는 댓글 리스트 조회용 쿼리문
SELECT R.RNO, R.BNO, R.CONTENT, M.ID, R.CREATE_DATE, R.MODIFY_DATE
FROM REPLY R
JOIN MEMBER M ON(R.MNO = M.MNO)
WHERE R.STATUS='Y' AND BNO = 1 
ORDER BY R.RNO DESC;


select * FROM REPLY; 

DELETE FROM REPLY WHERE RNO=4;


SELECT  
	mNO, ID, PASSWORD, ROLE, NAME,  
	PHONE, EMAIL, ADDRESS, HOBBY, STATUS, 
	ENROLL_DATE, MODIFY_DATE
FROM MEMBER;


-- 검색용 쿼리 2 종 세트
-- 게시판 리스트 용 쿼리 완성

SELECT  B.BNO, B.TYPE, B.TITLE, M.ID, B.READCOUNT, B.ATTACHCOUNT, B.CREATE_DATE
FROM BOARD B
JOIN MEMBER M ON (B.MNO = M.MNO)
JOIN BOARD_CATEGORY C ON (B.TYPE = C.TYPE)
WHERE 
	B.STATUS = 'Y'
    AND B.TYPE IN ('NOTICE', 'PLAIN', 'CMM1', 'CMM2', 'CMM3', 'QUESTION', 'TIP', 'BUY', 'SELL')
    AND M.ID LIKE '%test%' 
    AND B.TITLE LIKE '%구매%' 
    AND B.CONTENT LIKE '%아이폰%' 
ORDER BY C.LEVEL, B.BNO DESC LIMIT 12 OFFSET 0;


SELECT COUNT(*)
FROM BOARD B
JOIN MEMBER M ON (B.MNO = M.MNO)
JOIN BOARD_CATEGORY C ON (B.TYPE = C.TYPE)
WHERE 
	B.STATUS = 'Y'
    AND B.TYPE IN ('NOTICE', 'PLAIN', 'CMM1', 'CMM2', 'CMM3', 'QUESTION', 'TIP', 'BUY', 'SELL')
	AND M.ID LIKE '%test%' 
	AND B.TITLE LIKE '%구매%'
	AND B.CONTENT LIKE '%아이폰%';
    
    
    