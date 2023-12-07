package com.original.sense.psit.model.ResponseModel

data class TempRegister(
    val error: Boolean?,
    val message: Message?,
    val responseData: Any?,
    val statusCode: Int?
)
data class Message(
    val email: List<String>,
    val phoneNo: List<String>,
    val username: List<String>,
    val nonFieldErrors: List<String>
)