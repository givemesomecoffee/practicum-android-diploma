package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employer(
    @SerialName("alternate_url")
    val alternateUrl: String,
    val blacklisted: Boolean,
    val id: String,
    @SerialName("logo_urls")
    val logoUrls: LogoUrls,
    val name: String,
    val trusted: Boolean,
    val url: String
)