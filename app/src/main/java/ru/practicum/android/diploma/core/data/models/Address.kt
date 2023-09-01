package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val building: String,
    val city: String,
    val description: String,
    val street: String
)