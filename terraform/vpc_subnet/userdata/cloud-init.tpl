#cloud-config
repo_update: true
repo_upgrade: all

packages:
 - python-pip
 - awscli

runcmd:
 - [ sh, -c, "mkdir /tmp/testdir" ]
 - [ sh, -c, "yum install -y https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm" ]
 - yum-config-manager --disable mysql80-community
 - yum-config-manager --enable mysql57-community
 - yum install -y mysql-community-client
