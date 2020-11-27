# 作るもの
- VPC 1つ
- public subnet 1つ
- その他ネットワーク関係(internet gateway, route table)
- subnet内にEC2 1つ

subnetはus-west-2aに作る。EC2が起動できないリージョンがあるため。

# EC2用のkeypair作成
```shell
ssh-keygen -t rsa -f ec2-key-pair
```

# 実行
```shell
terraform init
terraform validate
terraform apply
```
最後にEC2のpublic DNS ホスト名が出力される。これを使ってEC2にSSHする。

# EC2に接続
```shell
ssh -i ./ec2-key-pair ec2-user@main_ec2_public_dns
```

# リソース削除
```shell
terraform destroy
```