package com.original.sense.psit.API

import com.original.sense.psit.SerializerProblem.LoginRequest
import com.original.sense.psit.SerializerProblem.LoginResponse2
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetPwdPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.PermissionPost
import com.original.sense.psit.model.PostModel.PostDelegation
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.model.PostModel.PostSuspension
import com.original.sense.psit.model.PostModel.PostTokenAccess
import com.original.sense.psit.model.PostModel.PostTokenRefresh
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.PostModel.sendResetPassword
import com.original.sense.psit.model.RefreshTokenRequest
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetPwdResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.LogoutResponse
import com.original.sense.psit.model.ResponseModel.ResponseEditProfile
import com.original.sense.psit.model.ResponseModel.ResponsePermission
import com.original.sense.psit.model.ResponseModel.ResponsePostDelegation
import com.original.sense.psit.model.ResponseModel.ResponseTokenAccess
import com.original.sense.psit.model.ResponseModel.ResponseTokenRefresh
import com.original.sense.psit.model.ResponseModel.ResposneFullDetails
import com.original.sense.psit.model.ResponseModel.TempRegister
import com.original.sense.psit.model.ResponseModel.UserProfileDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PsitApi {


    @Headers("Content-Type: application/json")
    @POST("/api/user/logout/")
    suspend fun logOut(
        @Body refreshToken: RefreshTokenRequest
    ):Response<LogoutResponse>


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


    @Headers("Content-Type: application/json")
    @POST("/api/permission/adddelegation/")
    suspend fun postDelegation(
        @Header("Authorization") access: String,
        @Body postDelegation: PostDelegation
    ): Response<ResponsePostDelegation>


    @Headers("Content-Type: application/json")
    @POST("/api/permission/getpermission/")
    suspend fun getPermission(
        @Header("Authorization") access: String,
        @Body permissionPost: PermissionPost
    ): Response<ResponsePermission>

    @Headers("Content-Type: application/json")
    @POST("/api/permission/addsuspension/")
    suspend fun postSuspension(
        @Header("Authorization") access: String,
        @Body postSuspension: PostSuspension
    ): Response<ResponsePostDelegation>


    @Headers("Content-Type: application/json")
    @POST("/api/user/editprofile/")
    suspend fun updateUserProfile(
        @Header("Authorization") access: String,
        @Body postEditProfile: PostEditProfile
    ): Response<ResponseEditProfile>

    @Headers("Content-Type: application/json")
    @GET("/api/user/profile/")
    suspend fun getUserProfileData(
        @Header("Authorization") access: String
    ): Response<UserProfileDetail>


    @Headers("Content-Type: application/json")
    @POST("/api/user/token/verify/")
    suspend fun postTokenVerify(
        @Body postTokenAccess: PostTokenAccess
    ): Response<ResponseTokenAccess>

    @Headers("Content-Type: application/json")
    @POST("/api/user/token/refresh/")
    suspend fun postTokenRefresh(
        @Body postTokenRefresh: PostTokenRefresh
    ): Response<ResponseTokenRefresh>


    @Headers("Content-Type: application/json")
    @POST("/api/student/getfulldetails/")
    suspend fun getFullDetails(
        @Header("Authorization") access: String,
        @Body getStudentPost: GetStudentPost
    ): Response<ResposneFullDetails>



}









//    @POST("/api/user/register/")
//    suspend fun tempRegister(
//       email: String,
//       name: String,
//       username: String,
//       phoneno: Long,
//       roomno: String
//    ):Response<TempRegister>