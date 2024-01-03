package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.ResponseModel.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel() {


    private val _loginStatus = MutableLiveData<LoginResponse?>()
    val loginStatus: LiveData<LoginResponse?> = _loginStatus


    fun loginUser(loginPost: LoginPost) {
        viewModelScope.launch {
            val result = repository.loginUser(loginPost)
            if(result!=null) {
                if (!result!!.error!!) {

                    val accessToken = result.responseData?.token?.access
                    val refreshToken = result.responseData?.token?.refresh

                    if (accessToken != null) {
                        Log.d("Repository - access token" , accessToken)
                    }
                    if (refreshToken != null) {
                        Log.d("Repository - refresh token" , refreshToken)
                    }

                    // Save tokens to DataStore upon successful login
                    if (refreshToken != null) {
                        if (accessToken != null) {
                            tokenStore.saveTokens(accessToken , refreshToken)
                        }
                    }

                }

                _loginStatus.postValue(result)
            }


        }
    }

//
//    private val _loginStatus = MutableLiveData<Resource<LoginResponse2>>()
//    val loginStatus: LiveData<Resource<LoginResponse2>> = _loginStatus
//
//    fun loginUser(request: LoginRequest) {
//        viewModelScope.launch {
//            _loginStatus.value = Resource.Loading
//            val result = repository.loginUser(request)
//            _loginStatus.value = result
//        }
//    }
}