package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val currency: String?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
)