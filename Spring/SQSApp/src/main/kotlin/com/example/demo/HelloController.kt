package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/hello")
class HelloController {
    @Autowired
    @Qualifier("sqsAppQueueMessaging")
    lateinit var sqsAppQueueMessaging: QueueMessagingTemplate

    @RequestMapping(method = [RequestMethod.GET])
    fun hello() = "hello world."

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(@RequestBody message: String): String {
        sqsAppQueueMessaging.convertAndSend(message)
        return "OK"
    }

}