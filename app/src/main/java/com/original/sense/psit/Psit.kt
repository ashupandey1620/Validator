package com.original.sense.psit

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.WorkManager.CustomWorker

import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Psit : Application(), Configuration.Provider {

    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory
    override val workManagerConfiguration: Configuration
        get() =  Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(customWorkerFactory)
            .build()

}


class CustomWorkerFactory @Inject constructor(private val tokenStore: TokenStore,private val api:PsitApi): WorkerFactory(){
    override fun createWorker(
        appContext: Context ,
        workerClassName: String ,
        workerParameters: WorkerParameters
    ): ListenableWorker? = CustomWorker( tokenStore,api,appContext, workerParameters)
}

