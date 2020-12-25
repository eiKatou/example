# SQS
# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sqs_queue
resource "aws_sqs_queue" "main_queue" {
  name = "sqs-app"

  tags = merge(
    var.tags,
    {
      Name = "${var.tags.System}-${var.tags.Environment}-sqs"
    },
  )
}

//data "aws_iam_policy_document" "sqs-queue-policy" {
//  count = var.number_of_queues
//  policy_id = "sqs_policy"
//
//  statement {
//    sid    = "First"
//    effect = "Allow"
//
//    principals {
//      type        = "AWS"
//      identifiers = ["*"]
//    }
//
//    actions = [
//      "SQS:SendMessage",
//    ]
//
//    resources = [
//      aws_sqs_queue.main_queue[count.index].arn,
//    ]
//
//    condition {
//      test     = "ArnEquals"
//      variable = "aws:SourceArn"
//
//      values = [
//        aws_sns_topic.main_topic.arn,
//      ]
//    }
//  }
//}
//
//# 全ての権限のpolicyを与える
//resource "aws_sqs_queue_policy" "sqs_queue_policy" {
//  count = var.number_of_queues
//  queue_url = aws_sqs_queue.main_queue[count.index].id
//  policy    = data.aws_iam_policy_document.sqs-queue-policy[count.index].json
//}
//
//# SNS Subscription
//# https://registry.terraform.io/providers/hashicorp/aws/latest/docs/resources/sns_topic_subscription
//resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
//  count = var.number_of_queues
//  topic_arn = aws_sns_topic.main_topic.arn
//  protocol  = "sqs"
//  endpoint  = aws_sqs_queue.main_queue[count.index].arn
//}
//
output "aws_sqs_url" {
  value = [aws_sqs_queue.main_queue.id]
}
//output "aws_sns_arn" {
//  value = aws_sns_topic.main_topic.id
//}