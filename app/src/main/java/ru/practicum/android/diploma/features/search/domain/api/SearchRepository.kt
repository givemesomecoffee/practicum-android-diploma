package ru.practicum.android.diploma.features.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.data.dto.APIQuery

interface SearchRepository {
    fun getVacancies(text: String, filter: Filter?): Flow<VacanciesState>
    fun isConnected(): Boolean
}