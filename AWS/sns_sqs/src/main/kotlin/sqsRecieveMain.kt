import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry
import com.amazonaws.services.sqs.model.ReceiveMessageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

fun main(args: Array<String>) = runBlocking {
    println("start: receive message.")

    val SQS_URL: String="https://sqs.us-west-2.amazonaws.com/954199018376/terraform-example-queue_01"
    val sqsClient = AmazonSQSClientBuilder.standard()
        .withCredentials(ProfileCredentialsProvider("work"))
        .withRegion("us-west-2")
        .build()

    repeat(100) {
        println("receive message count:${it}.")
        receiveAndDeleteMessage(sqsClient, SQS_URL)
        delay(1000L)
    }
}

fun receiveAndDeleteMessage(sqsClient: AmazonSQS, queueUrl: String) {
    // receive message
    val receiveMessageRequest = ReceiveMessageRequest(queueUrl).withMaxNumberOfMessages(10)
    val messages = sqsClient.receiveMessage(receiveMessageRequest).messages
    messages.forEach {
        println("messageId : ${it.messageId}")
        println("body : ${it.body}")
    }

    // delete message
    if (messages.isEmpty()) {
        return
    }
    val deleteEntries = messages.map {
        DeleteMessageBatchRequestEntry(UUID.randomUUID().toString(), it.receiptHandle)
    }
    val deleteMessageRequest = DeleteMessageBatchRequest(queueUrl, deleteEntries)
    sqsClient.deleteMessageBatch(deleteMessageRequest)
}