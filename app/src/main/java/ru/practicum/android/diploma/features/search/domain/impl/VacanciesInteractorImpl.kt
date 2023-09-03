package ru.practicum.android.diploma.features.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.VacanciesInteractor
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository
import ru.practicum.android.diploma.features.search.domain.models.APIQuery

class VacanciesInteractorImpl(private val repository: SearchRepository): VacanciesInteractor {
    override fun getVacancies(query: APIQuery): Flow<VacanciesState> {
        return repository.getVacancies(query)
    }
}