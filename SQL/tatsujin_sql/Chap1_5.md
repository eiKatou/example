# テーブル同士のマッチング

    CREATE TABLE CourseMaster
    (course_id   INTEGER PRIMARY KEY,
    course_name VARCHAR(32) NOT NULL);

    INSERT INTO CourseMaster VALUES(1, '経理入門');
    INSERT INTO CourseMaster VALUES(2, '財務知識');
    INSERT INTO CourseMaster VALUES(3, '簿記検定');
    INSERT INTO CourseMaster VALUES(4, '税理士');

    CREATE TABLE OpenCourses
    (month       INTEGER ,
    course_id   INTEGER ,
        PRIMARY KEY(month, course_id));

    INSERT INTO OpenCourses VALUES(200706, 1);
    INSERT INTO OpenCourses VALUES(200706, 3);
    INSERT INTO OpenCourses VALUES(200706, 4);
    INSERT INTO OpenCourses VALUES(200707, 4);
    INSERT INTO OpenCourses VALUES(200708, 2);
    INSERT INTO OpenCourses VALUES(200708, 4);


    select 
        course_name,
        case when course_id in (select course_id from opencourses where month='200706') then '○' else '×' end as "6月",
        case when course_id in (select course_id from opencourses where month='200707') then '○' else '×' end as "7月",
        case when course_id in (select course_id from opencourses where month='200708') then '○' else '×' end as "8月"
    from coursemaster;