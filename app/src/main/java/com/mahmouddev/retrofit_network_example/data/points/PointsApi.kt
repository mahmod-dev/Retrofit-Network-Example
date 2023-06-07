package com.mahmouddev.retrofit_network_example.data.points


import com.mahmouddev.retrofit_network_example.data.response.GeneralResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsApi {

    @GET("home")
    suspend fun getPoints(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?
    ): Response<GeneralResponse<PointResponse>>

}
