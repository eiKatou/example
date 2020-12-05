// VPC
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/vpc
resource "aws_vpc" "main" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true # DNS解決を有効化
  enable_dns_hostnames = true # DNSホスト名を有効化

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-VPC"
    },
  )
}

// Subnet
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/subnet
resource "aws_subnet" "main" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-west-2a"
  map_public_ip_on_launch = true

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-subnet"
    },
  )
}

// Internet Gateway
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/internet_gateway
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-gw"
    },
  )
}

// Route Table
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/route_table
resource "aws_route_table" "r" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-rt"
    },
  )
}

resource "aws_route_table_association" "a" {
  subnet_id      = aws_subnet.main.id
  route_table_id = aws_route_table.r.id
}

// Security Group
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/security_group_rule

// global ip 取得
// https://dev.classmethod.jp/articles/reference-my-pubic-ip-in-terraform/
data http ifconfig {
  url = "https://ifconfig.co/ip"
}
locals {
  current-ip        = chomp(data.http.ifconfig.body)
  allowed-cidr-myip = "${local.current-ip}/32"
}

resource "aws_security_group" "main" {
  name        = "main_sg"
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

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-sg"
    },
  )
}

// EC2
// https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/instance
// https://dev.classmethod.jp/articles/launch-ec2-from-latest-amazon-linux2-ami-by-terraform/

// 最新のamiを取得する
data aws_ssm_parameter amzn2_ami {
  name = "/aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2"
}

resource "aws_instance" "main_ec2" {
  ami                    = data.aws_ssm_parameter.amzn2_ami.value
  instance_type          = "t2.micro"
  key_name               = aws_key_pair.ec2-key-pair.id
  vpc_security_group_ids = [aws_security_group.main.id]
  subnet_id              = aws_subnet.main.id
  //  user_data              = file("./userdata/cloud-init.tpl")

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-ec2"
    },
  )
}

// key pair
// ssh-keygen -t rsa -f ec2-key-pair
resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "ec2-key-pair"
  public_key = file("./ec2-key-pair.pub")
}

output "main_ec2_public_dns" {
  value = aws_instance.main_ec2.public_dns
}