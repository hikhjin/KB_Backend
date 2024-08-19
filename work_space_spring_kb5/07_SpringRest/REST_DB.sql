-- DROP SCHEMA rest;
CREATE SCHEMA rest;
USE rest;


CREATE TABLE cat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    info VARCHAR(3000),
    eyecolor VARCHAR(300),
    hairlength VARCHAR(100),
    features VARCHAR(100),
    color VARCHAR(100),
    weight VARCHAR(100),
    createdate DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO CAT (id, name, info, eyecolor, hairlength, features, color, weight,createdate)
VALUES(0,'브리티시 숏헤어', '체형은 세미코비타입에 가깝고, 크고 둥그런 머리를 가지고 있으며, 귀는 머리에 비해 크지 않고, 눈은 크고 둥글며 볼살이 있어서 심술맞아 보이는 인상을 가지고 있지만, 이상한 나라의 앨리스에 나오는 체셔 고양이처럼 부드러운 미소를 가지고 있다. 몸은 단단하며 뼈가 굵다. 짧고 굵은 목을 가지고 있다. 다리도 역시 굵고 짧은 편이며, 꼬리는 끝이 둥글고 몸체의 3분의 2정도의 길이이다. 털은 짧고 조밀하게 나 있고 뻣뻣한 편이다. 한편 색은 아주 다양한데, 가장 많은 색은 블루이다. 눈색은 털색과 관련이 있다. ', '호박색,구리색,그린,골드', '장모', '직모', '흰색, 블루,흑색, 레드, 크림, 실버', '5.4', DEFAULT);

INSERT INTO CAT (id, name, info, eyecolor, hairlength, features, color, weight,createdate)
VALUES(0,'페르시안', '풍성한 털이 마치 솜사탕과 같은 페르시안 고양이는 장모종의 대표주자입니다. 얼굴은 옆으로 긴 차원형 모양이며 입과 코가 평면으로 눌려있고 많이 눌린경우에는 코가 들창코처럼 보이기도 합니다.', '호박색,구리색,그린,골드', '장모', '직모', '흰색, 블루,흑색, 레드, 크림, 실버', '5.4', DEFAULT);

INSERT INTO CAT (id, name, info, eyecolor, hairlength, features, color, weight,createdate)
VALUES(0, '메인쿤', '메인 쿤은 탄탄한 골격 넓은 가슴 폭에 훌륭한 장식 머리를 가지고 있고, 몸통 길이로 마치 살쾡이처럼 몸이 큰 고양이입니다. 노르웨이 숲 고양이와 비슷하다고 되어 있습니다 만, 노르웨이 숲 고양이의 옆모습이 직선 인 반면, 메인 쿤의 옆모습은 콧날은 부드러운 곡선을 그리고 있습니다. 네모 난 얼굴에 뾰족한 귀가 있고, 귀에는 많은 털이 나 있습니다. ', '구리색, 그린, 골드, 오드아이', '장모', '직모', '흰색, 흑색, 블루, 레드, 크림, 갈색, 실버, 톨토이즈쉘, 블루크림, 골드', '4.3', DEFAULT);

INSERT INTO CAT (id, name, info, eyecolor, hairlength, features, color, weight,createdate)
VALUES(0, '시암 고양이', '대체로 크림색 털이지만 점점 마치 눈에 다크서클이 낀 듯 코 주변으로 특징적으로 짙은 색의 털이 나고 귀, 꼬리, 발 등도 거뭇해진다. 장화를 신은 듯한 외견과 사파이어 색의 눈이 특징이다. 샴의 털은 온도 민감성 돌연변이로, 태어 난지 얼마 안된 샴은 포인트 컬러가 옅으며 어느 정도 성장 후에 환경에 따라 추우면 짙은 색으로 변하고, 더우면 옅은 색으로 변한다.', '블루', '단모', '부드러움', '초콜릿, 암갈색,라일락, 블루, 레드, 크림, 엷은 황갈색', '5', DEFAULT);

INSERT INTO CAT (id, name, info,eyecolor,hairlength,features,color,weight,createdate)
VALUES(0, '렉돌', '헝겊 인형같은 얼굴과 손발 등의 특징적인 얼룩이 들어간 머리 색깔, 중간 긴 피모를 가진 고양이입니다. 체형은 긴 그리고 서브 스탄 샤 타입으로 큰 머리에 약간 치켜 올라가 파란 눈, 둥근 뺨을 하고 있습니다. 꼬리는 몸길이만큼의 길이로 근육질로 중량감이 있는 체격입니다.', '블루', '장모', '매끄러움', '프로스트, 블루, 초콜릿, 암갈색, 레드, 라일락, 블루크림', '5', DEFAULT);

COMMIT;

SELECT * FROM CAT;
