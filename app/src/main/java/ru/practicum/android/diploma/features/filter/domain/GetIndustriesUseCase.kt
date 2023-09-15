package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Industry

interface GetIndustriesUseCase {
    suspend fun invoke(): List<Industry>
}