package com.original.sense.psit.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.PermissionPost
import com.original.sense.psit.model.PostModel.getDelegationPost
import com.original.sense.psit.model.ResponseModel.ResponseDataPermission
import com.original.sense.psit.model.ResponseModel.ResponsePermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel() {


    private val _getPermission = MutableLiveData<ResponsePermission?>()
    val getPermission : LiveData<ResponsePermission?> = _getPermission


    private val suspensionList = mutableListOf<ResponseDataPermission>()
    private val delegationList = mutableListOf<ResponseDataPermission>()


    fun getPermission(access:String,permissionPost: PermissionPost) {
        viewModelScope.launch {
            val result = repository.getPermission(access,permissionPost)
            _getPermission.postValue(result)

            result?.responseData?.let { responseData ->
                suspensionList.clear()
                delegationList.clear()

                responseData.forEach { data ->
                    when (data.type) {
                        "suspension" -> suspensionList.add(data)
                        "delegation" -> delegationList.add(data)
                    }
                }
            }

            Log.d("Suspension","$suspensionList")
            Log.d("Delegation","$delegationList")

        }
    }

}