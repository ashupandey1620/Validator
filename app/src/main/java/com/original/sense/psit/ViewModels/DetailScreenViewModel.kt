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
import com.original.sense.psit.model.ResponseModel.ResponseDataPermission
import com.original.sense.psit.model.ResponseModel.ResponsePermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class DateItem(val date: String, val day: String)

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
): ViewModel() {

    private val _assignedList = MutableLiveData<List<AssignedLectureModel>>()
    val assignedList: LiveData<List<AssignedLectureModel>> = _assignedList

    private val _getPermission = MutableLiveData<ResponsePermission?>()
    val getPermission : LiveData<ResponsePermission?> = _getPermission

    private val daysListLiveData = MutableLiveData<List<Boolean>>()
    val daysListData : LiveData<List<Boolean>> = daysListLiveData


    private val suspensionList = mutableListOf<ResponseDataPermission>()
    private val delegationList = mutableListOf<ResponseDataPermission>()


//    private val suspensionListLive = MutableLiveData<List<ResponseDataPermission>>()
//    val getSuspensionListLive : LiveData<List<ResponseDataPermission>> = suspensionListLive



    @RequiresApi(Build.VERSION_CODES.O)
    fun getPermission(access:String , permissionPost: PermissionPost) {
        viewModelScope.launch {
            val result = repository.getPermission(access,permissionPost)


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

            suspensionDateList(suspensionList)

//            suspensionListLive.postValue(suspensionList)
            _getPermission.postValue(result)

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateAssignedList(dateItem: DateItem)
    {
        val yearMonth = YearMonth.now()
        val selectedDate = yearMonth.atDay(dateItem.date.toInt())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = selectedDate.format(formatter)

        val updatedAssignedList = mutableListOf<AssignedLectureModel>()

        delegationList.forEach { delegationItem ->
            if (delegationItem.to_date == selectedDate.toString()) {
                val assignedLectureModel = AssignedLectureModel(
                    delegationItem.permission_id,
                    delegationItem.lectures ?: emptyList(), // Consider null case
                    delegationItem.assigned_by ?: "" // Consider null case
                )
                updatedAssignedList.add(assignedLectureModel)
            }
        }

        _assignedList.postValue(updatedAssignedList)
        Log.d("AssignedList", "$updatedAssignedList")
    }


    // Function to convert date string to LocalDate
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToDate(dateString: String?): LocalDate? {
        return dateString?.let {
            LocalDate.parse(it.substring(0, 10), DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun suspensionDateList(suspensionList: MutableList<ResponseDataPermission>) {
        val daysList = MutableList(7) { false }

    // Get today's date
        val today = LocalDate.now()

        Log.d("SuspensionDAYS fromDate", suspensionList.size.toString())

    // Iterate through the suspensionList
        suspensionList.forEach { responseData ->
            // Retrieve from_date and to_date from responseData
            val fromDate = stringToDate(responseData.from_date)
            val toDate = stringToDate(responseData.to_date)

            Log.d("SuspensionDAYS fromDate",fromDate.toString())

            Log.d("SuspensionDAYS ToDate",toDate.toString())

            // Iterate over the next 6 days including today
            for (i in 0 until 7) {
                val currentDate = today.plusDays(i.toLong())


                val withinRange = (fromDate == null || currentDate.isEqual(fromDate) || currentDate.isAfter(fromDate)) &&
                        (toDate == null || currentDate.isEqual(toDate) || currentDate.isBefore(toDate.plusDays(1)))

                // Update the daysList based on the condition
                if (withinRange) {
                    daysList[i] = true
                }
            }
        }

        Log.d("SuspensionDAYS View mOdel",daysList.toString())

        daysListLiveData.postValue(daysList)
    }

}