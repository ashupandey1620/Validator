package com.original.sense.psit.ViewModels


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.original.sense.psit.model.PersonModel

class StudentListViewModel : ViewModel() {
    val studentList = mutableStateListOf<PersonModel>()

    fun addStudent(student: PersonModel) {
        studentList.add(student)
    }

    fun removeStudent(student: SnapshotStateList<PersonModel>) {
        studentList.removeAll(student)
    }
}
