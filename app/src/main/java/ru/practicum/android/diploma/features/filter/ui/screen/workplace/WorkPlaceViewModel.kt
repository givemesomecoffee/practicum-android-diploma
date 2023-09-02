package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult

class WorkPlaceViewModel : ViewModel() {

    private val _state = MutableLiveData<WorkLocationUiState>()
    val state: LiveData<WorkLocationUiState> = _state

    fun updateCountry(country: AreaResult?) {
        _state.value = _state.value?.copy(
            country = country,
            region = null,
            hasChanges = true
        )
    }

    fun updateRegion(country: AreaResult?, region: AreaResult?) {
        _state.value = _state.value?.copy(country = country, region = region, hasChanges = true)
    }

    fun resetCountry() {
        _state.value = _state.value?.copy(
            country = null,
            region = null,
            hasChanges = true
        )
    }

    fun resetRegion() {
        _state.value = _state.value?.copy(
            region = null,
            hasChanges = true
        )
    }

    fun initState(country: AreaResult?, region: AreaResult?) {
        _state.value = WorkLocationUiState(country, region, false)
    }
}