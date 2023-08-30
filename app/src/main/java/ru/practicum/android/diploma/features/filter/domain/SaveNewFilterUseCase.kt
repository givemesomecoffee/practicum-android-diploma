package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Filter

interface SaveNewFilterUseCase {
    suspend fun invoke(filter: Filter)
}