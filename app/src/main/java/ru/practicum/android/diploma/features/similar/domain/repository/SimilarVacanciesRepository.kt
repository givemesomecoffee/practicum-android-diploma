package ru.practicum.android.diploma.features.similar.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.features.similar.data.dto.SimilarVacancyResponse

interface SimilarVacanciesRepository {
    suspend fun getSimilarVacancies(id: String): Flow<SimilarVacancyResponse>
}