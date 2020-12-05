variable "tags" {
  default = {
    Environment = "test"
    System      = "example-vartags"
  }
  description = "Additional resource tags"
  type        = map(string)
}