package com.original.sense.psit.model.ResponseModel

data class ResposneFullDetails(
    val error: Boolean,
    val responseData: ResponseDataFullDetails,
    val status_code: Int
)

data class ResponseDataFullDetails(
    val branch: String,
    val exp_year: Int,
    val name: String,
    val roll_no: Long,
    val section: String,
    val student_id: Int,
    val type: String
)