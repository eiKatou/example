# SNS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic
resource "aws_sns_topic" "main_topic" {
  name = "terraform-example-topic"

  tags = {
    Name = "main_topic"
  }
}

# SQS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sqs_queue
resource "aws_sqs_queue" "main_queue" {
  name = "terraform-example-queue"

  tags = {
    Name = "main_sqs"
  }
}

# 全ての権限のpolicyを与える
resource "aws_sqs_queue_policy" "sqs_queue_policy" {
  queue_url = aws_sqs_queue.main_queue.id
  policy    = <<EOF
{
  "Version": "2012-10-17",
  "Id": "sqspolicy",
  "Statement": [
    {
      "Sid": "First",
      "Effect": "Allow",
      "Principal": {
        "AWS": "*"
      },
      "Action": "SQS:SendMessage",
      "Resource": "${aws_sqs_queue.main_queue.arn}",
      "Condition": {
        "ArnLike": {
          "aws:SourceArn": "${aws_sns_topic.main_topic.arn}"
        }
      }
    }
  ]
}
EOF
}

# SNS Subscription
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic_subscription
resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.main_topic.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.main_queue.arn
}

output "aws_sns_url" {
  value = aws_sqs_queue.main_queue.id
}
output "aws_sqs_url" {
  value = aws_sns_topic.main_topic.id
}