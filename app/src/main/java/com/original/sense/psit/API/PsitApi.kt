package com.original.sense.psit.API

import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.TempRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PsitApi {


    @POST("/api/user/register/")
    suspend fun tempRegister(
        @Body tempRegisterPost: TempRegisterPost
    ): Response<TempRegister>

    @POST("/api/user/login/")
    suspend fun login(
        @Body loginPost: LoginPost
    ): Response<LoginResponse>

}







//@FormUrlEncoded
//@POST("/api/user/register/")
//suspend fun tempRegister(
//    @Field("email") email: String,
//    @Field("name") name: String,
//    @Field("username") username: String,
//    @Field("phoneno") phoneno: Long,
//    @Field("roomno") roomno: String
//):Response<TempRegister>


//    @POST("/api/user/register/")
//    suspend fun tempRegister(
//       email: String,
//       name: String,
//       username: String,
//       phoneno: Long,
//       roomno: String
//    ):Response<TempRegister>