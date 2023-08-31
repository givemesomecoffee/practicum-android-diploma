package ru.practicum.android.diploma.features.filter.domain.impl

import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.domain.TrackFilterUseCase

internal class TrackFilterUseCaseImpl(
    private val filterRepository: FilterRepository
): TrackFilterUseCase {

    override fun invoke() = filterRepository.trackFilter()
}