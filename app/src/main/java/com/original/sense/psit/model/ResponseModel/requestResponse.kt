package com.original.sense.psit.model.ResponseModel

data class requestResponse(
    val error: Boolean,
    val responseData: ResponseData,
    val status_code: Int
)