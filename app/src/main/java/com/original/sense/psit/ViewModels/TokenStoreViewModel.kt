package com.original.sense.psit.ViewModels

import androidx.lifecycle.ViewModel
import com.original.sense.psit.Tokens.TokenStore
import kotlinx.coroutines.flow.Flow


class TokenStoreViewModel(private val tokenStore: TokenStore) : ViewModel() {
    val accessTokenFlow: Flow<String?> = tokenStore.accessTokenFlow
    val refreshTokenFlow: Flow<String?> = tokenStore.refreshTokenFlow
}
