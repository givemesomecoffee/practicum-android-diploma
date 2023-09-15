package ru.practicum.android.diploma.features.search.data.dto

import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.core.data.models.Vacancy

@Serializable
data class VacanciesResponse(val items: List<Vacancy>?)