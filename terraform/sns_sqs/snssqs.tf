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

data "aws_iam_policy_document" "sqs-queue-policy" {
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
      aws_sqs_queue.main_queue.arn,
    ]

    condition {
      test     = "ArnEquals"
      variable = "aws:SourceArn"

      values = [
        aws_sns_topic.main_topic.arn,
      ]
    }
  }
}

# 全ての権限のpolicyを与える
resource "aws_sqs_queue_policy" "sqs_queue_policy" {
  queue_url = aws_sqs_queue.main_queue.id
  policy    = data.aws_iam_policy_document.sqs-queue-policy.json
}

# SNS Subscription
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic_subscription
resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.main_topic.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.main_queue.arn
}

output "aws_sqs_url" {
  value = aws_sqs_queue.main_queue.id
}
output "aws_sns_arn" {
  value = aws_sns_topic.main_topic.id
}