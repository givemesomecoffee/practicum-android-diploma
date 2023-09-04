package ru.practicum.android.diploma.features.search.domain.models

import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.core.data.models.Vacancy

data class VacanciesState(val code: Int, val items: List<Vacancy>?)
