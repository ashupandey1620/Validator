package com.original.sense.psit.Repository

import android.util.Log
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.TempRegister
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


}