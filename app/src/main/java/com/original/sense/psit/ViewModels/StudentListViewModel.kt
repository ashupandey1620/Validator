package com.original.sense.psit.ViewModels


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.original.sense.psit.model.PersonModel
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.model.ResponseModel.GetStudentResponse
import com.original.sense.psit.screens.access
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class StudentListViewModel @Inject constructor(private val psitViewModel: PsitViewModel) : ViewModel() {
    val studentList = mutableStateListOf<PersonModel>()

    val studentList2 = mutableStateListOf<PersonModel>()

    fun addStudent(rollNo: Long) {
        val postStudent = GetStudentPost(rollNo)
        psitViewModel.getStudent2(access,postStudent,this)
    }
    fun updateStudentList(name:String, rollNo: Long) {
        val model = PersonModel(name,rollNo)
        if (!(model.name=="Network Error"||model.name=="Incorrect Student Id"||model.name=="Internal Server Error")) {
            studentList2.add(model)
        }
        studentList.add(model)
    }

    fun removeStudent(student: SnapshotStateList<PersonModel>) {
        studentList.removeAll(student)
    }
}
