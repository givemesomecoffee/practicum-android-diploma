package ru.practicum.android.diploma.features.details.data.dto

import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.core.data.models.Vacancy

@Serializable
data class VacancyResponse (
    val code: Int? = null,
    val vacancy: Vacancy?
)