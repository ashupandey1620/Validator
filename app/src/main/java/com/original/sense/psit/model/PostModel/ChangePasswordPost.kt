package com.original.sense.psit.model.PostModel

data class ChangePasswordPost(
    val current: String,
    val password: String,
    val password2: String,
)