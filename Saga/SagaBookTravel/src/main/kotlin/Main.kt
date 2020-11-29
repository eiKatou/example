import java.lang.IllegalArgumentException

fun main(args : Array<String>) {
    when (args[0]) {
        "client" -> Client.execute()
        "travel" -> TravelAgentService.execute()
        else -> throw IllegalArgumentException("args: client or travel")
    }
}