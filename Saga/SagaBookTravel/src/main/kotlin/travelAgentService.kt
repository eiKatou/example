import com.amazonaws.services.sqs.model.Message
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class TravelAgentService {
    companion object {
        fun execute() = runBlocking {
            println("Travel Agent Service")
            val service = TravelAgentService()

            while(true) {
                // 旅行予約を受信 -> RentCarに進む
                service.receiveBookTrip { bookTripId ->
                    service.requestRentCar(bookTripId)
                }

                    // TODO:RentCarの予約完了メッセージを受信 -> BookHotelに進む
                    // TODO:RentCarの予約不可メッセージを受信 -> 予約依頼に失敗を返す
                    // TODO:RentCarの予約取消完了メッセージを受信 -> 予約依頼に失敗を返す

                    // TODO:BookHotelの予約完了メッセージを受信 -> BookFlightを予約
                    // TODO:BookHotelの予約不可メッセージを受信 -> RentCarの予約取消依頼
                    // TODO:BookHotelの予約取消完了メッセージを受信 -> RentCarの予約取消依頼

                    // TODO:BookFlightの予約完了メッセージを受信 -> RentCar,BookHotelが完了していることを確認して予約成功を返す
                    // TODO:BookFlightの予約不可メッセージを受信 -> BookHotelの予約取消依頼
                    // TODO:BookFlightの予約取消完了メッセージを受信 -> BookHotelの予約取消依頼

                    // 少し待つ
                    delay(1000L)
                    print(".")
                }
            }

    }

    /**
     * 旅行予約を受信 -> 次の処理
     */
    private fun receiveBookTrip(nextProcess: (String) -> Unit) {
        val messages = receiveBookTripMessage()
        messages.forEach {
            val bookTripId = MessageUtil.getBookTripId(it)
            val user = MessageUtil.getBookTripUser(it)
            println("\n\n予約を受信しました。 id:$bookTripId, user:$user")

            // 受付完了を記録
            SagaStateRepository.accepted(bookTripId)

            // 次の処理
            nextProcess(bookTripId)

            // メッセージ削除
            deleteReceivedBookTripMessage(it)
        }
    }

    /**
     * 旅行予約メッセージを受信(最大10件）
     */
    private fun receiveBookTripMessage(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL)
    }

    /**
     * 受信済みの旅行予約メッセージを削除
     */
    private fun deleteReceivedBookTripMessage(message: Message) {
        QueueClient.deleteMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL, message)
    }
    
    /**
     * 車を予約
     */
    private fun requestRentCar(bookTripId: String) {
        println("\n\n車を予約します。 id:$bookTripId")
        QueueClient.sendMessage(
            QueueResource.RENT_CAR_REQUEST_TOPIC_ARN,
            subject = bookTripId,
            message = "rent car."
        )
        SagaStateRepository.requestedRentCar(bookTripId)
    }

    /**
     * 受信済みの車予約メッセージを削除
     */
    private fun receiveRentCarMessage(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.RENT_CAR_RESPONSE_QUEUE_URL)
    }

}