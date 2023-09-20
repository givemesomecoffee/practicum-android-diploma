package ru.practicum.android.diploma.features.details.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.details.domain.usecases.GetFromFavoriteUseCase
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class GetFromFavoriteUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
): GetFromFavoriteUseCase {
    override suspend fun invoke(id: String): Flow<Vacancy> = flow {
        favoriteVacanciesRepository.getFavoriteVacancies(id).collect {
            emit(it.first())
        }
    }
}