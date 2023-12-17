package com.original.sense.psit.model.ResponseModel

data class GetStudentResponse(
    val error: Boolean,
    val message: String,
    val responseData: ResponseData?,
    val status_code: Int
)

data class ResponseData(
    val name: String,
    val student_id: Int
)