package com.original.sense.psit.model.PostModel

data class PostSuspension(
    val description: String,
    val from_date: String,
    val students: List<Long>,
    val to_date: String
)