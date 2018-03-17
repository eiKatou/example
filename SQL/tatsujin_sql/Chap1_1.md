# 既存のコード体系を新しい体系に変換して集計
初期データ

    CREATE TABLE PopTbl
    (pref_name VARCHAR(32) PRIMARY KEY,
    population INTEGER NOT NULL);

    INSERT INTO PopTbl VALUES('徳島', 100);
    INSERT INTO PopTbl VALUES('香川', 200);
    INSERT INTO PopTbl VALUES('愛媛', 150);
    INSERT INTO PopTbl VALUES('高知', 200);
    INSERT INTO PopTbl VALUES('福岡', 300);
    INSERT INTO PopTbl VALUES('佐賀', 100);
    INSERT INTO PopTbl VALUES('長崎', 200);
    INSERT INTO PopTbl VALUES('東京', 400);
    INSERT INTO PopTbl VALUES('群馬', 50);

caseで集計する

    select case pref_name
        when '徳島' then '四国'
        when '香川' then '四国'
        else 'その他' end as district,
        sum(population)
    from poptbl
    group by case pref_name
        when '徳島' then '四国'
        when '香川' then '四国'
        else 'その他' end;