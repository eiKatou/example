# リソース作成
```bash
cd terraform
terraform init
terraform apply
```

# アプリを試す
アプリを起動。

```bash
# メッセージ送信
curl http://localhost:8080/api/hello -v -X POST -H "Content-Type: application/json" -d "message hello"

export AWS_PROFILE=work
export AWS_DEFAULT_REGION=us-west-2

# queueが見れること
aws sqs list-queues
export QUEUE_URL="queue-url"

# メッセージの受信
aws sqs receive-message --queue-url $QUEUE_URL > message.txt

# メッセージの削除
aws sqs delete-message --queue-url $QUEUE_URL --receipt-handle {ReceiptHandle}

# メッセージの全削除
aws sqs purge-queue --queue-url $QUEUE_URL
```

# API
## GETメソッドを呼ぶ
```bash
curl http://localhost:8080/api/hello -v -X GET
```

## POSTメソッドを呼ぶ
```bash
curl http://localhost:8080/api/hello -v -X POST -H "Content-Type: application/json" -d "message hello"
```