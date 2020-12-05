# 試したいこと
- リソースのタグ名を変数で一括で指定したい。

リージョンはus-west-2。

# EC2用のkeypair作成
```shell
ssh-keygen -t rsa -f ec2-key-pair
```

# 実行
```shell
export AWS_PROFILE="work"
terraform init
terraform validate
terraform apply
```

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
$ tfenv --version
tfenv 2.0.0

$ terraform -version
Terraform v0.13.0
+ provider registry.terraform.io/hashicorp/aws v3.20.0

$ sw_vers
ProductName:    Mac OS X
ProductVersion: 10.13.6
BuildVersion:   17G14033
```