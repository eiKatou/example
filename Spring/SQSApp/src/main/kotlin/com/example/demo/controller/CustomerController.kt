package com.example.demo.controller

import com.example.demo.logic.CustomerMessageLogic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/customers")
class CustomerController {
    @Autowired
    @Qualifier("customerQueueMessaging")
    lateinit var customerQueueMessaging: QueueMessagingTemplate

    @Autowired
    @Qualifier("customerNotificationMessaging")
    lateinit var customerNotificationMessaging: NotificationMessagingTemplate

    @RequestMapping(method = [RequestMethod.GET])
    fun getCustomer(): CustomerResponse? {
        // SQSで送信したメッセージを受信する場合
//        return sqsAppQueueMessaging.receiveAndConvert(CustomerMessage::class.java)?.toResponse()

        val message = customerQueueMessaging.receiveAndConvert(String::class.java)
            ?: throw RuntimeException("do not received.")
        return CustomerMessageLogic.convert(message).toResponse()
    }

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer: CustomerRequest): Boolean {
        // SQSで送信する場合
//        sqsAppQueueMessaging.convertAndSend(customer.toMessage())

        // SNSで送信する場合
        customerNotificationMessaging.convertAndSend(customer.toMessage())

        return true
    }
}