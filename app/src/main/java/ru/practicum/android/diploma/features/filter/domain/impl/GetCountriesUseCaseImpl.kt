package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.AreaRepository
import ru.practicum.android.diploma.features.filter.domain.GetCountriesUseCase
import ru.practicum.android.diploma.features.filter.domain.model.Area

class GetCountriesUseCaseImpl(
    private val areaRepository: AreaRepository
): GetCountriesUseCase {
    override suspend fun invoke(): List<Area> {
        return areaRepository.getAreas().filter { it.parentId == null }
    }
}