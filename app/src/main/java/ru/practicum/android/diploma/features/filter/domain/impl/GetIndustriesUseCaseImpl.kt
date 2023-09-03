package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.GetIndustriesUseCase
import ru.practicum.android.diploma.features.filter.domain.IndustryRepository
import ru.practicum.android.diploma.features.filter.domain.model.Industry

class GetIndustriesUseCaseImpl(
    private val industryRepository: IndustryRepository
) : GetIndustriesUseCase {
    override suspend fun invoke(): List<Industry> {
        return industryRepository.getIndustries().flatten()
    }

    private fun List<Industry>.flatten(): List<Industry> {
        var children = this
        val areas = mutableListOf<Industry>()
        while (children.isNotEmpty()) {
            areas.addAll(children)
            children = children.flatMap { it.industries ?: emptyList() }
        }
        return areas.sortedBy { it.name }
    }
}