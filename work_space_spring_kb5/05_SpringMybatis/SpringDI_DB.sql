DROP SCHEMA spring1;
CREATE SCHEMA spring1;
USE spring1;

-- ----------------------------------------------
-- ------------- MEMBER 관련 테이블 --------
-- ----------------------------------------------

CREATE TABLE member (
    id 		VARCHAR(30) PRIMARY KEY,
    password 	VARCHAR(100) NOT NULL,
    name VARCHAR(15) NOT NULL,
    age INT,
    gender  VARCHAR(10),
    phone VARCHAR(13),
    address  VARCHAR(100),
    email VARCHAR(100),
    hobby VARCHAR(300),
    enroll_date DATETIME DEFAULT CURRENT_TIMESTAMP
  );

INSERT INTO member VALUES('admin', '1234', '홍길동', 28, '남', '010-1234-4341',  '서울시 강남구 역삼동', 'test@email.or.kr',  '축구,야구,영화보기', default);
INSERT INTO member VALUES('test1', '1234', '김길동', 25, '여', '010-1234-4341',  '서울시 강남구 도곡동', 'test@email.or.kr',  '축구,야구,영화보기', default);
INSERT INTO member VALUES('test2', '1234', '이길동', 27, '남', '010-4321-4341',  '서울시 강남구 삼성동', 'test@email.or.kr',  '축구,야구,영화보기', default);

COMMIT;

SELECT * FROM member;
