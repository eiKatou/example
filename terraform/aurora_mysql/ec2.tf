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

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-ec2"
    },
  )
}

# key pair
# ssh-keygen -t rsa -f ec2-key-pair
resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "ec2-key-pair"
  public_key = file("./ec2-key-pair.pub")
}