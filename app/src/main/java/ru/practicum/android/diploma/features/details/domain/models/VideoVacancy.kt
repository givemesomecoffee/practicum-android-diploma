package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoVacancy(
    @SerialName("`video_url`")
    val videoUrl: String
)