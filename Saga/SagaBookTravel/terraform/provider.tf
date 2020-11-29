terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.18"
    }
  }
}

provider "aws" {
  profile = "work" // 自分の環境に合わせて変えること
  region  = "us-west-2"
}
