package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val id: String,
    val level: Level,
    val name: String
)