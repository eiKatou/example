import com.amazonaws.services.sqs.model.Message
import com.google.gson.Gson

class MessageUtil {
    companion object {
        /**
         * SQS Messageから旅行予約IDを取得します
         */
        fun getBookTripId(message: Message): String{
            return Gson().fromJson<MessageBody>(message.body, MessageBody::class.java).Subject
        }

        /**
         * SQS Messageから旅行予約ユーザーを取得します
         */
        fun getBookTripUser(message: Message): String{
            return Gson().fromJson<MessageBody>(message.body, MessageBody::class.java).Message
        }
    }
}
data class MessageBody(
    val Type: String,
    val MessageId: String,
    val TopicArn: String,
    val Subject: String,
    val Message: String,
    val Timestamp: String,
)