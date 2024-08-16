DROP SCHEMA mybatis;
CREATE SCHEMA mybatis;
USE mybatis;

-- ------------------------------------------------
-- ------------ MEMBER 관련 테이블 ------------------
-- ------------------------------------------------

CREATE TABLE mybatis.member (
    mno         BIGINT PRIMARY KEY AUTO_INCREMENT,
    id          VARCHAR(30) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    role        VARCHAR(10) DEFAULT 'ROLE_USER',
    name        VARCHAR(15) NOT NULL,
    age         INT,
    phone       VARCHAR(13),
    email       VARCHAR(100),
    address     VARCHAR(100),
    hobby       VARCHAR(100),
    status      VARCHAR(1) DEFAULT 'Y' CHECK(status IN('Y', 'N')),
    enroll_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    modify_date DATETIME DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO MEMBER (
    mno, id, password, role, name,
    age, phone, email, address, hobby,
    status, enroll_date, modify_date
) VALUES(
    0, 'admin', '1234', 'ROLE_ADMIN', '관리자',
    31, '010-1234-4341', 'admin@test.com', '서울시 강남구 역삼동', DEFAULT,
    DEFAULT, DEFAULT, DEFAULT
);

INSERT INTO MEMBER (
    mno, id, password, role, name,
    age, phone, email, address, hobby,
    status, enroll_date, modify_date
) VALUES(
    0, 'test', '1234', 'ROLE_USER', '홍길동',
    20, '010-4221-4341', 'admin@test.com', '서울시 강남구 역삼동', DEFAULT,
    DEFAULT, DEFAULT, DEFAULT
);

INSERT INTO MEMBER (
    mno, id, password, role, name,
    age, phone, email, address, hobby,
    status, enroll_date, modify_date
) VALUES(
    0, 'test2', '1234', 'ROLE_USER', '최길동',
    27, '010-4221-4341', 'admin@test.com', '서울시 강남구 역삼동', DEFAULT,
    DEFAULT, DEFAULT, DEFAULT
);

COMMIT;

SELECT * FROM MYBATIS.MEMBER;

-- ------------------------------------------------
-- ------------- Board 관련 테이블 ------------------
-- ------------------------------------------------


CREATE TABLE mybatis.board (
    bno             BIGINT AUTO_INCREMENT,
    mno             BIGINT,
	title           VARCHAR(200),
	content         VARCHAR(3000),
	type            VARCHAR(100),
	original_filename VARCHAR(100),
	renamed_filename VARCHAR(100),
	readcount       INT DEFAULT 0,
    status          VARCHAR(1) DEFAULT 'Y' CHECK (status IN('Y', 'N')),
    create_date     DATETIME  DEFAULT CURRENT_TIMESTAMP,
    modify_date     DATETIME  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_BOARD_NO PRIMARY KEY(bno),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY(mno) REFERENCES MEMBER(mno) ON DELETE SET NULL
);


INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);

INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 애플 노트북 팔아요.',  '노트북 맥북 최신입니다. 게임하시면 안됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 삼성 노트북 팔아요.',  '삼성 노트북 팝니다. 터치 됩니다.', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 아이폰 팔아요.',  '아이폰13 팝니다. ', 'B1', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[판매] 갤럭시 팔아요.',  '갤럭시 플립3 팝니다. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 애플 노트북 삽니다.',  '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 삼성 노트북 삽니다.',  '삼성 노트북 삽니다. 아이폰 개발하려 삽니다.', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 아이폰 삽니다.',  '아이폰3사요. 30만원에 네고합니다. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);
INSERT INTO board VALUES(0, 1, '[구매] 갤럭시 삽니다.',  '갤럭시 삽니다. 아무 기종이나 상관없어요. ', 'B2', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', DEFAULT, DEFAULT);



COMMIT;
SELECT * FROM board;

-- ----------------------------------------------------------------
-- ----------------------- REPLY 관련 테이블 -------------------------
-- ----------------------------------------------------------------

CREATE TABLE mybatis.REPLY(
  rno BIGINT PRIMARY KEY AUTO_INCREMENT,
  bno BIGINT,
  mno BIGINT,
  content VARCHAR(1000),
  status VARCHAR(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  modify_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (bno) REFERENCES BOARD(bno),
  FOREIGN KEY (mno) REFERENCES MEMBER(mno)
);


INSERT INTO reply VALUES(0, 1, 1, '안녕하세요.', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO reply VALUES(0, 1, 1, '반갑습니다.', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO reply VALUES(0, 2, 1, '안녕하세요.', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO reply VALUES(0, 2, 1, '반갑습니다.', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO reply VALUES(0, 3, 1, '안녕하세요.', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO reply VALUES(0, 3, 1, '반갑습니다.', DEFAULT, DEFAULT, DEFAULT);


COMMIT;

SELECT * FROM REPLY;

-- --------------------------------- DDL 끝-------------------------------------------

-- MEMBER 

-- 멤버 조회
-- SELECT * FROM member WHERE mno=? AND STATUS='Y'

-- 멤버 추가
-- INSERT INTO member VALUES(0,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)

-- 멤버 수정               
-- UPDATE MEMBER SET name=?,phone=?,email=?,address=?,hobby=?, modify_date=CURRENT_TIMESTAMP WHERE mno=?

-- 멤버 패스워드 변경
-- UPDATE MEMBER SET password=? WHERE mno=?

-- 멤버 삭제
-- UPDATE MEMBER SET status=? WHERE mno=?


-- BOARD DML

-- 총 게시글 갯수
-- SELECT COUNT(*) FROM board WHERE status='Y'

-- 조회수 증가
-- UPDATE board SET readcount=? WHERE bno=?

-- 게시글 작성
-- INSERT INTO board VALUES(0,?,?,?,?,?,default,default,default,default)

-- 게시글 수정
-- UPDATE board SET title=?,content=?,original_filename=?,renamed_filename=?,modify_date=current_timestamp, where bno=?

-- 게시글 삭제
-- UPDATE board SET status=? WHERE bno=?

-- 상세 조회
/*
SELECT b.bno, b.title, m.id, b.readcount, b.original_filename, b.renamed_filename, b.content, b.create_date, b.modify_date
FROM board b
JOIN member M ON(b.mno = m.mno)
WHERE b.status = 'Y' AND b.bno = 1
*/


-- 목록 조회(페이징) 쿼리
/*
SELECT  B.NO, B.TITLE, M.ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS
FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) 
WHERE B.STATUS = 'Y' 
ORDER BY B.NO DESC LIMIT 10 OFFSET 0;
*/
  


-- insert query
-- INSERT INTO REPLY VALUES(0, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)


-- 한 게시판에 해당하는 댓글 리스트 조회용 쿼리문
/*
SELECT R.NO, R.BOARD_NO, R.CONTENT, M.ID, R.CREATE_DATE, R.MODIFY_DATE
FROM REPLY R
JOIN MEMBER M ON(R.WRITER_NO = M.NO)
WHERE R.STATUS='Y' AND BOARD_NO= ? 
ORDER BY R.NO DESC;
*/


/*
select * FROM REPLY;
DELETE FROM REPLY WHERE NO=5
*/


-- 검색 + 쿼리문
/*
SELECT  B.NO, B.TITLE, M.ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS
FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) 
WHERE 1 = 1 
AND B.STATUS = 'Y'
AND M.ID LIKE '%admin%' 
AND B.TITLE LIKE '%구매%' 
AND B.CONTENT LIKE '%아이폰%' 
ORDER BY B.NO DESC LIMIT ? OFFSET ?;
*/

/*
SELECT  COUNT(*)
FROM BOARD B
JOIN MEMBER M ON(B.WRITER_NO = M.NO)
WHERE 1=1
    AND B.STATUS = 'Y'
--	AND M.ID LIKE '%admin%' 
--	AND B.TITLE LIKE '%구매%' 
	AND B.CONTENT LIKE '%아이폰%' 
*/