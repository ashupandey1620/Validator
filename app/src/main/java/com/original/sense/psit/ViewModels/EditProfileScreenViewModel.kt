package com.original.sense.psit.ViewModels

import androidx.lifecycle.ViewModel
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import javax.inject.Inject

class EditProfileScreenViewModel @Inject constructor(
    private val repository: PsitRepository ,
    private val tokenStore: TokenStore
)
    : ViewModel() {

}