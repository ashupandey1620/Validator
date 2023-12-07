package com.original.sense.psit.model.ResponseModel

data class LogoutResponse(
    val error: Boolean,
    val message: String,
    val statusCode: Int
)