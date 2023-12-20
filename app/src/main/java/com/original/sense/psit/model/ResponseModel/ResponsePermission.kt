package com.original.sense.psit.model.ResponseModel

data class ResponsePermission(
    val error: Boolean,
    val responseData: List<ResponseDataPermission>,
    val status_code: Int
)

data class ResponseDataPermission(
    val assigned_at: String,
    val assigned_by: String,
    val description: String,
    val from_date: String,
    val lectures: List<Int>,
    val permission_id: String,
    val students: List<Long>,
    val subject: String,
    val to_date: String,
    val type: String
)