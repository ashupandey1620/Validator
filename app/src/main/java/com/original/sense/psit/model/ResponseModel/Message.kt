package com.original.sense.psit.model.ResponseModel

data class Message(
    val code: String,
    val detail: String,
    val messages: List<MessageX>
)