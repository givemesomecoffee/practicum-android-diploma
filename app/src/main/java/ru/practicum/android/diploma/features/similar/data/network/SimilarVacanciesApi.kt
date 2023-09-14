package ru.practicum.android.diploma.features.similar.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.practicum.android.diploma.features.similar.data.dto.SimilarVacancyResponse

interface SimilarVacanciesApi {
    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancy_id") id: String): Response<SimilarVacancyResponse>
}