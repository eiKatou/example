package com.example.demo

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class MessageReceiver {
    @SqsListener("sqs-app")
    fun queueListener(message: String?) {
        println(message)
    }
}