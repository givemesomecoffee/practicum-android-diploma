package ru.practicum.android.diploma.features.filter.data.dto

import kotlinx.serialization.Serializable

@Serializable
class AreaLocal(
    val id: String,
    val name: String,
    val parentId: String?
)