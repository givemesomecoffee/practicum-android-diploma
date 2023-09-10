package ru.practicum.android.diploma.features.favourites.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.core.data.models.Vacancy

interface FavoriteVacanciesRepository {
    fun getFavoriteVacancies(): Flow<List<Vacancy>>
    suspend fun insertIntoFavoriteVacancies(vacancies: List<Vacancy>)
    suspend fun deleteFavoriteVacancies(vacancies: List<Vacancy>)
}