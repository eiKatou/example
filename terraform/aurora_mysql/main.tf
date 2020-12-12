# global ip 取得
# https://dev.classmethod.jp/articles/reference-my-pubic-ip-in-terraform/
data "http" "ifconfig" {
  url = "https://ifconfig.co/ip"
}
locals {
  current-ip        = chomp(data.http.ifconfig.body)
  allowed-cidr-myip = "${local.current-ip}/32"
}