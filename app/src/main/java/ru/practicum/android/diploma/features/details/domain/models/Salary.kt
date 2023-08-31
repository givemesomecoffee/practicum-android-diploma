package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val currency: String,
    val from: Int,
    val gross: Boolean,
    val to: Int
)