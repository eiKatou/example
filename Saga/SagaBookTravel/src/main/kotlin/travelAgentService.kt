import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class TravelAgentService {
    companion object {
        fun execute() = runBlocking {
            println("Travel Agent Service")
            val service = TravelAgentService()

            while(true) {
                // BookTripのメッセージを受信 -> 車を予約
                val messages = service.receiveBookTrip()
                messages.forEach {
                    // TODO: state管理のためのデータ保存
                    service.requestRentCar(it.messageId)
                    service.deleteReceivedBookTrip(it)
                }

                // 少し待つ
                delay(1000L)
                print(".")
            }
        }
    }

    /**
     * 旅行予約を受信(最大10件）
     */
    fun receiveBookTrip(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL)
    }

    /**
     * 受信済みの旅行予約を削除
     */
    fun deleteReceivedBookTrip(message: Message) {
        QueueClient.deleteMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL, message)
    }

    /**
     * レンタカー予約を送信
     */
    fun requestRentCar(bookTripId: String) {
        QueueClient.sendMessage(QueueResource.RENT_CAR_REQUEST_TOPIC_ARN, subject = bookTripId, message = "rent car.")
    }

    /**
     * レンタカー予約結果を受信
     */
    fun receiveRentCar(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.RENT_CAR_RESPONSE_QUEUE_URL)
    }

}