# SQS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sqs_queue
resource "aws_sqs_queue" "friend_queue" {
  name = "friend"

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-sqsapp"
    },
  )
}

# SNS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic
resource "aws_sns_topic" "customer_topic" {
  name = "customer-topic"

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-customer"
    },
  )
}

# SQS
resource "aws_sqs_queue" "customer_queue" {
  name = "customer"

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-customer"
    },
  )
}

data "aws_iam_policy_document" "customer-queue-policy" {
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
      aws_sqs_queue.customer_queue.arn,
    ]

    condition {
      test     = "ArnEquals"
      variable = "aws:SourceArn"

      values = [
        aws_sns_topic.customer_topic.arn,
      ]
    }
  }
}

# 全ての権限のpolicyを与える
resource "aws_sqs_queue_policy" "customer_queue_policy" {
  queue_url = aws_sqs_queue.customer_queue.id
  policy    = data.aws_iam_policy_document.customer-queue-policy.json
}

# SNS Subscription
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic_subscription
resource "aws_sns_topic_subscription" "customer_updates_sqs_target" {
  topic_arn = aws_sns_topic.customer_topic.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.customer_queue.arn
}

output "aws_sqs_url" {
  value = [aws_sqs_queue.friend_queue.id, aws_sqs_queue.customer_queue.id]
}
output "aws_sns_arn" {
  value = aws_sns_topic.customer_topic.id
}