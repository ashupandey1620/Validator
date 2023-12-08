package com.original.sense.psit.model.PostModel

import com.google.gson.annotations.SerializedName

data class TempRegisterPost(
    val email: String ,
    val name: String ,
    val username: String ,
    val phoneno: Long ,
    val roomno: String ,
)
