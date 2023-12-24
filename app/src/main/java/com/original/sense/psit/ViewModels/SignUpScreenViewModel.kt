package com.original.sense.psit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.model.ResponseModel.TempRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel(){

    private val _registrationStatus = MutableLiveData<TempRegister?>()
    val registrationStatus: LiveData<TempRegister?> = _registrationStatus


    fun registerUser(tempRegisterPost: TempRegisterPost) {
        viewModelScope.launch {
            val result = repository.registerUser(tempRegisterPost)
            _registrationStatus.postValue(result)
        }
    }
}