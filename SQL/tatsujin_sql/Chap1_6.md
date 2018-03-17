#CASE式の中で集約関数を使う

    CREATE TABLE StudentClub
    (std_id  INTEGER,
    club_id INTEGER,
    club_name VARCHAR(32),
    main_club_flg CHAR(1),
    PRIMARY KEY (std_id, club_id));

    INSERT INTO StudentClub VALUES(100, 1, '野球',        'Y');
    INSERT INTO StudentClub VALUES(100, 2, '吹奏楽',      'N');
    INSERT INTO StudentClub VALUES(200, 2, '吹奏楽',      'N');
    INSERT INTO StudentClub VALUES(200, 3, 'バドミントン','Y');
    INSERT INTO StudentClub VALUES(200, 4, 'サッカー',    'N');
    INSERT INTO StudentClub VALUES(300, 4, 'サッカー',    'N');
    INSERT INTO StudentClub VALUES(400, 5, '水泳',        'N');
    INSERT INTO StudentClub VALUES(500, 6, '囲碁',        'N');

    select 
        case when count(*) = 1 then max(club_id)
        else
            max(case when main_club_flg = 'Y' then club_id else null end)
        end
    from studentclub group by std_id;