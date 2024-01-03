package com.original.sense.psit.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.di.NetworkResult
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
import com.original.sense.psit.model.RefreshTokenRequest
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
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
import org.json.JSONObject
import javax.inject.Inject

class PsitRepository @Inject constructor(private val psitApi : PsitApi){

    private val _loginResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val userResponseLiveData : LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponseLiveData

    suspend fun loginUser(loginPost: LoginPost): LoginResponse?  {
        return try {
            val response = psitApi.login(loginPost)
            if (response.isSuccessful && response.body()!=null) {
                _loginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody()!=null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _loginResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            response.body()
        } catch (e: Exception) {
             _loginResponseLiveData.postValue(NetworkResult.Error("Wrong Credentials"))
            null
        }
    }

    private val _registerResponseLiveData = MutableLiveData<NetworkResult<TempRegister>>()
    val registerResponseLiveData : LiveData<NetworkResult<TempRegister>>
        get() = _registerResponseLiveData

    suspend fun registerUser(tempRegisterPost: TempRegisterPost) {
       try {
           val response = psitApi.tempRegister(tempRegisterPost)
           if (response.isSuccessful && response.body()!=null) {
               _registerResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
           } else if (response.errorBody()!=null) {
               val errorObj = JSONObject(response.errorBody()!!.charStream().readText())

               val emailErrors = errorObj.getJSONObject("message").getJSONArray("email")
               val usernameErrors = errorObj.getJSONObject("message").getJSONArray("username")
               val phoneErrors = errorObj.getJSONObject("message").getJSONArray("phoneNo")
               val nfeErrors = errorObj.getJSONObject("message").getJSONArray("non_field_errors")

               Log.d("Register-errors",emailErrors.length().toString())
               Log.d("Register-username",emailErrors.length().toString())
               Log.d("Register-phone",emailErrors.length().toString())
               Log.d("Register-nfe",emailErrors.length().toString())

               if (emailErrors.length()!=0&&emailErrors[0]!=null) {
                   Log.d("Register-errors",emailErrors[0].toString())
                   _registerResponseLiveData.postValue(NetworkResult.Error(emailErrors[0].toString()))
               }
               else if (usernameErrors.length()!=0 && usernameErrors[0]!=null) {
                   Log.d("Register-errors",usernameErrors[0].toString())
                   _registerResponseLiveData.postValue(NetworkResult.Error(usernameErrors[0].toString()))
               }
               else if (phoneErrors.length()!=0 && phoneErrors[0]!=null) {
                   Log.d("Register-errors",phoneErrors[0].toString())
                   _registerResponseLiveData.postValue(NetworkResult.Error(phoneErrors[0].toString()))
               }
               else if (nfeErrors.length()!=0 && nfeErrors[0]!=null) {
                   Log.d("Register-errors",nfeErrors[0].toString())
                   _registerResponseLiveData.postValue(NetworkResult.Error(nfeErrors[0].toString()))
               }
               else {
                   _registerResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong2"))
               }
           }
        } catch (e: Exception) {
           _registerResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }


//    suspend fun loginUser(request: LoginRequest): Resource<LoginResponse2> {
//        return try {
//            val response = psitApi.login(request)
//            if (response.errorBody != null) {
//                Resource.Error(response.errorBody)
//            } else {
//                response.data?.let {
//                    Resource.Success(it)
//                } ?: Resource.Error(null)
//            }
//        } catch (e: Exception) {
//            Log.e("fatal", "Exception: ${e.message}")
//            Resource.Error(null)
//        }
//    }





//    suspend fun loginUser(loginPost: LoginPost): LoginResponse? {
//        return try {
//            val response = psitApi.login(loginPost)
//            if (response.isSuccessful) {
//                val loginResponse = response.body()
//                /*
//                Log.d("fatal", "Response code: ${response.code()}")
//                Log.d("fatal", "Response body: ${loginResponse.toString()}")
//                */
//
//                loginResponse
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            Log.e("fatal", "Exception: ${e.message}")
//            null
//        }
//    }




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

            response.body()

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