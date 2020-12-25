variable "tags" {
  default = {
    Environment = "test"
    System      = "sqs-app"
  }
  description = "Additional resource tags"
  type        = map(string)
}