package com.original.sense.psit.API

import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetPwdPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.PostModel.sendResetPassword
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetPwdResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.TempRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PsitApi {

//    @Headers("Content-Type: application/json")
//    @FormUrlEncoded
//    @POST("/api/user/register/")
//    suspend fun tempRegister(
//        @Field("email") email: String ,
//        @Field("name") name: String ,
//        @Field("username") username: String ,
//        @Field("phoneno") phoneno: Long ,
//        @Field("roomno") roomno: String
//    ):Response<TempRegister>


    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/api/user/logout/")
    suspend fun logOut(
        @Field("refresh_token") refreshToken: String
    ):Response<TempRegister>



    @Headers("Content-Type: application/json")
    @POST("/api/user/register/")
    suspend fun tempRegister(
        @Body tempRegisterPost: TempRegisterPost
    ): Response<TempRegister>

    @Headers("Content-Type: application/json")
    @POST("/api/user/login/")
    suspend fun login(
        @Body loginPost: LoginPost
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/user/send-reset-password-email/")
    suspend fun resetPasswordRequest(
        @Body resetPost: sendResetPassword
    ): Response<TempRegister>

    @Headers("Content-Type: application/json")
    @POST("/api/user/changepassword/")
    suspend fun changePassword(
        @Header("Authorization") access: String,
        @Body changePasswordPost : ChangePasswordPost
    ): Response<ChangePasswordResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/student/getdetails/")
    suspend fun getStudent(
        @Header("Authorization") access: String,
        @Body getStudentPost: GetStudentPost
    ): Response<GetStudentResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/nfc/getpwd/")
    suspend fun getChipPwd(
        @Header("Authorization") access: String,
        @Body getPwdPost: GetPwdPost
    ): Response<GetPwdResponse>

}









//    @POST("/api/user/register/")
//    suspend fun tempRegister(
//       email: String,
//       name: String,
//       username: String,
//       phoneno: Long,
//       roomno: String
//    ):Response<TempRegister>