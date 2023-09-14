package ru.practicum.android.diploma.features.similar.data.dto

import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.core.data.models.Vacancy

@Serializable
data class SimilarVacancyResponse(
    val items: List<Vacancy>
)