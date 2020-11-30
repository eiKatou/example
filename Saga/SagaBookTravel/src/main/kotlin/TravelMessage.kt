import com.amazonaws.services.sqs.model.Message
import com.google.gson.Gson

class TravelMessageUtil {
    companion object {
        /**
         * SQS Messageから旅行予約IDを取得します
         */
        fun getBookTripId(message: Message): String{
            return Gson().fromJson<TravelMessageBody>(message.body, TravelMessageBody::class.java).Subject
        }

        /**
         * SQS Messageから旅行予約ユーザーを取得します
         */
        fun getBookTripUser(message: Message): String{
            return Gson().fromJson<TravelMessageBody>(message.body, TravelMessageBody::class.java).Message
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