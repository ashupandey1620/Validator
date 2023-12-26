package com.original.sense.psit.model.ResponseModel

data class ResponseEditProfile(
    val error: Boolean,
    val responseData: ResponseDataUpdate,
    val status_code: Int
)

data class ResponseDataUpdate(
    val msg: String
)