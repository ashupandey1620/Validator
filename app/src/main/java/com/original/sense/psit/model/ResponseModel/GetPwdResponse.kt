package com.original.sense.psit.model.ResponseModel

data class GetPwdResponse(
    val errors: Boolean,
    val message: MessageXX,
    val responseData: ResponseDataX,
    val status_code: Int
)

data class ResponseDataX(
    val password: String
)