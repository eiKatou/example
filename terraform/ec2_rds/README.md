# 作るもの
- VPCとか
- EC2を1台
- RDS for MySQL

リージョンはap-northeast-1。

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
chmod 600 ./ec2-key-pair
ssh -i ./ec2-key-pair ec2-user@main_ec2_public_dns

# MySQLはインストール済み
mysql -h DBURL -P 3306 -u foo -p mydb
# パスワードは "foobarbaz"
```

# リソース削除
```shell
terraform destroy
```

# 動作環境
```shell
$ terraform -version
Terraform v0.13.5
+ provider registry.terraform.io/hashicorp/aws v3.20.0
+ provider registry.terraform.io/hashicorp/http v2.0.0

$ sw_vers
ProductName:    Mac OS X
ProductVersion: 10.13.6
BuildVersion:   17G14033
```