import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

// TravelAgentServiceに予約依頼を出します。
class Client {
    companion object {
        fun execute() = runBlocking {
            repeat(3) {
                val subject = UUID.randomUUID().toString()
                val message = "my message $it"
                QueueClient.sendMessage(QueueResource.TRAVEL_AGENT_REQUEST_TOPIC_ARN, subject, message)
                delay(5000L)
            }
        }
    }
}

