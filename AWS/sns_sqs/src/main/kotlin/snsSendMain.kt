import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    println("Hello World!")

    val TOPIC_ARN: String="arn:aws:sns:us-west-2:954199018376:terraform-example-topic"
    val snsClient = AmazonSNSClientBuilder.standard()
        .withCredentials(ProfileCredentialsProvider("work"))
        .withRegion("us-west-2")
        .build()

    repeat(3) {
        val subject = "my subject $it"
        val message = "my message $it"
        sendMessage(snsClient = snsClient, topicArn = TOPIC_ARN, message = message, subject = subject)
        delay(5000L)
    }
}

fun sendMessage(snsClient: AmazonSNS, topicArn: String, message: String,
                subject: String) {
    val publishResult = snsClient.publish(topicArn, message, subject)
    println(publishResult.messageId)
}