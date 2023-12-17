package com.original.sense.psit.model.ResponseModel

data class ResponsePostDelegation(
    val errors: Boolean,
    val message: MessageXXXX,
    val error : String,
    val responseData : RDPostDelegationResponse,
    val statusCode : Int,
)