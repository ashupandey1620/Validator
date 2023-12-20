package com.original.sense.psit.ViewModels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.AssignedLectureModel
import com.original.sense.psit.model.PostModel.PermissionPost
import com.original.sense.psit.model.PostModel.getDelegationPost
import com.original.sense.psit.model.ResponseModel.ResponseDataPermission
import com.original.sense.psit.model.ResponseModel.ResponsePermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class DateItem(val date: String, val day: String)

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel() {


    val assignedList = mutableListOf<AssignedLectureModel>().apply {
        add(AssignedLectureModel("8JEI9JDOESE0WR", listOf(1,2,3,4) ,"Raghav Tiwari"))
        add(AssignedLectureModel("9DDJFNEIFDKDDJ", listOf(5,7,8) ,"Ashutosh Pandey"))
    }


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

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateNextSevenDays(): List<DateItem> {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd")

        return generateSequence(today) { it.plusDays(1) }
            .take(7)
            .map {
                DateItem(
                    date = it.format(formatter),
                    day = it.dayOfWeek.toString().lowercase().capitalize()
                )
            }
            .toList()
    }

}