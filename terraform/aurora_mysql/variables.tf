variable "tags" {
  default = {
    Environment = "test"
    System      = "example-aurora"
  }
  description = "Additional resource tags"
  type        = map(string)
}