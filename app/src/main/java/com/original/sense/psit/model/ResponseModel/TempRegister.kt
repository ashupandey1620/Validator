package com.original.sense.psit.model.ResponseModel

data class TempRegister(
    val error: Boolean?,
    val responseData: MessageRegister?,
    val statusCode: Int?
)

data class MessageRegister(
    val msg: String?,
)


