# 試したいこと
- Terraformの構成を標準の形にする
- Terraformのバージョンを固定する(required_versionでの指定とtfenv)
- tfstateファイルをS3に配置する。DynamoDBまでは使わない

リージョンはus-west-2。

# tfenv
```shell
echo 0.13.0 > .terraform-version
```

# S3バケットの作成
```shell
BUCKET_NAME=terraform-state-954199018376
REGION=us-west-2
aws --region $REGION s3api create-bucket \
  --create-bucket-configuration LocationConstraint=$REGION \
  --bucket $BUCKET_NAME
aws s3api put-bucket-versioning \
  --bucket $BUCKET_NAME \
  --versioning-configuration Status=Enabled
```

# 実行
```shell
terraform init
terraform validate
terraform apply
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