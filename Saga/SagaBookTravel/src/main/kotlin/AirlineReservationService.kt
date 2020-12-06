import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class AirlineReservationService {
    companion object {
        fun execute() = runBlocking {
            println("Airline Reservation Service")
            val service = AirlineReservationService()

            while (true) {
                val requestProcess = fun(bookTripId: String, requestMessageDetail: RequestMessageDetail) {
                    // 飛行機予約は全てOK。失敗しない。
                    println("\n\n飛行機の予約を完了しました。 id:$bookTripId")
                    service.replyFlightHotelMessage(bookTripId, requestMessageDetail, ResponseType.OK)
                }

                // 飛行機予約のリクエストを受けて処理を行う。
                service.receiveRequest(requestProcess)

                delay(500L)
            }
        }
    }

    /**
     * 飛行機予約のリクエストを受信 -> 次の処理
     */
    private fun receiveRequest(
        requestProcess: (String, RequestMessageDetail) -> Unit
    ) {
        // リクエストメッセージを一括受信
        val messages = receiveBookFrightMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val requestMessageDetail = TravelMessageUtil.getRequestMessageDetail(it)
            when(requestMessageDetail.requestType) {
                RequestType.Request -> {
                    println("\n\n飛行機の予約を受け付けました。 id:$bookTripId")
                    requestProcess(bookTripId, requestMessageDetail)
                }
                else -> {
                    println("\n\n飛行機の予約キャンセルを受け付けました。 id:$bookTripId")
                    throw RuntimeException("飛行機の予約キャンセルはできません")
                }
            }
            // リクエストメッセージの削除
            deleteBookFlightMessage(it)
        }
    }

    /**
     * 飛行機予約メッセージを受信(最大10件）
     */
    private fun receiveBookFrightMessage(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.BOOK_FLIGHT_REQUEST_QUEUE_URL)
    }

    /**
     * 受信済みの飛行機予約メッセージを削除
     */
    private fun deleteBookFlightMessage(message: Message) {
        QueueClient.deleteMessage(QueueResource.BOOK_FLIGHT_REQUEST_QUEUE_URL, message)
    }

    /**
     * 飛行機予約リクエストの結果を返信
     */
    private fun replyFlightHotelMessage(bookTripId: String, requestMessageDetail: RequestMessageDetail, responseType: ResponseType) {
        QueueClient.sendMessage(
            QueueResource.BOOK_FLIGHT_RESPONSE_TOPIC_ARN,
            subject = bookTripId,
            message = ResponseMessageDetail(userName = requestMessageDetail.userName, responseType, requestMessageDetail.requestType).toString()
        )
    }
}