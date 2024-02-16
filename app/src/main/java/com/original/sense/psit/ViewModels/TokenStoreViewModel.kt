package com.original.sense.psit.ViewModels

import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Tokens.TokenStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TokenStoreViewModel @Inject constructor(private val tokenStore: TokenStore) : ViewModel() {
    val accessTokenFlow: Flow<String?> = tokenStore.accessTokenFlow
    val refreshTokenFlow: Flow<String?> = tokenStore.refreshTokenFlow

    val userNameFlow: Flow<String?> = tokenStore.userNameFlow

    val emailFlow: Flow<String?> = tokenStore.emailFlow

    val nameFlow: Flow<String?> = tokenStore.nameFlow

    val phoneNoFlow: Flow<String?> = tokenStore.phoneNoFlow

    val roomNoFlow: Flow<String?> = tokenStore.roomNoFlow



    val readAccess : StateFlow<String?> = tokenStore.accessTokenFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val readRefresh : StateFlow<String?> = tokenStore.refreshTokenFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val readUserName : StateFlow<String?> = tokenStore.userNameFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val readEmail : StateFlow<String?> = tokenStore.emailFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )


    val readName : StateFlow<String?> = tokenStore.nameFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )


    val readPhoneNo : StateFlow<String?> = tokenStore.phoneNoFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )


    val readRoomNo : StateFlow<String?> = tokenStore.roomNoFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )


    suspend fun updateProfile(phoneNo: String, roomNo: String) {
        tokenStore.updateProfile(phoneNo,roomNo)
    }



}
