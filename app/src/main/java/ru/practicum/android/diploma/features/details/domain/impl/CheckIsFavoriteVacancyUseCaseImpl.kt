package ru.practicum.android.diploma.features.details.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.details.domain.usecases.CheckIsFavoriteVacancyUseCase
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class CheckIsFavoriteVacancyUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
): CheckIsFavoriteVacancyUseCase {
    override suspend fun invoke(vacancyId: String): Flow<Boolean> = flow {
        emit(favoriteVacanciesRepository.isVacancyFavorite(vacancyId))
    }
}