# 演習問題1

    CREATE TABLE Greatests
    (key CHAR(1) PRIMARY KEY,
    x   INTEGER NOT NULL,
    y   INTEGER NOT NULL,
    z   INTEGER NOT NULL);

    INSERT INTO Greatests VALUES('A', 1, 2, 3);
    INSERT INTO Greatests VALUES('B', 5, 5, 2);
    INSERT INTO Greatests VALUES('C', 4, 7, 1);
    INSERT INTO Greatests VALUES('D', 3, 3, 8);

## 回答

    select 
        case when x > y then x else y end as m
    from GREATESTS;

    select 
        key,
        (case when x > y then 
            case when x > z then x
            else z end
        else y end)
        as m
    from GREATESTS;

    select 
    key,
    max(col)
    from (SELECT key, x AS col FROM Greatests
            UNION ALL
            SELECT key, y AS col FROM Greatests
            UNION ALL
            SELECT key, z AS col FROM Greatests)
    group by key;


