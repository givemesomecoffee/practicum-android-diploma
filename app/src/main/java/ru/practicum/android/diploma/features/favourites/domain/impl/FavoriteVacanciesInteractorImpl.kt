package ru.practicum.android.diploma.features.favourites.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class FavoriteVacanciesInteractorImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository,
) : FavoriteVacanciesInteractor {
    override fun getFavoriteVacancies(requestId: String): Flow<List<Vacancy>> {
        return favoriteVacanciesRepository.getFavoriteVacancies(requestId)
    }
    override suspend fun insertIntoFavoriteVacancies(vacancies: List<Vacancy>) {
        return favoriteVacanciesRepository.insertIntoFavoriteVacancies(vacancies)
    }
    override suspend fun deleteFavoriteVacancies(vacancies: List<Vacancy>) {
        return favoriteVacanciesRepository.deleteFavoriteVacancies(vacancies)
    }

    override suspend fun isVacancyFavorite(requestId: String): Boolean {
        return favoriteVacanciesRepository.isVacancyFavorite(requestId)
    }
}