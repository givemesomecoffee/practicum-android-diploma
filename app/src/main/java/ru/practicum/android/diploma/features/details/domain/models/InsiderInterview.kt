package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class InsiderInterview(
    val id: String,
    val url: String
)