package com.example.demo.controller

data class CustomerRequest (
    val id: Int,
    val firstName: String,
    val lastName: String,
)

fun CustomerRequest.toMessage(): CustomerMessage {
    return CustomerMessage(
        id = id,
        firstName = firstName,
        lastName = lastName,
    )
}