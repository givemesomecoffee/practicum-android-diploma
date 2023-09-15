package ru.practicum.android.diploma.features.details.domain.usecases

import kotlinx.coroutines.flow.Flow

interface CheckIsFavoriteVacancyUseCase {
    suspend fun invoke(vacancyId: String): Flow<Boolean>
}