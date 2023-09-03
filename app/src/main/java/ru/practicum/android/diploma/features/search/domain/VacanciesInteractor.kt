package ru.practicum.android.diploma.features.search.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.models.APIQuery

interface VacanciesInteractor {
    fun getVacancies(query: APIQuery): Flow<VacanciesState>
}