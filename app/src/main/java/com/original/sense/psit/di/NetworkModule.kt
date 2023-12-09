package com.original.sense.psit.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.original.sense.psit.API.PsitApi
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
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // Set your desired log level

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            // Add any other OkHttpClient configurations if needed
            .build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://18.61.72.79/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient())
            .build()
    }

    //https://special-space-goggles-x5w5pwx6gpphv6wq.github.dev/
    //https://animated-capybara-45j74vw7w4gc7r5g-8000.app.github.dev/
    //http://18.61.72.79/

    @Singleton
    @Provides
    fun providesPsitAPI(retrofit: Retrofit) : PsitApi{
        return retrofit.create(PsitApi::class.java)
    }



    @Module
    @InstallIn(SingletonComponent::class) // Use appropriate component
    object DataStoreModule {

        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create { context.preferencesDataStoreFile("name") }
        }
    }
}



