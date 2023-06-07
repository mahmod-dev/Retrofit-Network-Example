package com.mahmouddev.retrofit_network_example.data.response


data class GeneralResponse<T>(
    val status: Boolean?,
    val errNum: Int?,
    val msg: String?,
    val data: T?
)
