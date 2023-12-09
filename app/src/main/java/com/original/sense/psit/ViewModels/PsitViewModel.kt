package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.LoginPost
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.ResponseModel.LoginResponse
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

    fun registerUser(tempRegisterPost: TempRegisterPost) {
        viewModelScope.launch {
            val result = repository.registerUser(tempRegisterPost)
            _registrationStatus.postValue(result)
        }
    }

    fun loginUser(loginPost: LoginPost) {
        viewModelScope.launch {
            val result = repository.loginUser(loginPost)
            if (result!!.error) {

                val accessToken = result.responseData.token.access
                val refreshToken = result.responseData.token.refresh

                // Save tokens to DataStore upon successful login
                tokenStore.saveTokens(accessToken, refreshToken)
            }

            _loginStatus.postValue(result)

        }
    }


}
