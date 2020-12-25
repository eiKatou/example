package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/hello")
class HelloController {
    @Autowired
    lateinit var sqsQueueSender: SqsQueueSender

    @RequestMapping(method = [RequestMethod.GET])
    fun hello() = "hello world."

    @RequestMapping(method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun postMessage(@RequestBody message: String): String {
        sqsQueueSender.sendString(message)
        return "OK"
    }

}