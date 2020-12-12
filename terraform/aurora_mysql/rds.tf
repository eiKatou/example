# RDS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/db_instance
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/db_subnet_group
resource "aws_db_subnet_group" "private-db" {
  name = "private-db"
  subnet_ids = [
  aws_subnet.private-db1.id, aws_subnet.private-db2.id]
  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-db-subnet-group"
    },
  )
}

# Aurora
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/rds_cluster
resource "aws_rds_cluster" "default" {
  cluster_identifier = "aurora-cluster-demo"
  engine             = "aurora-mysql"
  engine_version     = "5.7.mysql_aurora.2.03.2"
  //  availability_zones     = ["us-west-2b", "us-west-2c"]
  database_name          = "mydb"
  master_username        = "foo"
  master_password        = "foobarbaz"
  vpc_security_group_ids = [aws_security_group.private-db-sg.id]
  db_subnet_group_name   = aws_db_subnet_group.private-db.name
  skip_final_snapshot    = true
  lifecycle {
    ignore_changes = [
      master_password,
      availability_zones
    ]
  }
}
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/rds_cluster_instance
resource "aws_rds_cluster_instance" "cluster_instances" {
  count                = 2
  identifier           = "aurora-cluster-demo-${count.index}"
  cluster_identifier   = aws_rds_cluster.default.id
  instance_class       = "db.t3.small"
  engine               = aws_rds_cluster.default.engine
  engine_version       = aws_rds_cluster.default.engine_version
  db_subnet_group_name = aws_db_subnet_group.private-db.name
}
