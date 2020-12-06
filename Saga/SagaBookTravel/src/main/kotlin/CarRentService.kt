import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CarRentService {
    companion object {
        fun execute() = runBlocking {
            println("Car Rent Service")
            val service = CarRentService()

            while(true) {
                val requestProcess = fun(bookTripId: String, requestMessageDetail: RequestMessageDetail) {
                    // 車予約は全てOK。失敗しない。
                    println("\n\n車の予約を完了しました。 id:$bookTripId")
                    service.replyRentCarMessage(bookTripId, requestMessageDetail, ResponseType.OK)
                }
                val cancelRequestProcess = fun(bookTripId: String, requestMessageDetail: RequestMessageDetail) {
                    // 車キャンセル予約は全てOK。失敗しない。
                    println("\n\n車の予約キャンセルを完了しました。 id:$bookTripId")
                    service.replyRentCarMessage(bookTripId, requestMessageDetail, ResponseType.OK)
                }

                // 車予約のリクエストを受けて処理を行う。
                service.receiveRequest(requestProcess, cancelRequestProcess)

                delay(500L)
            }
        }
    }

    /**
     * 車予約のリクエストを受信 -> 次の処理
     */
    private fun receiveRequest(
        requestProcess: (String, RequestMessageDetail) -> Unit,
        cancelProcess: (String, RequestMessageDetail) -> Unit
    ) {
        // リクエストメッセージを一括受信
        val messages = receiveRentCarMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val requestMessageDetail = TravelMessageUtil.getRequestMessageDetail(it)
            when(requestMessageDetail.requestType) {
                RequestType.Request -> {
                    println("\n\n車の予約を受け付けました。 id:$bookTripId")
                    requestProcess(bookTripId, requestMessageDetail)
                }
                RequestType.Cancel -> {
                    println("\n\n車の予約キャンセルを受け付けました。 id:$bookTripId")
                    cancelProcess(bookTripId, requestMessageDetail)
                }
            }
            // リクエストメッセージの削除
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
    private fun replyRentCarMessage(bookTripId: String, requestMessageDetail: RequestMessageDetail, responseType: ResponseType) {
        QueueClient.sendMessage(
            QueueResource.RENT_CAR_RESPONSE_TOPIC_ARN,
            subject = bookTripId,
            message = ResponseMessageDetail(userName = requestMessageDetail.userName, responseType, requestMessageDetail.requestType).toString()
        )
    }
}