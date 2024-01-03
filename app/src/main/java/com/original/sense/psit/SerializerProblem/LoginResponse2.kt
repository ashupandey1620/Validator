package com.original.sense.psit.SerializerProblem

data class LoginResponse2(
    val access_token: String,
    val refresh_token: String,
    val success: Boolean,
    val user: User
)