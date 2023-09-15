package ru.practicum.android.diploma.features.search.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.features.search.data.dto.VacanciesResponse
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState

interface SearchAPI {
    @GET("vacancies/")
    suspend fun getVacancies(@QueryMap params: Map<String, String>): Response<VacanciesResponse>
}