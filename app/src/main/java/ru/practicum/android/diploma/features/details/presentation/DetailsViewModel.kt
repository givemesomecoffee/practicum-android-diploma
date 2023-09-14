package ru.practicum.android.diploma.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.details.data.mapper.VacancyMapper
import ru.practicum.android.diploma.features.details.domain.usecases.GetVacancyUseCase

class DetailsViewModel(
    private val getVacancyUseCase: GetVacancyUseCase,
    private val vacancyMapper: VacancyMapper
) : ViewModel() {

    private val _screenState = MutableLiveData<DetailsScreenState>(DetailsScreenState.Loading)
    val screenState: LiveData<DetailsScreenState> = _screenState

    fun getVacancy(id: String) {
        viewModelScope.launch {
            getVacancyUseCase.invoke(id).collect {
                if (it != null) {
                    _screenState.postValue(DetailsScreenState.Filled(vacancyMapper.map(it)))
                } else {
                    _screenState.postValue(DetailsScreenState.Error)
                }
            }
        }
    }

    fun getVacancyLink(): String {
        if (screenState.value is DetailsScreenState.Filled) {
            return (screenState.value as DetailsScreenState.Filled).vacancy.alternateUrl
        }
        return ""
    }
}