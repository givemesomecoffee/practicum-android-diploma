package ru.practicum.android.diploma.features.filter.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class IndustryRemote(
    val name: String,
    val id: String,
    val industries: List<IndustryRemote>? = null
)