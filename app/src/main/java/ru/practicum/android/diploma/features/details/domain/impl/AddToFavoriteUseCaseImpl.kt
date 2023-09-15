package ru.practicum.android.diploma.features.details.domain.impl

import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.details.domain.usecases.AddToFavoriteUseCase
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class AddToFavoriteUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
) : AddToFavoriteUseCase {
    override suspend fun invoke(vacancies: List<Vacancy>) {
        favoriteVacanciesRepository.insertIntoFavoriteVacancies(vacancies)
    }
}