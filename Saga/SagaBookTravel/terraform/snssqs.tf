variable "pipe_name" {
  default = ["travel-agent-request", "rent-car-request", "rent-car-response"]
}

# SNS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic
resource "aws_sns_topic" "main_topic" {
  count = length(var.pipe_name)
  name  = "${element(var.pipe_name, count.index)}-topic"

  tags = {
    Name = "${element(var.pipe_name, count.index)}-topic"
  }
}

# SQS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sqs_queue
resource "aws_sqs_queue" "main_queue" {
  count = length(var.pipe_name)
  name  = "${element(var.pipe_name, count.index)}-queue"

  tags = {
    Name = "${element(var.pipe_name, count.index)}-queue"
  }
}

data "aws_iam_policy_document" "sqs-queue-policy" {
  count     = length(var.pipe_name)
  policy_id = "sqs_policy"

  statement {
    sid    = "First"
    effect = "Allow"

    principals {
      type        = "AWS"
      identifiers = ["*"]
    }

    actions = [
      "SQS:SendMessage",
    ]

    resources = [
      aws_sqs_queue.main_queue[count.index].arn,
    ]

    condition {
      test     = "ArnEquals"
      variable = "aws:SourceArn"

      values = [
        aws_sns_topic.main_topic[count.index].arn,
      ]
    }
  }
}

# 全ての権限のpolicyを与える
resource "aws_sqs_queue_policy" "sqs_queue_policy" {
  count     = length(var.pipe_name)
  queue_url = aws_sqs_queue.main_queue[count.index].id
  policy    = data.aws_iam_policy_document.sqs-queue-policy[count.index].json
}

# SNS Subscription
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic_subscription
resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  count     = length(var.pipe_name)
  topic_arn = aws_sns_topic.main_topic[count.index].arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.main_queue[count.index].arn
}

output "aws_sns_arn" {
  value = aws_sns_topic.main_topic[*].id
}
output "aws_sqs_url" {
  value = aws_sqs_queue.main_queue[*].id
}
