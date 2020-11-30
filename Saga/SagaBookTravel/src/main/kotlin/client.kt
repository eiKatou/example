import java.util.*

// TravelAgentServiceに予約依頼を出します。
class Client {
    companion object {
        fun execute(user: String) {
            val subject = UUID.randomUUID().toString()
            QueueClient.sendMessage(QueueResource.TRAVEL_AGENT_REQUEST_TOPIC_ARN, subject, user)
            println("予約を依頼しました。 id:$subject, user:$user")
        }
    }
}

