package com.original.sense.psit.API

import com.original.sense.psit.model.LoginResponse
import com.original.sense.psit.model.TeacherDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PsitApi {

    @POST("/api/user/login/")
    suspend fun login(category:String): Response<LoginResponse>

    //For Static Headers
    @GET("/api/user/profile/")
    suspend fun getCategories() : Response<TeacherDetailModel>

}