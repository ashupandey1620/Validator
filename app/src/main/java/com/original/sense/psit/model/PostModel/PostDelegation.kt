package com.original.sense.psit.model.PostModel

data class PostDelegation(
    val description: String,
    val from_date: Any,
    val lectures: List<Int>,
    val students: List<Long>,
    val subject: String,
    val to_date: String
)