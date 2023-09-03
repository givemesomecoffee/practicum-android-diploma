package ru.practicum.android.diploma.features.filter.ui.screen.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.core.ui.lce.asState
import ru.practicum.android.diploma.features.filter.domain.GetCountriesUseCase
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult
import ru.practicum.android.diploma.features.filter.ui.model.toAreaResult

class CountryFilterViewModel(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ContentState<List<AreaResult>>>()
    val state: LiveData<ContentState<List<AreaResult>>> = _state

    fun sync() {
        viewModelScope.launch {
            _state.value = ContentState(isLoading = true)
            _state.value = runCatching {
                getCountriesUseCase.invoke().map { it.toAreaResult() }
            }.asState()
        }
    }
}


