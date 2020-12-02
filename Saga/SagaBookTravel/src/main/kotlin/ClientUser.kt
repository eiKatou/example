import java.util.*

// TravelAgentServiceに予約依頼を出します。
class Client {
    companion object {
        fun execute(user: String) {
            val subject = UUID.randomUUID().toString()
            val messageDetail = TravelMessageDetail(userName = user, requestType = "request")
            QueueClient.sendMessage(QueueResource.TRAVEL_AGENT_REQUEST_TOPIC_ARN, subject, messageDetail.toString())
            println("予約を依頼しました。 id:$subject, user:$user")
        }
    }
}

