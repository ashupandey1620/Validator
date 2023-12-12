package com.original.sense.psit.model.ResponseModel

data class LogoutResponse(
    val error: Boolean,
    val responseData: ResponseDataLogout,
    val status_code: Int
)

data class ResponseDataLogout(
    val password: String
)