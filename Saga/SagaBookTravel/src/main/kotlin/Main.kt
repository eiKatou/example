import java.lang.IllegalArgumentException

fun main(args : Array<String>) {
    when (args[0]) {
        "bookTrip" -> Client.execute(args[1])
        "TravelAgentSvc" -> TravelAgentService.execute()
        "CarRentSvc" -> CarRentService.execute()
        "HotelReservationSvc" -> HotelReservationService.execute()
        "AirlineReservationSvc" -> AirlineReservationService.execute()
        else -> throw IllegalArgumentException("args: invalid.")
    }
}