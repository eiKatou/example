package com.example.demo

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class SqsQueueSender {
    @Autowired
    @Qualifier("sqsAppQueueMessaging")
    lateinit var sqsAppQueueMessaging: QueueMessagingTemplate

    fun sendString(message: String) {
//        sqsAppQueueMessaging.send("sqs-app", MessageBuilder.withPayload(message).build())
        sqsAppQueueMessaging.send(MessageBuilder.withPayload(message).build())
    }

    fun send(message: Any) {
//        messagingTemplate!!.convertAndSend("queueName", message)
        sqsAppQueueMessaging.convertAndSend(message)
    }
}