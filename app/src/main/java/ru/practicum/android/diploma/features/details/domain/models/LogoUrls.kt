package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoUrls(
    @SerialName("`240`")
    val twoHundredAndForty: String,
    @SerialName("`90`")
    val ninety: String,
    val original: String
)