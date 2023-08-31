package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetroStation(
    val lat: Double,
    @SerialName("`line_id`")
    val lineId: String,
    @SerialName("`line_name`")
    val lineName: String,
    val lng: Double,
    @SerialName("`station_id`")
    val stationId: String,
    @SerialName("`station_name`")
    val stationName: String
)