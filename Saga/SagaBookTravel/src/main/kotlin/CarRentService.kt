import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.runBlocking

class CarRentService {
    companion object {
        fun execute() = runBlocking {
            println("Car Rent Service")
            val service = CarRentService()

            while(true) {
                service.receiveRequest { bookTripId ->
                    service.replyRentCarMessage(bookTripId)
                }
            }
        }
    }

    /**
     * 車予約のリクエストを受信 -> 次の処理
     */
    private fun receiveRequest(nextProcess: (String) -> Unit) {
        val messages = receiveRentCarMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            println("\n\n車の予約を受け付けました。 id:$bookTripId")

            nextProcess(bookTripId)

            deleteRentCarMessage(it)
        }
    }

    /**
     * 車予約メッセージを受信(最大10件）
     */
    private fun receiveRentCarMessage(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.RENT_CAR_REQUEST_QUEUE_URL)
    }

    /**
     * 受信済みの車予約メッセージを削除
     */
    private fun deleteRentCarMessage(message: Message) {
        QueueClient.deleteMessage(QueueResource.RENT_CAR_REQUEST_QUEUE_URL, message)
    }

    /**
     * 車予約リクエストの結果を返信
     */
    private fun replyRentCarMessage(bookTripId: String) {
        QueueClient.sendMessage(
            QueueResource.RENT_CAR_RESPONSE_TOPIC_ARN,
            subject = bookTripId,
            message = "OK. rent car."
        )
        println("\n\n車の予約を完了しました。 id:$bookTripId")
    }
}