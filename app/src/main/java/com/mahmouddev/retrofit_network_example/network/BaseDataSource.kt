package com.mahmouddev.retrofit_network_example.network

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {

    suspend fun <T> getApiResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return NetworkResult.Success(body)
            }
            return NetworkResult.Error(code = response.code(), message = response.message())

        } catch (e: IOException) {
            return NetworkResult.Exception(e)
        } catch (e: HttpException) {
            return NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Exception) {
            return NetworkResult.Exception(e)
        }
    }


}

