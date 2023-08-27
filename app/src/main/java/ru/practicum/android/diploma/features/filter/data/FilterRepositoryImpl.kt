package ru.practicum.android.diploma.features.filter.data

import android.content.SharedPreferences
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.practicum.android.diploma.core.utils.getSerializable
import ru.practicum.android.diploma.core.utils.putSerializable
import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.domain.model.Filter

class FilterRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : FilterRepository {

    private val _filterState = MutableSharedFlow<Filter>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )

    init {
        _filterState.tryEmit(getCachedFilter()).toString()
    }

    override suspend fun saveFilter(filter: Filter) {
        sharedPreferences.putSerializable(FILTERS, filter)
        _filterState.emit(filter)
    }

    override fun getCachedFilter(): Filter =
        sharedPreferences.getSerializable<Filter>(FILTERS) ?: Filter()

    override fun trackFilter(): Flow<Filter> = _filterState

    companion object {
        const val FILTERS = "vacancy_filters"
    }
}