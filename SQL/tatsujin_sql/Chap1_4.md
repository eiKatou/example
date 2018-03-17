# 条件を分岐させたUPDATE

    CREATE TABLE SomeTable
    (p_key CHAR(1) PRIMARY KEY,
    col_1 INTEGER NOT NULL, 
    col_2 CHAR(3) NOT NULL);

    INSERT INTO SomeTable VALUES('a', 1, 'あ');
    INSERT INTO SomeTable VALUES('b', 2, 'い');
    INSERT INTO SomeTable VALUES('c', 3, 'う');

    update SOMETABLE set p_key =
        case when p_key='a' then 'b' 
            when p_key='b' then 'a' else p_key end
    where p_key in('a', 'b');