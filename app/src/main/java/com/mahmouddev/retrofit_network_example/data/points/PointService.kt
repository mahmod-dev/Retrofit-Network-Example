package com.mahmouddev.retrofit_network_example.data.points

import com.mahmouddev.retrofit_network_example.data.response.GeneralResponse
import com.mahmouddev.retrofit_network_example.network.BaseDataSource
import com.mahmouddev.retrofit_network_example.network.NetworkResult
import javax.inject.Inject

class PointService @Inject constructor(
    private val pointsApi: PointsApi,
) : BaseDataSource() {

    suspend fun getPoints(
        latitude: Double?,
        longitude: Double?
    ): NetworkResult<GeneralResponse<PointResponse>> =
        getApiResult { pointsApi.getPoints(latitude, longitude) }

}
