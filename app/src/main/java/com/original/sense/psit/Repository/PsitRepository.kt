package com.original.sense.psit.Repository

import android.util.Log
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetPwdPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.PostDelegation
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.model.PostModel.PostSuspension
import com.original.sense.psit.model.PostModel.PostTokenAccess
import com.original.sense.psit.model.PostModel.PostTokenRefresh
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.PostModel.getDelegationPost
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetPwdResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.LogoutResponse
import com.original.sense.psit.model.ResponseModel.ResponseEditProfile
import com.original.sense.psit.model.ResponseModel.ResponseGetDelegation
import com.original.sense.psit.model.ResponseModel.ResponsePostDelegation
import com.original.sense.psit.model.ResponseModel.ResponseTokenAccess
import com.original.sense.psit.model.ResponseModel.ResponseTokenRefresh
import com.original.sense.psit.model.ResponseModel.TempRegister
import com.original.sense.psit.model.ResponseModel.UserProfileDetail
import okhttp3.MultipartBody
import javax.inject.Inject

class PsitRepository @Inject constructor(private val psitApi : PsitApi){

    suspend fun registerUser(tempRegisterPost: TempRegisterPost): TempRegister? {
        return try {
            val response = psitApi.tempRegister(tempRegisterPost)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("KodanKing-error",e.toString())
            null
        }
    }


    suspend fun loginUser(loginPost: LoginPost): LoginResponse? {
        return try {
            val response = psitApi.login(loginPost)

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            null
        }
    }

    suspend fun changePassword(accessToken:String,changePasswordPost: ChangePasswordPost): ChangePasswordResponse? {
        return try {
            val response = psitApi.changePassword("Bearer $accessToken",changePasswordPost)

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            null
        }
    }


    suspend fun getStudent(accessToken:String , getStudentPost: GetStudentPost): GetStudentResponse? {
        return try {
            val response = psitApi.getStudent("Bearer $accessToken",getStudentPost)

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            null
        }
    }

    suspend fun getPwdChip(accessToken:String , getPwdPost: GetPwdPost): GetPwdResponse? {
        return try {
            val response = psitApi.getChipPwd("Bearer $accessToken",getPwdPost)

            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            null
        }
    }

    suspend fun logOut(refreshToken:String): LogoutResponse? {
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("refresh_token", refreshToken)
            .build()

        return try {
            val response = psitApi.logOut(body)
            if (response.isSuccessful) {
                response.body()
            } else {
                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            null
        }
    }


    suspend fun postDelegation(access:String,postDelegation: PostDelegation): ResponsePostDelegation? {
        return try {
            val response = psitApi.postDelegation(access,postDelegation)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("SHINCHAN - POST DELEGATION",e.toString())
            null
        }
    }


    suspend fun postSuspension(access:String,postSuspension: PostSuspension): ResponsePostDelegation? {
        return try {
            val response = psitApi.postSuspension(access,postSuspension)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("SHINCHAN - POST SUSPENSION",e.toString())
            null
        }
    }



    suspend fun getDelegation(access: String,getDelegationPost: getDelegationPost): ResponseGetDelegation? {
        return try {
            val response = psitApi.getDelegation(access,getDelegationPost)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("SHINCHAN - GET DELEGATION",e.toString())
            null
        }
    }



    suspend fun updateUserProfile(access: String,postEditProfile: PostEditProfile): ResponseEditProfile? {
        return try {
            val response = psitApi.updateUserProfile(access,postEditProfile)


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
            val response = psitApi.getUserProfileData(access)


            if (response.isSuccessful) {
                response.body()
            } else {

                // Handle unsuccessful response (maybe return null or throw an exception)
                null
            }
        } catch (e: Exception) {
            // Handle exceptions here
            Log.d("DINANATH - get User profile Data",e.toString())
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

    suspend fun getSuspension(access: String,getDelegationPost: getDelegationPost): ResponseGetDelegation? {
        return try {
            val response = psitApi.getDelegation(access,getDelegationPost)


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