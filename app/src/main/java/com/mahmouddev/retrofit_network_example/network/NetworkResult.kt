package com.mahmouddev.retrofit_network_example.network

sealed class NetworkResult<out T > {

    class Success<T: Any>(val data: T?) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}