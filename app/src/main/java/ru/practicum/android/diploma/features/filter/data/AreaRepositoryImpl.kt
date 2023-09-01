package ru.practicum.android.diploma.features.filter.data

import ru.practicum.android.diploma.features.filter.data.network.AreaApi
import ru.practicum.android.diploma.features.filter.domain.AreaRepository
import ru.practicum.android.diploma.features.filter.domain.model.Area

class AreaRepositoryImpl(
    private val areaApi: AreaApi
): AreaRepository {
    override suspend fun getAreas(): List<Area> {
        return areaApi.getAreas().map { it.toDomain() }
    }
}