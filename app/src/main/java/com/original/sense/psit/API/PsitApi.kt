package com.original.sense.psit.API

import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.TeacherDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PsitApi {

    @POST("/api/user/login/")
    suspend fun login(category:String): Response<LoginResponse>

    //For Static Headers
    @GET("/api/user/profile/")
    suspend fun getCategories() : Response<TeacherDetailModel>

}