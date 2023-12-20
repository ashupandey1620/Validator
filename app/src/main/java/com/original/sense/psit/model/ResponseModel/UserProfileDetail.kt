package com.original.sense.psit.model.ResponseModel

data class UserProfileDetail(
    val error: Boolean,
    val responseData: ResponseDataProfileDetail,
    val status_code: Int
)

data class ResponseDataProfileDetail(
    val email: String,
    val name: String,
    val phoneno: Long,
    val roomno: String,
    val username: String
)