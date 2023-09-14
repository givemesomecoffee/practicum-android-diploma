package ru.practicum.android.diploma.features.details.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.details.data.dto.VacancyResponse
import ru.practicum.android.diploma.features.details.data.network.VacancyApi
import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository

class VacancyRepositoryImpl(
    private val vacancyApi: VacancyApi
) : VacancyRepository {
    override suspend fun getVacancy(id: String): Flow<VacancyResponse> = flow {
        try {
            val response = vacancyApi.getVacancy(id)
            emit(VacancyResponse(response.code(), response.body()))
        } catch (e: Throwable) {
            emit(VacancyResponse(code = -1, vacancy = null))
        }
    }
}