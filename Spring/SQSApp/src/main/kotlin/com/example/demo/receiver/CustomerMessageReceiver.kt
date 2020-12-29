package com.example.demo.receiver

import com.example.demo.config.MessagingConfig
import com.example.demo.logic.CustomerMessageLogic
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class CustomerMessageReceiver {
    @SqsListener(value = [MessagingConfig.QUEUE_CUSTOMER], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun queueListener(message: String, @Header("SenderId") senderId: String) {
//        println(senderId)
//        println(message)

        val customer = CustomerMessageLogic.convert(message)
        println(customer)
    }
}