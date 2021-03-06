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

EC2の初期化にcloud-initを利用。
https://docs.aws.amazon.com/ja_jp/AWSEC2/latest/UserGuide/user-data.html

# EC2に接続
```shell
ssh -i ./ec2-key-pair ec2-user@main_ec2_public_dns
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