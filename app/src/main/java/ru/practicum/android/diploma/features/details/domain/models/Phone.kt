package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Phone(
    val city: String,
    val comment: String,
    val country: String,
    val number: String
)