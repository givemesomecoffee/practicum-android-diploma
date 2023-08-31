package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val id: String,
    val name: String
)