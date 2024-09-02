drop schema mvc;
create schema mvc;
use mvc;

CREATE TABLE board
(
    bno         BIGINT AUTO_INCREMENT,
    title       VARCHAR(1000)  NOT NULL,
    content     VARCHAR(2000) NOT NULL,
    writer      VARCHAR(30) NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    modify_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_board_no PRIMARY KEY (bno)
);

INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[공지] 클린한 게시판 환경을 만들어주세요.', '깨끗한 게시판 환경 유지에 협조 바랍니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[일반] 안녕하세요? 처음 가입한 개발자입니다.', '잘 부탁드립니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[질문] 질문 있습니다.', '자바 어렵나요?');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[팁] 팁글입니다.', '개발일은 어렵습니다. 코딩만 안합니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[팁] 팁글입니다.', '문서작업은 귀찮습니다. 이게 개발일의 실체입니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[판매] 삼성 노트북 팔아요.', '삼성 노트북 팝니다. 터치 됩니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[판매] 아이폰 팔아요.', '아이폰15 팝니다.');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[판매] 애플 맥북 노트북 팝니다.', 'M2 모델입니다. 맥북 게임용으로 잘써요');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[구매] 애플 노트북 삽니다.', '맥북 게임용으로 삽니다. 이거 게임 잘돌아가나요?');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[구매] 삼성 노트북 삽니다', '게임용으로 삽니다. ');
INSERT INTO board (bno, writer, title, content)
VALUES (0, 'user', '[구매] 애플 아이폰 삽니다', '아이폰14사요. 30만원에 네고합니다. ');

commit;

SELECT * FROM board;

