package com.original.sense.psit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.model.ResponseModel.ResponseEditProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel  @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
) : ViewModel()
{
    private val _updateUserProfile = MutableLiveData<ResponseEditProfile?>()
    val updateUserProfile : LiveData<ResponseEditProfile?> = _updateUserProfile



    fun updateUserProfile(access: String,postEditProfile: PostEditProfile) {
        viewModelScope.launch {
            val result = repository.updateUserProfile(access,postEditProfile)
            _updateUserProfile.postValue(result)

            if (result != null && !result.error) {
                delay(2000)
                _updateUserProfile.value = null
            }
        }
    }

}