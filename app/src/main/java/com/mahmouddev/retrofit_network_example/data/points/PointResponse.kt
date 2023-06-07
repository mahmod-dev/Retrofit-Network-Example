package com.mahmouddev.retrofit_network_example.data.points

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PointResponse(
    val MyPoints: List<ChargingPoint>?,
    val all: List<ChargingPoint>?,
    val nearby: List<ChargingPoint>?,
    val speeds: List<SpeedsResponse>?,
    val time_charge: Int?,
    val duration_charge: Int?,
)

@Parcelize
data class ChargingPoint(
    val cost: Int?,
    val created_at: Long?,
    val id: Int?,
    val images: List<Image>?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String?,
    val note: String?,
    val phone: String?,
    val speed_id: Int?,
    val status: Int?,
    val updated_at: String?,
    val user_id: Int?,
    val total_rate: Double?,
    val speed: SpeedsResponse?,
    val type_rate: Rating?,
    val comments: List<Comments>?,
) : Parcelable

@Parcelize
data class Comments(
    val comment: String?,
    val created_at: Long?,
    val id: Int?,
    val user_img: String?,
    val user_name: String?,
    val rate: Double?,
) : Parcelable

@Parcelize
data class Image(
    val charge_point_id: Int?,
    val created_at: String?,
    val id: Int?,
    val image: String?,
    val updated_at: String?
) : Parcelable

@Parcelize
data class Rating(
    val parking: Double?,
    val communication: Double?,
    val electronic: Double?,

    ) : Parcelable


@Parcelize
data class SpeedsResponse(
    val id: Int?,
    val value: Int?
) : Parcelable

