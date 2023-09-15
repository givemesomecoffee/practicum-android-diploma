package ru.practicum.android.diploma.features.similar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.similar.data.dto.SimilarVacancyResponse
import ru.practicum.android.diploma.features.similar.data.network.SimilarVacanciesApi
import ru.practicum.android.diploma.features.similar.domain.repository.SimilarVacanciesRepository

class SimilarVacanciesRepositoryImpl(
    private val similarVacanciesApi: SimilarVacanciesApi
) : SimilarVacanciesRepository {

    override suspend fun getSimilarVacancies(id: String): Flow<SimilarVacancyResponse> = flow {
        try {
            val response = similarVacanciesApi.getSimilarVacancies(id)
            if (response.isSuccessful) {
                response.body()?.let { emit(it) }
            }
        } catch (e: Throwable) {
            emit(SimilarVacancyResponse(emptyList()))
        }
    }
}