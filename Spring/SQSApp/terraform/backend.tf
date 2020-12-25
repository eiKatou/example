terraform {
  backend "s3" {
    bucket = "terraform-state-954199018376"
    key    = "example/sqs_app"
    region = "us-west-2"
  }
}