package com.original.sense.psit.di

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.ViewModels.StudentListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


//        val errorInterceptor = Interceptor { chain ->
//            val request = chain.request()
//            val response = chain.proceed(request)
//
//            if (!response.isSuccessful) {
//                val errorBody = response.body
//                // Handle error body parsing here
//                // For example, log the error body
//                errorBody?.let {
//                    val errorBodyString = it.string()
//                    Log.e("Error Body", errorBodyString)
//                }
//            }
//
//            response // Return the response as is
//        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor) // Add the error interceptor
            .build()
    }
    //http://18.61.72.79/
    //https://api-prod.attendify.atishir.in
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://18.61.72.79/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }
    @Singleton
    @Provides
    fun providesPsitAPI(retrofit: Retrofit) : PsitApi{
        return retrofit.create(PsitApi::class.java)
    }
    @Module
    @InstallIn(SingletonComponent::class)
    object DataStoreModule {

        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create { context.preferencesDataStoreFile("name") }
        }
    }

}



