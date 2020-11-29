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
./gradlew run --args="client"
```

# Sagaサンプルを止める
## アプリケーション
アプリを停止する。

## SNS/SQSのリソース削除
```shell
cd ./terraform
terraform destroy
```