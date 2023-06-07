package com.mahmouddev.retrofit_network_example.di

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.mahmouddev.retrofit_network_example.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    private fun getBaseUrl() = "http://46.101.191.31/api/"

    private fun getCache(@ApplicationContext appContext: Context) =
        Cache(appContext.cacheDir, CACHE_SIZE.toLong())

    @Singleton
    @Provides
    fun providesRetrofitClient(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().setPrettyPrinting().create()
            )
        )
        .client(client)
        .build()

    @Provides
    fun providesHttpClient(
        @ApplicationContext appContext: Context,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(getLogger())
            .cache(getCache(appContext))
            .build()



    private fun getLogger() = HttpLoggingInterceptor { message ->
        if (!message.contains("�")) {
            Log.i("RetrofitModule", message)
        }
    }.apply {
        level = when (BuildConfig.DEBUG) {
            true  -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }    }


    companion object {
        //10MB
        const val CACHE_SIZE = 10 * 1024 * 1024
    }
}
