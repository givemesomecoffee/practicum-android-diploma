package ru.practicum.android.diploma.features.filter.data.network

import retrofit2.http.GET
import ru.practicum.android.diploma.features.filter.data.dto.IndustryRemote

interface IndustryApi {
    @GET("industries")
    suspend fun getIndustries(): List<IndustryRemote>
}