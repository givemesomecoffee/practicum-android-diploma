package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.domain.SaveNewFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.model.Filter

class SaveNewFilterUseCaseImpl(
    private val filterRepository: FilterRepository
) : SaveNewFilterUseCase {
    override suspend fun invoke(filter: Filter) = filterRepository.saveFilter(filter)
}