package ru.practicum.android.diploma.features.details.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.details.domain.usecases.GetVacancyUseCase
import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository

class GetVacancyUseCaseImpl(
    private val vacancyRepository: VacancyRepository
) : GetVacancyUseCase {
    override suspend fun invoke(id: String): Flow<Pair<Vacancy?, Int>> = flow {
        vacancyRepository.getVacancy(id).collect { vacancyResponse ->
            when(vacancyResponse.code) {
                200 -> vacancyResponse.vacancy?.let { emit(Pair(it, 200)) }
                else -> emit(Pair(null, -1))
            }
        }
    }
}