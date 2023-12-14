package com.original.sense.psit.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val psitViewModel: PsitViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentListViewModel(psitViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}