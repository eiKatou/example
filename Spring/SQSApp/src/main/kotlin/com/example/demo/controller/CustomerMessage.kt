package com.example.demo.controller

data class CustomerMessage (
    val id: Int,
    val firstName: String,
    val lastName: String,
)

fun CustomerMessage.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = id,
        firstName = firstName,
        lastName = lastName,
    )
}