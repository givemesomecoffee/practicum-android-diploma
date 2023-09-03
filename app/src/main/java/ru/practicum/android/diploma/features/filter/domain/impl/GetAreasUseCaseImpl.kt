package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.AreaRepository
import ru.practicum.android.diploma.features.filter.domain.GetAreasUseCase
import ru.practicum.android.diploma.features.filter.domain.model.Area

class GetAreasUseCaseImpl(
    private val areaRepository: AreaRepository
) : GetAreasUseCase {
    override suspend fun invoke(countryId: String?): List<Area> {
        val result = areaRepository.getAreas().run {
            if (countryId != null) {
                this.filter { it.id == countryId }
            } else {
                this
            }
        }
        return buildList {
            result.forEach {
                add(it.copy(areas = it.areas?.flatten()))
            }
        }
    }

    private fun List<Area>.flatten(): List<Area> {
        var children = this
        val areas = mutableListOf<Area>()
        while (children.isNotEmpty()) {
            areas.addAll(children)
            children = children.flatMap { it.areas ?: emptyList() }
        }
        return areas.sortedBy { it.name }
    }
}