DockerでMySQL 5.7を起動します。

# 起動と停止
```
docker-compose up -d
docker-compose down
```

# 接続手順
MySQL Workbenchで以下で接続。
- Host:port -> 127.0.0.1:3314
- User -> user
- Default schema -> sample_db

# Dockerへの接続
```
docker exec -it mysql_db_1 bash
mysql -u user -p
use sample_db;
show tables;
quit;
```

# 参考
[docker-composeでMySQL5.7を起動して接続してみた - Qiita](https://qiita.com/Manabu-man/items/58d0f98a15656ed65136)