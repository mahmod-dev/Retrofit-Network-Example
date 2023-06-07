package com.mahmouddev.retrofit_network_example.di

import com.mahmouddev.retrofit_network_example.data.points.PointsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun providesLoginApi(retrofit: Retrofit): PointsApi =
        retrofit.create(PointsApi::class.java)

}
