package ru.practicum.android.diploma.features.details.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.details.data.dto.VacancyResponse
import ru.practicum.android.diploma.features.details.data.network.VacancyApi
import ru.practicum.android.diploma.features.details.domain.VacancyRepository

class VacancyRepositoryImpl(
    private val vacancyApi: VacancyApi
) : VacancyRepository {
    override suspend fun getVacancy(id: String): Flow<VacancyResponse> = flow {
        try {
            val vacancyResponse = vacancyApi.getVacancy(id)
            emit(vacancyResponse.apply { resultCount = 200 })
        } catch (e: Throwable) {
            emit(VacancyResponse().apply { resultCount = -1 })
        }
    }
}