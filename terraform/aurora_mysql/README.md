# 試したいこと
- Aurora MySQLを立ち上げて繋げるようにしたい

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

# Aurora MySQLに接続
- aurora-cluster-demo.cluster-ccnqmhelwx3d.us-west-2.rds.amazonaws.com
- aurora-cluster-demo.cluster-ro-ccnqmhelwx3d.us-west-2.rds.amazonaws.com
```shell
sudo yum localinstall https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm -y
sudo yum-config-manager --disable mysql80-community
sudo yum-config-manager --enable mysql57-community
sudo yum install mysql-community-client

mysql -h DBURL -P 3306 -u foo -p mydb
# パスワードは "foobarbaz"
```

## テーブルを作る
```sql
use mydb;
create table user (id int, name varchar(10));
show tables;
insert into user values (1, 'Yamada');
insert into user values (2, 'Sato');
select * from user;
commit;
quit
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
Terraform v0.14.2
+ provider registry.terraform.io/hashicorp/aws v3.21.0
+ provider registry.terraform.io/hashicorp/http v2.0.0

$ sw_vers
ProductName:    Mac OS X
ProductVersion: 10.13.6
BuildVersion:   17G14033
```