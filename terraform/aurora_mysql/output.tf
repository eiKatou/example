output "main_ec2_public_dns" {
  value = aws_instance.terraform_ec2.public_dns
}

# https://github.com/terraform-aws-modules/terraform-aws-rds-aurora/blob/master/outputs.tf
output "this_rds_cluster_endpoint" {
  description = "The cluster endpoint"
  value       = element(concat(aws_rds_cluster.default.*.endpoint, [""]), 0)
}
output "this_rds_cluster_reader_endpoint" {
  description = "The cluster reader endpoint"
  value       = element(concat(aws_rds_cluster.default.*.reader_endpoint, [""]), 0)
}