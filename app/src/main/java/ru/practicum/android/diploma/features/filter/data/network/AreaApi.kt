package ru.practicum.android.diploma.features.filter.data.network

import retrofit2.http.GET
import ru.practicum.android.diploma.features.filter.data.dto.AreaRemote

interface AreaApi {
    @GET ("areas")
    suspend fun getAreas(): List<AreaRemote>
}