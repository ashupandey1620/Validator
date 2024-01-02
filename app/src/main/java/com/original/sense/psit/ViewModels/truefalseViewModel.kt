package com.original.sense.psit.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.ResponseModel.ResposneFullDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class truefalseViewModel@Inject constructor(private val tokenStore: TokenStore):ViewModel() {
    var show = mutableStateOf(false)
    fun updateShow(newshow:Boolean){
        show.value = newshow
    }
}