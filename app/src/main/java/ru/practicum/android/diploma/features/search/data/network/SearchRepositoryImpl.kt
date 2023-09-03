package ru.practicum.android.diploma.features.search.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository
import ru.practicum.android.diploma.features.search.domain.models.APIQuery
import ru.practicum.android.diploma.features.search.ui.SearchFragment

class SearchRepositoryImpl(private val api: SearchAPI, private val context: Context) : SearchRepository {
    override fun getVacancies(query: APIQuery): Flow<VacanciesState> = flow{
        if (!isConnected()){
            emit(VacanciesState(SearchFragment.CODE_NO_INTERNET, null))
            return@flow
        }
        val response = api.getVacancies(query.toMap())
        emit(VacanciesState(response.code(), response.body()?.items))
    }

    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}