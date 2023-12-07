package com.original.sense.psit.model.ResponseModel

data class LoginResponse(
    val error: Boolean ,
    val responseData: ResponseData ,
    val statusCode: Int
)

data class ResponseData(
    val msg: String,
    val token: Token
)

data class Token(
    val access: String,
    val refresh: String
)