import com.amazonaws.services.sqs.model.Message
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class TravelAgentService {
    companion object {
        fun execute() = runBlocking {
            println("Travel Agent Service")
            val service = TravelAgentService()

            while(true) {
                // 旅行予約を受信 -> RentCarに進む
                service.receiveBookTrip { bookTripId, userName ->
                    TravelServicesClient.requestRentCar(bookTripId, userName)
                }

                // RentCarの返信メッセージを受信
                service.receiveRentCarResponse(
                    completeProcess = { bookTripId, userName ->
                        TravelServicesClient.requestBookHotel(bookTripId, userName)
                    },
                    failureProcess = { bookTripId, userName ->
                        println("===============")
                        println("予約できませんでした。　bookTripId:$bookTripId, userName:$userName")
                    },
                    cancelCompleteProcess = { bookTripId, userName ->
                        println("===============")
                        println("予約できませんでした。　bookTripId:$bookTripId, userName:$userName")
                    },
                )

                // BookHotelの返信メッセージを受信
                service.receiveBookHotelResponse(
                    completeProcess = { bookTripId, userName ->
                        TravelServicesClient.requestBookFlight(bookTripId, userName)
                    },
                    failureProcess = { bookTripId, userName ->
                        TravelServicesClient.requestCancelRentCar(bookTripId, userName)
                    }
                )

                // BookFlightの返信メッセージを受信
                // BookFlightは必ず成功する！
                service.receiveBookFlightResponse(
                    completeProcess = { bookTripId, userName ->
                        // TODO: RentCar,BookHotelが完了していることを確認する。
                        println("===============")
                        println("予約が完了しました。　bookTripId:$bookTripId, userName:$userName")
                    },
                )

                // 少し待つ
                delay(300L)
            }
        }
    }

    /**
     * 旅行予約を受信 -> 次の処理
     */
    private fun receiveBookTrip(nextProcess: (bookTripId: String, userName: String) -> Unit) {
        val messages = TravelServicesClient.receiveBookTripMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val userName = TravelMessageUtil.getRequestMessageDetail(it).userName
            println("\n\n予約を受信しました。 id:$bookTripId, user:$userName")

            // 受付完了を記録
            SagaStateRepository.accepted(bookTripId)

            // 次の処理
            nextProcess(bookTripId, userName)

            // メッセージ削除
            TravelServicesClient.deleteReceivedBookTripMessage(it)
        }
    }

    /**
     * 車予約返信を受信 -> 次の処理
     */
    private fun receiveRentCarResponse(
        completeProcess: (bookTripId: String, userName: String) -> Unit,
        failureProcess: (bookTripId: String, userName: String) -> Unit,
        cancelCompleteProcess: (bookTripId: String, userName: String) -> Unit,
    ) {
        val messages = TravelServicesClient.receiveRentCarResponseMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val responseMessageDetail = TravelMessageUtil.getResponseMessageDetail(it)
            val userName = responseMessageDetail.userName
            println("\n\n車予約返信を受信しました。 id:$bookTripId, user:$userName")

            if (responseMessageDetail.requestType == RequestType.Book) {
                if (responseMessageDetail.responseType == ResponseType.OK) {
                    // 車予約完了
                    SagaStateRepository.completedRentCar(bookTripId)
                    completeProcess(bookTripId, userName)
                } else {
                    // 車予約失敗
                    SagaStateRepository.failureRentCar(bookTripId)
                    failureProcess(bookTripId, userName)
                }
            }

            if (responseMessageDetail.requestType == RequestType.Cancel) {
                if (responseMessageDetail.responseType == ResponseType.OK) {
                    // 車予約キャンセル完了
                    SagaStateRepository.cancelCompletedRentCar(bookTripId)
                    cancelCompleteProcess(bookTripId, userName)
                } else {
                    // 車予約キャンセル失敗
                    throw RuntimeException("車予約キャンセル失敗はありえません")
                }
            }

            // メッセージ削除
            TravelServicesClient.deleteRentCarResponseMessage(it)
        }
    }

    /**
     * ホテル予約返信を受信 -> 次の処理
     */
    private fun receiveBookHotelResponse(
        completeProcess: (bookTripId: String, userName: String) -> Unit,
        failureProcess: (bookTripId: String, userName: String) -> Unit,
    ) {
        val messages = TravelServicesClient.receiveBookHotelResponseMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val responseMessageDetail = TravelMessageUtil.getResponseMessageDetail(it)
            val userName = responseMessageDetail.userName
            println("\n\nホテル予約返信を受信しました。 id:$bookTripId, user:$userName")

            if (responseMessageDetail.requestType == RequestType.Book) {
                if (responseMessageDetail.responseType == ResponseType.OK) {
                    // ホテル予約完了
                    SagaStateRepository.completedBookHotel(bookTripId)
                    completeProcess(bookTripId, userName)
                } else {
                    // ホテル予約失敗
                    SagaStateRepository.failureBookHotel(bookTripId)
                    failureProcess(bookTripId, userName)
                }
            } else {
                throw RuntimeException("ホテル予約以外の操作はありえません")
            }

            // メッセージ削除
            TravelServicesClient.deleteBookHotelResponseMessage(it)
        }
    }

    /**
     * 飛行機予約返信を受信 -> 次の処理
     */
    private fun receiveBookFlightResponse(
        completeProcess: (bookTripId: String, userName: String) -> Unit
    ) {
        val messages = TravelServicesClient.receiveBookFlightResponseMessage()
        messages.forEach {
            val bookTripId = TravelMessageUtil.getBookTripId(it)
            val responseMessageDetail = TravelMessageUtil.getResponseMessageDetail(it)
            val userName = responseMessageDetail.userName
            println("\n\n飛行機予約返信を受信しました。 id:$bookTripId, user:$userName")

            if (responseMessageDetail.requestType == RequestType.Book) {
                if (responseMessageDetail.responseType == ResponseType.OK) {
                    // 飛行機予約完了
                    SagaStateRepository.completedBookFlight(bookTripId)
                    completeProcess(bookTripId, userName)
                } else {
                    throw RuntimeException("飛行機予約の失敗はありえません")
                }
            } else {
                throw RuntimeException("飛行機予約の失敗はありえません")
            }

            // メッセージ削除
            TravelServicesClient.deleteBookFlightResponseMessage(it)
        }
    }

    private class TravelServicesClient {
        companion object {
            /**
             * 旅行予約メッセージを受信(最大10件）
             */
            fun receiveBookTripMessage(): List<Message> {
                return QueueClient.receiveMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL)
            }

            /**
             * 受信済みの旅行予約メッセージを削除
             */
            fun deleteReceivedBookTripMessage(message: Message) {
                QueueClient.deleteMessage(QueueResource.TRAVEL_AGENT_REQUEST_QUEUE_URL, message)
            }

            /**
             * 車を予約
             */
            fun requestRentCar(bookTripId: String, userName: String) {
                println("\n\n車を予約します。 id:$bookTripId")
                QueueClient.sendMessage(
                    QueueResource.RENT_CAR_REQUEST_TOPIC_ARN,
                    subject = bookTripId,
                    message = RequestMessageDetail(userName = userName, requestType = RequestType.Book).toString()
                )
            }

            /**
             * 車を予約キャンセル
             */
            fun requestCancelRentCar(bookTripId: String, userName: String) {
                println("\n\n車を予約キャンセルします。 id:$bookTripId")
                QueueClient.sendMessage(
                    QueueResource.RENT_CAR_REQUEST_TOPIC_ARN,
                    subject = bookTripId,
                    message = RequestMessageDetail(userName = userName, requestType = RequestType.Cancel).toString()
                )
            }

            /**
             * 車予約返信メッセージを受信(最大10件）
             */
            fun receiveRentCarResponseMessage(): List<Message> {
                return QueueClient.receiveMessage(QueueResource.RENT_CAR_RESPONSE_QUEUE_URL)
            }

            /**
             * 車予約返信メッセージを削除
             */
            fun deleteRentCarResponseMessage(message: Message) {
                QueueClient.deleteMessage(QueueResource.RENT_CAR_RESPONSE_QUEUE_URL, message)
            }

            /**
             * ホテルを予約
             */
            fun requestBookHotel(bookTripId: String, userName: String) {
                println("\n\nホテルを予約します。 id:$bookTripId")
                QueueClient.sendMessage(
                    QueueResource.BOOK_HOTEL_REQUEST_TOPIC_ARN,
                    subject = bookTripId,
                    message = RequestMessageDetail(userName = userName, requestType = RequestType.Book).toString()
                )
            }

            /**
             * ホテル予約返信メッセージを受信(最大10件）
             */
            fun receiveBookHotelResponseMessage(): List<Message> {
                return QueueClient.receiveMessage(QueueResource.BOOK_HOTEL_RESPONSE_QUEUE_URL)
            }

            /**
             * ホテル予約返信メッセージを削除
             */
            fun deleteBookHotelResponseMessage(message: Message) {
                QueueClient.deleteMessage(QueueResource.BOOK_HOTEL_RESPONSE_QUEUE_URL, message)
            }

            /**
             * 飛行機を予約
             */
            fun requestBookFlight(bookTripId: String, userName: String) {
                println("\n\n飛行機を予約します。 id:$bookTripId")
                QueueClient.sendMessage(
                    QueueResource.BOOK_FLIGHT_REQUEST_TOPIC_ARN,
                    subject = bookTripId,
                    message = RequestMessageDetail(userName = userName, requestType = RequestType.Book).toString()
                )
            }

            /**
             * 飛行機予約返信メッセージを受信(最大10件）
             */
            fun receiveBookFlightResponseMessage(): List<Message> {
                return QueueClient.receiveMessage(QueueResource.BOOK_FLIGHT_RESPONSE_QUEUE_URL)
            }

            /**
             * 飛行機予約返信メッセージを削除
             */
            fun deleteBookFlightResponseMessage(message: Message) {
                QueueClient.deleteMessage(QueueResource.BOOK_FLIGHT_RESPONSE_QUEUE_URL, message)
            }
        }
    }

}