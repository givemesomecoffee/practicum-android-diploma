package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoUrls(
    @SerialName("`240`")
    val twoHundredAndForty: String? = null,
    @SerialName("`90`")
    val ninety: String? = null,
    val original: String? = null
)