package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Area

interface AreaRepository {
    suspend fun getAreas() : List<Area>
}