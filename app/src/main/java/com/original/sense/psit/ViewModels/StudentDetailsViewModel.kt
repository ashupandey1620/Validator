package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.ResponseModel.ResposneFullDetails
import com.original.sense.psit.model.ResponseModel.TempRegister
import com.original.sense.psit.screens.access
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentDetailsViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel() {

    private val _studentDetails = MutableLiveData<ResposneFullDetails?>()
    val studentDetails: LiveData<ResposneFullDetails?> = _studentDetails

    fun getStudentDetails(
        accessToken:String,
        getStudentPost: GetStudentPost ,
    ) {
        viewModelScope.launch {
            val result = repository.getFullDetails(accessToken,getStudentPost)
            Log.e("okhttp","$result")
            _studentDetails.postValue(result)
        }
    }
}