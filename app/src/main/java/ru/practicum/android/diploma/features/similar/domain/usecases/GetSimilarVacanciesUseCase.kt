package ru.practicum.android.diploma.features.similar.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.models.Vacancy

interface GetSimilarVacanciesUseCase {
    suspend fun invoke(id: String): Flow<List<Vacancy>>
}