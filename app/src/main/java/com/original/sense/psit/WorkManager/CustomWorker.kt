package com.original.sense.psit.WorkManager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.Repository.PsitRepository
import com.original.sense.psit.Tokens.TokenStore
import com.original.sense.psit.model.PostModel.PostTokenRefresh
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull

@HiltWorker
class CustomWorker @AssistedInject constructor(
    private val tokenStore: TokenStore ,
    @Assisted private val api: PsitApi ,
    @Assisted context: Context ,
    @Assisted workerParameters: WorkerParameters)
    : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {

        val accessToken = tokenStore.accessTokenFlow.firstOrNull()
        val refreshToken = tokenStore.refreshTokenFlow.firstOrNull()
        Log.d("WorkerClass Access TOken",accessToken.toString())
        Log.d("WorkerClass Refresh TOken",refreshToken.toString())

        try {
            val postTokenRefresh = PostTokenRefresh(refreshToken.toString())

            val response = api.postTokenRefresh(postTokenRefresh)

            Log.d("WorkerClass","No error making post class")
            return if(response.isSuccessful){
                tokenStore.saveTokens(response.body()!!.access, refreshToken.toString())
                Log.d("WorkerClass", response.body()!!.access)
                Result.success()
            } else{
                Log.d("WorkerClass - Failure","Response is not SuccessFul")
                Result.retry()
            }

        } catch (e:Exception){
            Log.d("WorkerClass - Exception",e.toString())
            return Result.failure()
        }

    }

}