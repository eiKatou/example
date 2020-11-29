import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

// TravelAgentServiceに予約依頼を出します。
fun main(args: Array<String>) = runBlocking {
    println("Client")

    val snsClient = AmazonSNSClientBuilder.standard()
        .withCredentials(ProfileCredentialsProvider(QueueResource.AWS_PROFILE_NAME))
        .withRegion(QueueResource.AWS_REGION)
        .build()

    repeat(3) {
        val subject = UUID.randomUUID().toString()
        val message = "my message $it"
        val publishResult = snsClient.publish(
            QueueResource.TRAVEL_AGENT_REQUEST_TOPIC_ARN,
            message, subject
        )
        println(publishResult.messageId)
        delay(5000L)
    }
}
