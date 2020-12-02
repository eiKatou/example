import com.amazonaws.services.sqs.model.Message
import com.google.gson.Gson
import com.google.gson.GsonBuilder




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
            return getMessageDetail(message).userName
        }

        fun getMessageDetail(message: Message): TravelMessageDetail {
            val messageDetailString = Gson().fromJson<TravelMessageBody>(message.body, TravelMessageBody::class.java).Message
            return TravelMessageDetail.fromString(messageDetailString)
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
class TravelMessageDetail(val userName: String, val requestType: String) {
    override fun toString() = "$userName:$requestType"
    companion object {
        fun fromString(string: String):TravelMessageDetail {
            val values = string.split(":")
            return TravelMessageDetail(values[0], values[1])
        }
    }
}