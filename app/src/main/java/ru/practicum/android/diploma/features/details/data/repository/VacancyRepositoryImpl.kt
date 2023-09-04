package ru.practicum.android.diploma.features.details.data.repository

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.features.details.data.dto.VacancyResponse
import ru.practicum.android.diploma.features.details.data.network.VacancyApi
import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository

class VacancyRepositoryImpl(
    private val context: Context,
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

    override fun shareVacancy(shareLink: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareLink)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(
            sendIntent,
            context.resources.getString(R.string.select_application_to_send)
        )
        context.startActivity(shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}