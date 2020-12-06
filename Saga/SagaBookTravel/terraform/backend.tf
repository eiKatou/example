terraform {
  backend "s3" {
    bucket = "terraform-state-954199018376"
    key    = "example/saga_book_travel"
    region = "us-west-2"
  }
}