import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class HotelReservationService {
    companion object {
        fun execute() = runBlocking {
            println("Hotel Reservation Service")
            val service = HotelReservationService()

            while (true) {
                val requestProcess = fun(bookTripId: String, requestMessageDetail: RequestMessageDetail) {
                    // ホテル予約は全てOK。失敗しない。
                    println("\n\nホテルの予約を完了しました。 id:$bookTripId")
                    service.replyBookHotelMessage(bookTripId, requestMessageDetail, ResponseType.OK)
                }

                // ホテル予約のリクエストを受けて処理を行う。
                service.receiveRequest(requestProcess)

                delay(1000L)
            }
        }
    }

    /**
     * ホテル予約のリクエストを受信 -> 次の処理
     */
    private fun receiveRequest(
        requestProcess: (String, RequestMessageDetail) -> Unit
    ) {
        // リクエストメッセージを一括受信
        val messages = receiveBookHotelMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val requestMessageDetail = TravelMessageUtil.getRequestMessageDetail(it)
            when(requestMessageDetail.requestType) {
                RequestType.Book -> {
                    println("\n\nホテルの予約を受け付けました。 id:$bookTripId")
                    requestProcess(bookTripId, requestMessageDetail)
                }
                else -> {
                    println("\n\nホテルの予約キャンセルを受け付けました。 id:$bookTripId")
                    throw RuntimeException("ホテルの予約キャンセルはできません")
                }
            }
            // リクエストメッセージの削除
            deleteBookHotelMessage(it)
        }
    }

    /**
     * ホテル予約メッセージを受信(最大10件）
     */
    private fun receiveBookHotelMessage(): List<Message> {
        return QueueClient.receiveMessage(QueueResource.BOOK_HOTEL_REQUEST_QUEUE_URL)
    }

    /**
     * 受信済みのホテル予約メッセージを削除
     */
    private fun deleteBookHotelMessage(message: Message) {
        QueueClient.deleteMessage(QueueResource.BOOK_HOTEL_REQUEST_QUEUE_URL, message)
    }

    /**
     * ホテル予約リクエストの結果を返信
     */
    private fun replyBookHotelMessage(bookTripId: String, requestMessageDetail: RequestMessageDetail, responseType: ResponseType) {
        QueueClient.sendMessage(
            QueueResource.BOOK_HOTEL_RESPONSE_TOPIC_ARN,
            subject = bookTripId,
            message = ResponseMessageDetail(userName = requestMessageDetail.userName, responseType, requestMessageDetail.requestType).toString()
        )
    }
}