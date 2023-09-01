package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfessionalRole(
    val id: String,
    val name: String
)