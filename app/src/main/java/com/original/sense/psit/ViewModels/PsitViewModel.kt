package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.ResponseModel.ChangePasswordResponse
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.model.ResponseModel.LoginResponse
import com.original.sense.psit.model.ResponseModel.LogoutResponse
import com.original.sense.psit.model.ResponseModel.TempRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PsitViewModel @Inject constructor(private val repository: PsitRepository, private val tokenStore: TokenStore) : ViewModel() {



    private val _registrationStatus = MutableLiveData<TempRegister?>()
    val registrationStatus: LiveData<TempRegister?> = _registrationStatus

    private val _loginStatus = MutableLiveData<LoginResponse?>()
    val loginStatus: LiveData<LoginResponse?> = _loginStatus

    private val _changePassword = MutableLiveData<ChangePasswordResponse?>()
    val changePassword: LiveData<ChangePasswordResponse?> = _changePassword

    private val _getStudent = MutableLiveData<GetStudentResponse?>()
    val getStudent: LiveData<GetStudentResponse?> = _getStudent


    private val _logoutResponse = MutableLiveData<LogoutResponse?>()
    val logoutResponse : LiveData<LogoutResponse?> = _logoutResponse

    fun registerUser(tempRegisterPost: TempRegisterPost) {
        viewModelScope.launch {
            val result = repository.registerUser(tempRegisterPost)
            _registrationStatus.postValue(result)
        }
    }

    fun loginUser(loginPost: LoginPost) {
        viewModelScope.launch {
            val result = repository.loginUser(loginPost)
            if (!result!!.error) {

                val accessToken = result.responseData.token.access
                val refreshToken = result.responseData.token.refresh

                Log.d("Repository - access token", accessToken)
                Log.d("Repository - refresh token", refreshToken)

                // Save tokens to DataStore upon successful login
                tokenStore.saveTokens(accessToken, refreshToken)
            }

            _loginStatus.postValue(result)

        }
    }

    fun changePassword(accessToken:String,changePasswordPost: ChangePasswordPost) {
        viewModelScope.launch {
            val result = repository.changePassword(accessToken,changePasswordPost)
            _changePassword.postValue(result)
        }
    }


    fun getStudent(accessToken:String , getStudentPost: GetStudentPost) {
        viewModelScope.launch {
            val result = repository.getStudent(accessToken,getStudentPost)
            _getStudent.postValue(result)
        }
    }

    fun logOut(refreshToken:String) {
        viewModelScope.launch {
            val result = repository.logOut(refreshToken)
            _logoutResponse.postValue(result)
        }
    }



}
