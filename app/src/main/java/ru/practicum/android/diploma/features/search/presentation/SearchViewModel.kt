package ru.practicum.android.diploma.features.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.filter.domain.TrackFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.VacanciesInteractor
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.ui.SearchFragment

class SearchViewModel(
    private val interactor: VacanciesInteractor,
    private val filterUseCase: TrackFilterUseCase
) : ViewModel() {
    private val _stateLiveData = MutableLiveData<VacanciesState>()
    val stateLiveData: LiveData<VacanciesState> get() = _stateLiveData
    private val _filtersLiveData = MutableLiveData<Filter>()
    val filtersLiveData: LiveData<Filter> get() = _filtersLiveData
    private var lastFilter: Filter? = null
    private var filterChanged: Boolean = false
    fun getJobs(query: String) {
        _stateLiveData.postValue(VacanciesState(true, null, null))
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getVacancies(query, filtersLiveData.value).collect {
                _stateLiveData.postValue(it)
            }
        }
    }

    fun getFilters() {
        viewModelScope.launch {
            filterUseCase.invoke().collect {
                _filtersLiveData.postValue(it)
                filterChanged = (it != lastFilter)
                lastFilter = it
                cancel()
            }

        }
    }

    fun filterChanged(): Boolean = filterChanged

    fun loadFiltersToVar() {
        if (lastFilter == null) viewModelScope.launch {
            filterUseCase.invoke().collect {
                lastFilter = it
                cancel()
            }

        }
    }
}