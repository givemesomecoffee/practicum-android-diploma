package ru.practicum.android.diploma.features.filter.domain

import ru.practicum.android.diploma.features.filter.domain.model.Industry

interface IndustryRepository {
    suspend fun getIndustries(): List<Industry>
}