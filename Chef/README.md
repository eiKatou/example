# このDockerは？
Chefのサンプルを動かすために、Dockerを用意する

# Chefインストール済みのイメージを作成
DockerfileにChefをインストールする様に記述。
```bash
docker build --no-cache=true -t chef:20180210 .
docker images
```

# Chefインストール済みのDockerを起動
```bash
docker run -it --rm --entrypoint /bin/bash chef:20180210
```