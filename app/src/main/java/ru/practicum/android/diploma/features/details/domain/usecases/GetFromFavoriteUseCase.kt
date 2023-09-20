package ru.practicum.android.diploma.features.details.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.models.Vacancy

interface GetFromFavoriteUseCase {

    suspend fun invoke(id: String): Flow<Vacancy>
}