package ru.practicum.android.diploma.features.search.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.domain.VacanciesInteractor
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository

class VacanciesInteractorImpl(private val repository: SearchRepository): VacanciesInteractor {
    override fun getVacancies(text: String, filter: Filter?): Flow<VacanciesState> {
        return repository.getVacancies(text, filter)
    }
}