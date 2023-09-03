package ru.practicum.android.diploma.features.search.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository
import ru.practicum.android.diploma.features.search.domain.models.APIQuery

class SearchRepositoryImpl(private val api: SearchAPI) : SearchRepository {
    override fun getVacancies(query: APIQuery): Flow<VacanciesState> = flow{
        val response = api.getVacancies(query.toMap())
        emit(VacanciesState(response.code(), response.body()?.items))
    }
}