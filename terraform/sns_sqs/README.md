# 作るもの
- SNS 1トピック
- SQS 1キュー

# 実行
```shell
terraform init
terraform validate
terraform apply
```

# SNSにメッセージ送信

# SQSでメッセージ受信と削除

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