package com.example.demo

import com.example.demo.config.SqsConfig
import com.example.demo.controller.CustomerMessage
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class CustomerMessageReceiver {
    @SqsListener(value = [SqsConfig.QUEUE_CUSTOMER], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun queueListener(message: String, @Header("SenderId") senderId: String) {
        val customer = jacksonObjectMapper().readValue(message, CustomerMessage::class.java)
        println(senderId)
        println(customer)
    }
}