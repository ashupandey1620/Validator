package com.original.sense.psit.model

data class LoginResponse(
    val error: Boolean,
    val responseData: ResponseData,
    val status_code: Int
)

data class ResponseData(
    val msg: String,
    val token: Token
)

data class Token(
    val access: String,
    val refresh: String
)