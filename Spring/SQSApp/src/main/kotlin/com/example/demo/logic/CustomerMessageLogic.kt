package com.example.demo.logic

import com.example.demo.controller.CustomerMessage
import com.example.demo.controller.SNSMessage
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class CustomerMessageLogic {
    companion object {
        fun convert(message: String): CustomerMessage {
            val mapper = jacksonObjectMapper().apply {
                configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            }
            val snsMessage = mapper.readValue(message, SNSMessage::class.java)
            return jacksonObjectMapper().readValue(snsMessage.message, CustomerMessage::class.java)
        }
    }
}