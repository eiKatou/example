# 異なる条件の集計を1つのSQLで行う
初期データ

    CREATE TABLE PopTbl2
    (pref_name VARCHAR(32),
    sex CHAR(1) NOT NULL,
    population INTEGER NOT NULL,
        PRIMARY KEY(pref_name, sex));

    INSERT INTO PopTbl2 VALUES('徳島', '1',	60 );
    INSERT INTO PopTbl2 VALUES('徳島', '2',	40 );
    INSERT INTO PopTbl2 VALUES('香川', '1',	100);
    INSERT INTO PopTbl2 VALUES('香川', '2',	100);
    INSERT INTO PopTbl2 VALUES('愛媛', '1',	100);
    INSERT INTO PopTbl2 VALUES('愛媛', '2',	50 );
    INSERT INTO PopTbl2 VALUES('高知', '1',	100);
    INSERT INTO PopTbl2 VALUES('高知', '2',	100);
    INSERT INTO PopTbl2 VALUES('福岡', '1',	100);
    INSERT INTO PopTbl2 VALUES('福岡', '2',	200);
    INSERT INTO PopTbl2 VALUES('佐賀', '1',	20 );
    INSERT INTO PopTbl2 VALUES('佐賀', '2',	80 );
    INSERT INTO PopTbl2 VALUES('長崎', '1',	125);
    INSERT INTO PopTbl2 VALUES('長崎', '2',	125);
    INSERT INTO PopTbl2 VALUES('東京', '1',	250);
    INSERT INTO PopTbl2 VALUES('東京', '2',	150);

caseで集計する

select 
    pref_name,
    sum(case when sex='1' then population else 0 end) as cm,
    sum(case when sex='2' then population else 0 end) as cf
from poptbl2 group by pref_name;
