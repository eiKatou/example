# 作るもの
- SNS 1トピック
- SQS 1キュー
- SNSのサブスクリプションにSQSを追加

# 実行
```shell
terraform init
terraform validate
terraform apply
```

# SNSにメッセージ送信
[AWS CLI での Amazon SNS の使用 - AWS Command Line Interface](https://docs.aws.amazon.com/ja_jp/cli/latest/userguide/cli-services-sns.html)
```shell
export AWS_PROFILE=work
export AWS_DEFAULT_REGION=us-west-2
export TOPIC_ARN="topic_arn"

# トピックへの発行
aws sns publish --topic-arn $TOPIC_ARN --message "Hello World!"
```

# SQSでメッセージ受信と削除
```shell
export AWS_PROFILE=work
export AWS_DEFAULT_REGION=us-west-2

# queueが見れること
aws sqs list-queues
export QUEUE_URL="queue-url"

# メッセージの送信
aws sqs send-message --queue-url $QUEUE_URL --message-body "hello, world"

# メッセージの受信
aws sqs receive-message --queue-url $QUEUE_URL --wait-time-seconds 3

# メッセージの削除
aws sqs delete-message --queue-url $QUEUE_URL --receipt-handle {ReceiptHandle}

# メッセージの全削除
aws sqs purge-queue --queue-url $QUEUE_URL
```

# リソース削除
```shell
terraform destroy
```

# 動作環境
```shell
$ terraform -version
Terraform v0.13.5

$ sw_vers
ProductName:    Mac OS X
ProductVersion: 10.14.6
BuildVersion:   18G4032
```