# Dockerfileの作り方
以下で実行したコマンドをDockerfileに書いていく。
```bash
docker run -it --rm --entrypoint /bin/bash ubuntu:16.04
```

# Dockerfileでイメージを作成
UbuntuベースでRedisをインストール。

Dockerfileのあるディレクトリで実行。myredisというイメージができているはず。

```bash
docker build -t myredis:20180127 .
docker images
```

# docker-composeで起動・停止
コンテナを作成して起動する
```bash
docker-compose up
```

バックグラウンド実行する時は、-dオプションをつける
```bash
docker-compose up -d
```

停止する時
```bash
docker-compose stop
```

コンテナが出来ていて起動する時
```bash
docker-compose start
```

# redis-cliで接続
```bash
brew redis
redis-cli
```

# イメージを消す時
```bash
docker images
docker rmi IMAGEID
```

# docker-composeを使わずに、そのまま起動
```bash
docker run --rm -p 6379 -d myredis:20180127 /usr/bin/redis-server
```

docker ps で割り当てられているポートを確認。

```bash
redis-cli -h 0.0.0.0 -p 32770
```