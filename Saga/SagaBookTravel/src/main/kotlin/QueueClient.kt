import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishResult
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.*
import com.google.gson.Gson
import java.util.*

class QueueClient {
    companion object {
        private val snsClient: AmazonSNS = AmazonSNSClientBuilder.standard()
            .withCredentials(ProfileCredentialsProvider(QueueResource.AWS_PROFILE_NAME))
            .withRegion(QueueResource.AWS_REGION)
            .build()

        fun sendMessage(topicArn: String, subject: String, message: String): PublishResult {
            val publishResult = snsClient.publish(
                topicArn,
                message, subject)
            println("""
                send message
                -> subject:$subject
                -> $topicArn
            """.trimIndent())
            return publishResult
        }

        private val sqsClient: AmazonSQS = AmazonSQSClientBuilder.standard()
            .withCredentials(ProfileCredentialsProvider(QueueResource.AWS_PROFILE_NAME))
            .withRegion(QueueResource.AWS_REGION)
            .build()

        fun receiveMessage(queueUrl: String, maxCount: Int = 10): List<Message> {
            val receiveMessageRequest =
                ReceiveMessageRequest(queueUrl).withMaxNumberOfMessages(maxCount)
            val messages = sqsClient.receiveMessage(receiveMessageRequest).messages
            // debug print
            messages.forEach {
                val bookTripId = Gson().fromJson<MessageBody>(it.body, MessageBody::class.java).Subject
                println("""
                    receive message
                    -> bookTripId:$bookTripId
                    -> $queueUrl
                """.trimIndent())
            }
            return messages
        }

        fun deleteMessage(queueUrl: String, message: Message): DeleteMessageResult {
            return sqsClient.deleteMessage(queueUrl, message.receiptHandle)
        }

        fun deleteMessage(queueUrl: String, messages: List<Message>): DeleteMessageBatchResult {
            if (messages.isEmpty()) {
                // 適当に返す
                return DeleteMessageBatchResult()
            }
            val deleteEntries = messages.map {
                DeleteMessageBatchRequestEntry(UUID.randomUUID().toString(), it.receiptHandle)
            }
            val deleteMessageRequest =
                DeleteMessageBatchRequest(queueUrl, deleteEntries)
            return sqsClient.deleteMessageBatch(deleteMessageRequest)
        }
    }
}