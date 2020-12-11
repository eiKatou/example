terraform {
  backend "s3" {
    bucket = "terraform-state-954199018376"
    key    = "example/aurora_mysql"
    region = "us-west-2"
  }
}