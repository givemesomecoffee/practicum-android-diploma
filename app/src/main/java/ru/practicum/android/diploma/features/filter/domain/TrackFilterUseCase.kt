package ru.practicum.android.diploma.features.filter.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter

interface TrackFilterUseCase {
    fun invoke(): Flow<Filter>
}