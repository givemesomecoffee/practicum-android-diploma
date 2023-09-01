package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employer(
    val id: String,
    @SerialName("logo_urls")
    val logoUrls: LogoUrls,
    val name: String,
    val url: String
)