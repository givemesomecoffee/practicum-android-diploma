package ru.practicum.android.diploma.features.details.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.models.Vacancy

interface GetVacancyUseCase {
    suspend fun invoke(id: String): Flow<Pair<Vacancy?, Int>>
}