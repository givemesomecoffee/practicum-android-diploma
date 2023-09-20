package ru.practicum.android.diploma.features.details.domain.usecases

import ru.practicum.android.diploma.core.data.models.Vacancy

interface AddToFavoriteUseCase {

    suspend fun invoke(vacancies: List<Vacancy>)
}