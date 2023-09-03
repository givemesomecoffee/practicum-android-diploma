package ru.practicum.android.diploma.features.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.domain.VacanciesInteractor
import ru.practicum.android.diploma.features.search.domain.models.APIQuery
import ru.practicum.android.diploma.features.search.ui.SearchFragment

class SearchViewModel(private val interactor: VacanciesInteractor) : ViewModel() {
    private val _stateLiveData = MutableLiveData<VacanciesState>()
    val stateLiveData: LiveData<VacanciesState> get() = _stateLiveData
    fun getJobs(query: String) {
        _stateLiveData.postValue(VacanciesState(SearchFragment.CODE_LOADING, null))
        viewModelScope.launch {
            interactor.getVacancies(APIQuery(query, null)).collect{
                _stateLiveData.postValue(it)
            }
//            TODO("Применить фильтры")
        }
    }
}