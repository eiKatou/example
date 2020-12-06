class SagaStateRepository {
    companion object {
        // 履歴データが追加されていく
        val states = mutableListOf<SagaState>()

        private fun find(bookTripId: String): List<SagaState> {
            return states.filter { it.bookTripId == bookTripId }
        }

        /**
         * 旅行予約の受付
         */
        fun accepted(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.TravelAgent, Status.Accepted))
            printAllState()
        }

        /**
         * 車予約の完了
         */
        fun completedRentCar(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.CarRent, Status.Complete))
            printAllState()
        }

        /**
         * 車予約の失敗
         */
        fun failureRentCar(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.CarRent, Status.Failure))
            printAllState()
        }

        /**
         * 車予約のキャンセル成功
         */
        fun cancelCompletedRentCar(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.CarRent, Status.CancelComplete))
            printAllState()
        }

        /**
         * ホテル予約の完了
         */
        fun completedBookHotel(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.HotelReservation, Status.Complete))
            printAllState()
        }

        /**
         * ホテル予約の失敗
         */
        fun failureBookHotel(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.HotelReservation, Status.Failure))
            printAllState()
        }

        /**
         * 飛行機予約の完了
         */
        fun completedBookFlight(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.AirlineReservation, Status.Complete))
            printAllState()
        }

        /**
         * デバック用：全状態の出力
         */
        fun printAllState() {
            println("--- TravelStateRepository ---")
            states.forEach {
                println(it)
            }
        }
    }
}

data class SagaState(val bookTripId: String, val service: Service, val status: Status)
enum class Service {
    TravelAgent, CarRent, HotelReservation, AirlineReservation
}
enum class Status {
    Accepted, Complete, Failure, CancelComplete
}