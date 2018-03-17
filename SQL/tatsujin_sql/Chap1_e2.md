# 回答
select 
    pref_name,
    sum(case when sex=1 then population end) as mail,
    sum(case when sex=2 then population end) as femail
from poptbl2
group by pref_name;


select 
    case when sex=1 then '男性' else '女性' end  as "性別",
    sum(population) as "全国",
    sum(case when pref_name='徳島' then population end) as "徳島",
    sum(case when pref_name='香川' then population end) as "香川",
    sum(case when pref_name='愛媛' then population end) as "愛媛",
    sum(case when pref_name='高知' then population end) as "高知",
    sum(case when pref_name in ('徳島', '香川', '愛媛', '高知') then population end) as "四国"
from poptbl2
group by sex, case when sex=1 then '男性' else '女性' end;
