terraform {
  backend "s3" {
    bucket = "terraform-state-954199018376"
    key    = "example/tfstate_s3"
    region = "us-west-2"
  }
}