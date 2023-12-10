package com.original.sense.psit.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.original.sense.psit.Tokens.TokenStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TokenStoreViewModel @Inject constructor(private val tokenStore: TokenStore) : ViewModel() {
    val accessTokenFlow: Flow<String?> = tokenStore.accessTokenFlow
    val refreshTokenFlow: Flow<String?> = tokenStore.refreshTokenFlow

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


}
