# WordPress環境構築
## Dockerfileとdocker-composeの概要
Dockerfileは、zip extensionをインストール済みWordPressイメージを作成する。docker-compose.ymlは、以下の修正が入っている。
- Dockerfileで作ったイメージを利用する
- phpMyAdminが動くようにする
- WordPressのデータ保存先をData Volumeにする
- WordPressは80番ポートで動かすようにする（元は8000番ポート）

## Dockerfileとdocker-composeの準備
~/wordpress/配下に、以下のファイルを配置。
- Dockerfile
- docker-compose.yml

## イメージの作成
```bash
cd ~/wordpress
docker build -t wordpress:20170419 ~/wordpress
docker images
```

# WordPressの起動・停止
## WordPress起動
```bash
cd ~/wordpress/
docker-compose up -d
```

## WordPress停止
```bash
cd ~/wordpress/
docker-compose down
```

## WordPressへのアクセス
http://localhost/

## PHP My Adminへのアクセス
http://localhost:8080/  
wordpress/wordpressでログイン
