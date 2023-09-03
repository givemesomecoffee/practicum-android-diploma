package ru.practicum.android.diploma.features.details.data.dto

import ru.practicum.android.diploma.core.data.models.Vacancy

data class VacancyResponse (
    var resultCount: Int = 0,
    val vacancy: Vacancy? = null
)