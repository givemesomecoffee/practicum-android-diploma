package ru.practicum.android.diploma.features.details.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.details.data.dto.VacancyResponse

interface VacancyRepository {
    suspend fun getVacancy(id: String): Flow<VacancyResponse>
}