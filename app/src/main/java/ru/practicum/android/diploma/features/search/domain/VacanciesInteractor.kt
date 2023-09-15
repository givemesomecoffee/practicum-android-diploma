package ru.practicum.android.diploma.features.search.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.data.dto.APIQuery

interface VacanciesInteractor {
    fun getVacancies(text: String, filter: Filter?): Flow<VacanciesState>
}