package com.original.sense.psit.Tokens

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TokenStore @Inject constructor(private val dataStore: DataStore<Preferences>){


    companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        val USER_NAME = stringPreferencesKey("username")
        val E_MAIL = stringPreferencesKey("email")
        val NAME = stringPreferencesKey("name")
        val PHONE_NO = stringPreferencesKey("phone_no")
        val ROOM_NO = stringPreferencesKey("room_no")

    }

    val accessTokenFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY] ?: ""
    }

    val refreshTokenFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[REFRESH_TOKEN_KEY] ?: ""
    }

    val userNameFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_NAME] ?: ""
    }

    val emailFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[E_MAIL] ?: ""
    }

    val nameFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[NAME] ?: ""
    }

    val phoneNoFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PHONE_NO] ?: ""
    }


    val roomNoFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ROOM_NO] ?: ""
    }


    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    suspend fun saveData(userName: String, email: String,name: String, phoneNo: String, roomNo: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME]   =    userName
            preferences[E_MAIL]      =    email
            preferences[NAME]        =    name
            preferences[PHONE_NO]    =    phoneNo
            preferences[ROOM_NO]     =    roomNo
        }
    }
}
