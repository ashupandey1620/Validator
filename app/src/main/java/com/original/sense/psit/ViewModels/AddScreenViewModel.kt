package com.original.sense.psit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.PostDelegation
import com.original.sense.psit.model.PostModel.PostSuspension
import com.original.sense.psit.model.ResponseModel.ResponsePostDelegation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddScreenViewModel  @Inject constructor(
    private val repository: PsitRepository,
    private val tokenStore: TokenStore)
    :ViewModel() {

    private val _postDelegation = MutableLiveData<ResponsePostDelegation?>()
    val postDelegationResponse : LiveData<ResponsePostDelegation?> = _postDelegation


    private val _postSuspension = MutableLiveData<ResponsePostDelegation?>()
    val postSuspension : LiveData<ResponsePostDelegation?> = _postSuspension



    fun postSuspension(access:String,postSuspension: PostSuspension) {
        viewModelScope.launch {
            val result = repository.postSuspension(access,postSuspension)
            _postSuspension.postValue(result)
        }
    }


    fun postDelegation(access:String,postDelegation: PostDelegation) {
        viewModelScope.launch {
            val result = repository.postDelegation(access,postDelegation)
            _postDelegation.postValue(result)
        }
    }

}