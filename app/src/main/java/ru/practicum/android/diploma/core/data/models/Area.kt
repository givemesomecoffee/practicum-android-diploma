package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Area(
    val id: String,
    val name: String,
    val url: String
)