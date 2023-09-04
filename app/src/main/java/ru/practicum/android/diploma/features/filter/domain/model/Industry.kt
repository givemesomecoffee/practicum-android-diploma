package ru.practicum.android.diploma.features.filter.domain.model

data class Industry(
    val name: String,
    val id: String,
    val industries: List<Industry>?
)