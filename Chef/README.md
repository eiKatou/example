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

# Chefを実行
Dockerの中に/chefを作成する。

```bash
knife cookbook create apache -o httpserver
vi /chef/httpserver/apache/recipes/default.rb 
vi /chef/localhost.json
vi /chef/solo.rb
chef-solo -c solo.rb -j ./localhost.json
```
