package ru.practicum.android.diploma.features.search.domain.models

import ru.practicum.android.diploma.core.data.models.Vacancy

data class VacanciesState(val isLoading: Boolean, val items: List<Vacancy>?, val errorMessage: String?)
