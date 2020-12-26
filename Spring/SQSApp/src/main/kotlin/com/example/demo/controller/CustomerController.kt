package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/customers")
class CustomerController {
    @Autowired
    @Qualifier("customerQueueMessaging")
    lateinit var sqsAppQueueMessaging: QueueMessagingTemplate

    @RequestMapping(method = [RequestMethod.GET])
    fun getCustomer(): CustomerResponse? {
        return sqsAppQueueMessaging.receiveAndConvert(CustomerMessage::class.java)?.toResponse()
    }

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer: CustomerRequest): Boolean {
        sqsAppQueueMessaging.convertAndSend(customer.toMessage())
        return true
    }
}