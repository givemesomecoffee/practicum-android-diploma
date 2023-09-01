package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.domain.GetCachedFilterStateUseCase
import ru.practicum.android.diploma.features.filter.domain.model.Filter

class GetCachedFilterStateUseCaseImpl(
    private val filterRepository: FilterRepository
) : GetCachedFilterStateUseCase {
    override fun invoke(): Filter = filterRepository.getCachedFilter()
}