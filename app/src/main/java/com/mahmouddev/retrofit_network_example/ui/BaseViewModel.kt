package com.mahmouddev.retrofit_network_example.ui

import android.util.Log
import androidx.lifecycle.*
import com.mahmouddev.retrofit_network_example.data.response.GeneralResponse
import com.mahmouddev.retrofit_network_example.network.NetworkResult

abstract class BaseViewModel : ViewModel() {
    open val TAG = "BaseViewModel"

    protected fun <T> fetchData(
        networkResult: NetworkResult<GeneralResponse<T>>,
        call: (ViewEvent<T?>) -> Unit
    ) {
        Log.e("TAG", "fetchData: ")

        when (networkResult) {

            is NetworkResult.Success -> {
                val response = networkResult.data
                if (response != null && response.status == true) {
                    call.invoke(ViewEvent.Success(response.data))
                } else {
                    call.invoke(ViewEvent.Failure(response?.msg ?: "Unknown Error"))
                }
            }
            is NetworkResult.Error -> {
                Log.e(
                    TAG,
                    "fetchData: code: ${networkResult.code} message: ${networkResult.message}"
                )
                //    call.invoke(ViewEvent.Failure("Something went wrong!"))
                call.invoke(ViewEvent.Failure(networkResult.message))
            }
            is NetworkResult.Exception -> {
                Log.e(TAG, "fetchData: Throwable: ${networkResult.e}")
                call.invoke(ViewEvent.Failure("No internet connection"))
            }
        }


    }


}

sealed class ViewEvent<T> {
    class Loading<T> : ViewEvent<T>()
    data class Success<T>(val data: T) : ViewEvent<T>()
    data class Failure<T>(val reason: String? = null) : ViewEvent<T>()
}

