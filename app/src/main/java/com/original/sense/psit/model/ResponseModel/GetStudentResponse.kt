package com.original.sense.psit.model.ResponseModel

data class GetStudentResponse(
    val errors: Boolean,
    val message: Message,
    val responseData: ResponseData,
    val status_code: Int
)

data class ResponseData(
    val name: String,
    val student_id: Int
)