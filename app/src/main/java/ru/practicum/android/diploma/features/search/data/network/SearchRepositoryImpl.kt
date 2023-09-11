package ru.practicum.android.diploma.features.search.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.network.ConnectionChecker
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository
import ru.practicum.android.diploma.features.search.data.dto.APIQuery
import ru.practicum.android.diploma.features.search.ui.SearchFragment

class SearchRepositoryImpl(private val api: SearchAPI, private val context: Context, private val connectionChecker: ConnectionChecker) : SearchRepository {
    override fun getVacancies(text: String, filter: Filter?): Flow<VacanciesState> = flow{
        if (!connectionChecker.isConnected()){
            emit(VacanciesState(false, null, context.getString(R.string.no_internet)))
            return@flow
        }
        val response = api.getVacancies(APIQuery(text, filter).toMap())
        when(response.code()){
            200 -> emit(VacanciesState(false, response.body()?.items, null))
            403 -> emit(VacanciesState(false, null, context.getString(R.string.error_403)))
            else -> emit(VacanciesState(false, null, context.getString(R.string.error_else, response.code())))
        }

    }
}