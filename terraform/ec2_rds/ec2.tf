# Security Group
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group_rule

# global ip 取得
# https://dev.classmethod.jp/articles/reference-my-pubic-ip-in-terraform/
data "http" "ifconfig" {
  url = "https://ifconfig.co/ip"
}
locals {
  current-ip        = chomp(data.http.ifconfig.body)
  allowed-cidr-myip = "${local.current-ip}/32"
}

resource "aws_security_group" "public-web-sg" {
  name        = "public-web-sg"
  description = "Allow SSH inbound traffic"
  vpc_id      = aws_vpc.main.id

  ingress {
    description = "SSH from VPC"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = [local.allowed-cidr-myip]
  }

  ingress {
    description = "Ping from VPC"
    from_port   = -1
    to_port     = -1
    protocol    = "icmp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "terraform-test-public-web-sg"
  }
}

resource "aws_security_group" "private-db-sg" {
  name   = "private-db-sg"
  vpc_id = aws_vpc.main.id
  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["10.0.1.0/24"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "terraform-test-private-db-sg"
  }
}

# EC2
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/instance
# https://dev.classmethod.jp/articles/launch-ec2-from-latest-amazon-linux2-ami-by-terraform/

# 最新のamiを取得する
data "aws_ssm_parameter" "amzn2_ami" {
  name = "/aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2"
}

resource "aws_instance" "terraform_ec2" {
  ami                    = data.aws_ssm_parameter.amzn2_ami.value
  instance_type          = "t2.micro"
  key_name               = aws_key_pair.ec2-key-pair.id
  vpc_security_group_ids = [aws_security_group.public-web-sg.id]
  subnet_id              = aws_subnet.public-web.id
  user_data              = file("./userdata/cloud-init.tpl")

  tags = {
    Name = "terraform-test-ec2"
  }
}

# key pair
# ssh-keygen -t rsa -f ec2-key-pair
resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "ec2-key-pair"
  public_key = file("./ec2-key-pair.pub")
}

output "main_ec2_public_dns" {
  value = aws_instance.terraform_ec2.public_dns
}