package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Area

interface GetCountriesUseCase {
    suspend fun invoke(): List<Area>
}