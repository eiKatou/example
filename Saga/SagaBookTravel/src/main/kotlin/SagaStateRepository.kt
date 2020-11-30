class SagaStateRepository {
    companion object {
        // 履歴データが追加されていく
        val states = mutableListOf<SagaState>()

        private fun find(bookTripId: String): List<SagaState> {
            return states.filter { it.bookTripId == bookTripId }
        }

        fun accepted(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.TravelAgent, Status.Accepted))
            printAllState()
        }

        fun requestedRentCar(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.RentCar, Status.Requested))
            printAllState()
        }

        fun completedRentCar(bookTripId: String) {
            states.add(SagaState(bookTripId, Service.RentCar, Status.Complete))
            printAllState()
        }

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
    TravelAgent, RentCar
}
enum class Status {
    Accepted, Requested, Complete
}