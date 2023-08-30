package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Filter

interface GetCachedFilterStateUseCase {
    fun invoke(): Filter
}