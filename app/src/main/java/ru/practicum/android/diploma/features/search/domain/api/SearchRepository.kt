package ru.practicum.android.diploma.features.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.models.APIQuery

interface SearchRepository {
    fun getVacancies(query: APIQuery): Flow<VacanciesState>
}