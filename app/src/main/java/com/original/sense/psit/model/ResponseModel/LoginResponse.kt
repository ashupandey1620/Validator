package com.original.sense.psit.model.ResponseModel

data class LoginResponse(
    val errors :Boolean?,
    val error: Boolean? ,
    val responseData: ResponseData2?,
    val statusCode: Int?,
    val message : MessageLogin?,
)


data class MessageLogin(
    val password: List<String>?,
    val username: List<String>?,
)

data class ResponseData2(
    val msg: String?,
    val token: Token?
)

data class Token(
    val access: String?,
    val refresh: String?
)