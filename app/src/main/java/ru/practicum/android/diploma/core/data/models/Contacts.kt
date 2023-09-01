package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Contacts(
    val email: String,
    val name: String,
    val phones: List<Phone>
)