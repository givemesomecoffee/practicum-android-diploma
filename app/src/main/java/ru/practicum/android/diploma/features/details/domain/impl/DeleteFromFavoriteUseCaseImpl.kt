package ru.practicum.android.diploma.features.details.domain.impl

import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.details.domain.usecases.DeleteFromFavoriteUseCase
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class DeleteFromFavoriteUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
) : DeleteFromFavoriteUseCase {
    override suspend fun invoke(vacancies: List<Vacancy>) {
        favoriteVacanciesRepository.deleteFavoriteVacancies(vacancies)
    }
}