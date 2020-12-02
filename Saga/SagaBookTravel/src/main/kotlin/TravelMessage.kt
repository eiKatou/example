import com.amazonaws.services.sqs.model.Message
import com.google.gson.Gson

class TravelMessageUtil {
    companion object {
        /**
         * SQS Messageから旅行予約IDを取得します
         */
        fun getBookTripId(message: Message): String {
            return Gson().fromJson<TravelMessageBody>(message.body, TravelMessageBody::class.java).Subject
        }

        /**
         * SQS Messageから旅行予約ユーザーを取得します
         */
        fun getBookTripUser(message: Message): String {
            return getRequestMessageDetail(message).userName
        }

        fun getRequestMessageDetail(message: Message): RequestMessageDetail {
            val messageDetailString = Gson().fromJson<TravelMessageBody>(message.body, TravelMessageBody::class.java).Message
            return RequestMessageDetail.fromString(messageDetailString)
        }
    }
}
data class TravelMessageBody(
    val Type: String,
    val MessageId: String,
    val TopicArn: String,
    val Subject: String,
    val Message: String,
    val Timestamp: String,
)
enum class RequestType {
    Request, Cancel
}
class RequestMessageDetail(val userName: String, val requestType: RequestType) {
    override fun toString() = "$userName:$requestType"
    companion object {
        fun fromString(string: String):RequestMessageDetail {
            val values = string.split(":")
            return RequestMessageDetail(values[0], RequestType.valueOf(values[1]))
        }
    }
}
enum class ResponseType {
    OK, NG
}
class ResponseMessageDetail(val userName: String, val responseType: ResponseType, val requestType: RequestType, ) {
    override fun toString() = "$userName:$responseType:$requestType"
    companion object {
        fun fromString(string: String):ResponseMessageDetail {
            val values = string.split(":")
            return ResponseMessageDetail(values[0], ResponseType.valueOf(values[1]), RequestType.valueOf(values[2]))
        }
    }
}