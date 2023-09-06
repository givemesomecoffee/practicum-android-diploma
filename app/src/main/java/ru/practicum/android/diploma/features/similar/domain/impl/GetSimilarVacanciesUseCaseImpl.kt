package ru.practicum.android.diploma.features.similar.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.similar.domain.repository.SimilarVacanciesRepository
import ru.practicum.android.diploma.features.similar.domain.usecases.GetSimilarVacanciesUseCase

class GetSimilarVacanciesUseCaseImpl(
    private val similarVacanciesRepository: SimilarVacanciesRepository
) : GetSimilarVacanciesUseCase {
    override suspend fun invoke(id: String): Flow<List<Vacancy>> = flow {
        similarVacanciesRepository.getSimilarVacancies(id).collect {
            emit(it.items)
        }
    }
}