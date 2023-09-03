package ru.practicum.android.diploma.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.details.domain.GetVacancyUseCase

class DetailsViewModel(
    private val getVacancyUseCase: GetVacancyUseCase
) : ViewModel() {
    private val _screenState = MutableLiveData<DetailsScreenState>()
    val screenState: LiveData<DetailsScreenState> = _screenState

    fun getVacancy(id: String) {
        viewModelScope.launch {
            getVacancyUseCase.invoke(id).collect {
                if (it.first != null) {
                    _screenState.postValue(DetailsScreenState.Filled(it.first!!))
                } else {
                    _screenState.postValue(DetailsScreenState.Error)
                }
            }
        }
    }
}