package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.features.filter.domain.model.Area

class WorkPlaceViewModel : ViewModel() {

    private val _state = MutableLiveData<WorkLocationUiState>()
    val state: LiveData<WorkLocationUiState> = _state

    fun updateCountry(country: Area?) {
        _state.value = _state.value?.copy(
            country = country,
            region = null,
            hasChanges = true
        )
    }

    fun updateRegion(country: String, region: String) {
        //_state.value = _state.value?.copy(country = country, region = region, hasChanges = true)
    }

    fun resetCountry(){
        _state.value = _state.value?.copy(
            country = null,
            region = null,
            hasChanges = true
        )
    }
    fun resetRegion(){
        _state.value = _state.value?.copy(
            region = null,
            hasChanges = true
        )
    }

    fun initState(country: Area?, region: Area?){
        _state.value = WorkLocationUiState(country, region, false)
    }
}