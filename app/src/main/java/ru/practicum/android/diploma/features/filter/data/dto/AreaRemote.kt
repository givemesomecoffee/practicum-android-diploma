package ru.practicum.android.diploma.features.filter.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AreaRemote(
    val id: String,
    val name: String,
    val areas: List<AreaRemote>?
)