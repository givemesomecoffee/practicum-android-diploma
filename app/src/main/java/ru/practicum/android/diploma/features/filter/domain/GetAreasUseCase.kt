package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Area

interface GetAreasUseCase {
    suspend fun invoke(countryId: String?): List<Area>
}