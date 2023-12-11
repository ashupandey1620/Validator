package com.original.sense.psit.model.ResponseModel

data class LoginResponse(
    val error: Boolean ,
    val responseData: ResponseData2 ,
    val statusCode: Int
)

data class ResponseData2(
    val msg: String,
    val token: Token
)

data class Token(
    val access: String,
    val refresh: String
)