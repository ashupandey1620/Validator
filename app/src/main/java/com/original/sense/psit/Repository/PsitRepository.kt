package com.original.sense.psit.Repository

import android.util.Log
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.PermissionPost
import com.original.sense.psit.model.PostModel.PostDelegation
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.model.PostModel.PostSuspension
import com.original.sense.psit.model.PostModel.PostTokenAccess
import com.original.sense.psit.model.PostModel.PostTokenRefresh
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.PostModel.getDelegationPost
import com.original.sense.psit.model.RefreshTokenRequest
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.LogoutResponse
import com.original.sense.psit.model.ResponseModel.ResponseEditProfile
import com.original.sense.psit.model.ResponseModel.ResponseGetDelegation
import com.original.sense.psit.model.ResponseModel.ResponsePermission
import com.original.sense.psit.model.ResponseModel.ResponsePostDelegation
import com.original.sense.psit.model.ResponseModel.ResponseTokenAccess
import com.original.sense.psit.model.ResponseModel.ResponseTokenRefresh
import com.original.sense.psit.model.ResponseModel.ResposneFullDetails
import com.original.sense.psit.model.ResponseModel.TempRegister
import com.original.sense.psit.model.ResponseModel.UserProfileDetail
import javax.inject.Inject

class PsitRepository @Inject constructor(private val psitApi : PsitApi){

    suspend fun registerUser(tempRegisterPost: TempRegisterPost): TempRegister? {
        return try {
            val response = psitApi.tempRegister(tempRegisterPost)
            response.body()
        } catch (e: Exception) {
            Log.d("KodanKing-error",e.toString())
            null
        }
    }


    suspend fun loginUser(loginPost: LoginPost): LoginResponse? {
        return try {
            val response = psitApi.login(loginPost)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                Log.d("fatal", "Response code: ${response.code()}")
                Log.d("fatal", "Response body: ${loginResponse.toString()}")

                loginResponse
            } else {

                val error = response.errorBody()
                Log.e("fatal", "Error: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("fatal", "Exception: ${e.message}")
            null
        }
    }

    suspend fun changePassword(accessToken:String,changePasswordPost: ChangePasswordPost): ChangePasswordResponse? {
        return try {
            val response = psitApi.changePassword("Bearer $accessToken",changePasswordPost)
            response.body()
        } catch (e: Exception) {
            null
        }
    }


    suspend fun getStudent(accessToken:String , getStudentPost: GetStudentPost): GetStudentResponse? {
        return try {
            val response = psitApi.getStudent("Bearer $accessToken",getStudentPost)
            response.body()
        } catch (e: Exception) {
         null
        }
    }

    suspend fun getFullDetails(accessToken:String , getStudentPost: GetStudentPost): ResposneFullDetails? {
        return try {
            val response = psitApi.getFullDetails("Bearer $accessToken",getStudentPost)
            response.body()
        } catch (e: Exception) {
            null
        }
    }


    suspend fun logOut(refreshToken:String): LogoutResponse? {
        val request = RefreshTokenRequest(refreshToken)

        return try {
            val response = psitApi.logOut(request)
            Log.d("okhttp",response.toString())
            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                Log.d("okhttp - error",response.toString())
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("okhttp - exception",e.toString())
            null
        }
    }


    suspend fun postDelegation(access:String,postDelegation: PostDelegation): ResponsePostDelegation? {
        return try {
            val response = psitApi.postDelegation("Bearer $access",postDelegation)
            response.body()
        } catch (e: Exception) {
            null
        }
    }


    suspend fun postSuspension(access:String,postSuspension: PostSuspension): ResponsePostDelegation? {
        return try {
            val response = psitApi.postSuspension("Bearer $access",postSuspension)
            response.body()
        } catch (e: Exception) {
            null
        }
    }



    suspend fun getPermission(access: String,permissionPost: PermissionPost): ResponsePermission? {
        return try {
            val response = psitApi.getPermission("Bearer $access",permissionPost)
            response.body()

        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("Oggy",e.toString())
            null
        }
    }



    suspend fun updateUserProfile(access: String,postEditProfile: PostEditProfile): ResponseEditProfile? {
        return try {
            val response = psitApi.updateUserProfile("Bearer $access",postEditProfile)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("DINANATH - UpdateUserProfile",e.toString())
            null
        }
    }



    suspend fun getUserProfileData(access: String): UserProfileDetail? {
        return try {
            val response = psitApi.getUserProfileData("Bearer $access")
            Log.d("DINANATH","${response.body()}")
            response.body()
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("DINANATH",e.toString())
            null
        }
    }


    suspend fun postTokenRefresh(postTokenRefresh: PostTokenRefresh): ResponseTokenRefresh? {
        return try {
            val response = psitApi.postTokenRefresh(postTokenRefresh)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("DINANATH - Post Token Refresh",e.toString())
            null
        }
    }


    suspend fun postTokenVerify(postTokenAccess: PostTokenAccess): ResponseTokenAccess? {
        return try {
            val response = psitApi.postTokenVerify(postTokenAccess)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("SHINCHAN - GET SUSPENSION",e.toString())
            null
        }
    }



}