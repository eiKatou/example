# VPC
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/vpc
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

# subnet
# public
resource "aws_subnet" "public-web" {
  vpc_id                  = aws_vpc.main.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-west-2a"
  map_public_ip_on_launch = true

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-public-subnet"
    },
  )
}

# private
resource "aws_subnet" "private-db1" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-west-2b"

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-private-subnet1"
    },
  )
}

resource "aws_subnet" "private-db2" {
  vpc_id            = aws_vpc.main.id
  cidr_block        = "10.0.3.0/24"
  availability_zone = "us-west-2c"
  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-private-subnet2"
    },
  )
}


# Internet Gateway
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/internet_gateway
resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.main.id

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-gw"
    },
  )
}

# Route Table
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/route_table
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

resource "aws_route_table_association" "public-a" {
  subnet_id      = aws_subnet.public-web.id
  route_table_id = aws_route_table.r.id
}
