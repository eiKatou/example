# 回答

    SELECT *
      FROM Greatests
    ORDER BY CASE key
                WHEN 'B' THEN 1
                WHEN 'A' THEN 2
                WHEN 'D' THEN 3
                WHEN 'C' THEN 4
                ELSE NULL END;
                
    SELECT key,
          CASE key
            WHEN 'B' THEN 1
            WHEN 'A' THEN 2
            WHEN 'D' THEN 3
            WHEN 'C' THEN 4
            ELSE NULL END AS sort_col
      FROM Greatests
    ORDER BY sort_col;