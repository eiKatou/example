# Sagaサンプル

# Sagaサンプルを動かす
## SNS/SQSのリソース作成
```shell
cd ./terraform
terraform init
terraform validate
terraform apply
```

## アプリケーション
以下の順にアプリを動かす。
1. TravelAgentService
1. Client

```shell
./gradlew run --args="travel"
./gradlew run --args="bookTrip user01"
```

# Sagaサンプルを止める
## アプリケーション
アプリを停止する。

## SNS/SQSのリソース削除
```shell
cd ./terraform
terraform destroy
```

# TODO
キャンセルを実装したい。そのためにはユーザーごとに予約状態を管理する必要がある。