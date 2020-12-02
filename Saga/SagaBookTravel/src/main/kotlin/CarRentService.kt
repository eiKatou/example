import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.runBlocking

class CarRentService {
    companion object {
        fun execute() = runBlocking {
            println("Car Rent Service")
            val service = CarRentService()

            while(true) {
                // 車予約のリクエスト
                service.receiveRequest { bookTripId, requestMessageDetail ->
                    // 今は全てOK。失敗しない。
                    service.replyRentCarMessage(bookTripId, requestMessageDetail, ResponseType.OK)
                }
            }
        }
    }

    /**
     * 車予約のリクエストを受信 -> 次の処理
     */
    private fun receiveRequest(nextProcess: (String, RequestMessageDetail) -> Unit) {
        val messages = receiveRentCarMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val requestMessageDetail = TravelMessageUtil.getRequestMessageDetail(it)
            if (requestMessageDetail.requestType == RequestType.Request) {
                println("\n\n車の予約を受け付けました。 id:$bookTripId")
            } else {
                println("\n\n車の予約キャンセルを受け付けました。 id:$bookTripId")
            }

            nextProcess(bookTripId, requestMessageDetail)

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
        println("\n\n車の予約を完了しました。 id:$bookTripId")
    }
}