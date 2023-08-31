package ru.practicum.android.diploma.features.filter.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter

interface FilterRepository {
    fun getCachedFilter(): Filter
    fun trackFilter(): Flow<Filter>
    suspend fun saveFilter(filter: Filter)
}