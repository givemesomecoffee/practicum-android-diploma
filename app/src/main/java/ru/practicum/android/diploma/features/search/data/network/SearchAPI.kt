package ru.practicum.android.diploma.features.search.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState

interface SearchAPI {
    @GET("vacancies/")
    suspend fun getVacancies(@QueryMap params: Map<String, String>): Response<VacanciesState>
}