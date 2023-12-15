package com.original.sense.psit.model.ResponseModel

data class ResponseTokenRefresh(
    val code: String,
    val detail: String,
    val access: String
)