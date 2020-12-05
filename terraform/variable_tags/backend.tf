terraform {
  backend "s3" {
    bucket = "terraform-state-954199018376"
    key    = "example/variable_tags"
    region = "us-west-2"
  }
}