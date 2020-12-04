# RDS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/db_instance
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/db_subnet_group
resource "aws_db_subnet_group" "private-db" {
  name = "private-db"
  subnet_ids = [
  aws_subnet.private-db1.id, aws_subnet.private-db2.id]
  tags = {
    Name = "terraform-test-db"
  }
}

resource "aws_db_instance" "terraform-test-db" {
  identifier             = "terraform-test-db"
  allocated_storage      = 20
  storage_type           = "gp2"
  engine                 = "mysql"
  engine_version         = "5.7"
  instance_class         = "db.t2.micro"
  name                   = "mydb"
  username               = "foo"
  password               = "foobarbaz"
  vpc_security_group_ids = [aws_security_group.private-db-sg.id]
  db_subnet_group_name   = aws_db_subnet_group.private-db.name
  skip_final_snapshot    = true
}

output "rds_endpoint" {
  value = aws_db_instance.terraform-test-db.endpoint
}