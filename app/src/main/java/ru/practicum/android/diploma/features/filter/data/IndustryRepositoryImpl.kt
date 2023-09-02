package ru.practicum.android.diploma.features.filter.data

import ru.practicum.android.diploma.features.filter.data.dto.toDomain
import ru.practicum.android.diploma.features.filter.data.network.IndustryApi
import ru.practicum.android.diploma.features.filter.domain.IndustryRepository
import ru.practicum.android.diploma.features.filter.domain.model.Industry

class IndustryRepositoryImpl(
    private val industryApi: IndustryApi
): IndustryRepository {
    override suspend fun getIndustries(): List<Industry> {
       return industryApi.getIndustries().map { it.toDomain() }
    }
}