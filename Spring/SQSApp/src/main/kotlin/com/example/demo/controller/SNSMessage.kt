package com.example.demo.controller

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SNSMessage (
    val messageId: String,
    val message: String,
)