package com.example.demo

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class SqsQueueSender {
    @Autowired
    lateinit var messagingTemplate: QueueMessagingTemplate

    fun sendString(message: String) {
        messagingTemplate!!.send("sqs-app", MessageBuilder.withPayload(message).build())
//        messagingTemplate.send(MessageBuilder.withPayload(message).build())
    }

    fun send(message: Any) {
//        messagingTemplate!!.convertAndSend("queueName", message)
        messagingTemplate.convertAndSend(message)
    }
}