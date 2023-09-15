package ru.practicum.android.diploma.features.details.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.practicum.android.diploma.core.data.models.Vacancy

interface VacancyApi {

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): Response<Vacancy>
}