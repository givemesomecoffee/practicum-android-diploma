package ru.practicum.android.diploma.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.details.data.mapper.VacancyMapper
import ru.practicum.android.diploma.features.details.domain.usecases.AddToFavoriteUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.CheckIsFavoriteVacancyUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.DeleteFromFavoriteUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.GetVacancyUseCase

class DetailsViewModel(
    private val checkIsFavoriteVacancyUseCase: CheckIsFavoriteVacancyUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getVacancyUseCase: GetVacancyUseCase,
    private val vacancyMapper: VacancyMapper,
) : ViewModel() {

    private val _screenState = MutableLiveData<DetailsScreenState>(DetailsScreenState.Loading)
    val screenState: LiveData<DetailsScreenState> = _screenState

    private val _vacancy = MutableLiveData<Vacancy>()
    val vacancy: LiveData<Vacancy> = _vacancy

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getVacancy(id: String) {
        viewModelScope.launch {
            getVacancyUseCase.invoke(id).collect {
                if (it != null) {
                    _vacancy.postValue(it)
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

    fun isFavorite(vacancyId: String) {
        viewModelScope.launch {
            checkIsFavoriteVacancyUseCase.invoke(vacancyId).collect { isFavorite ->
                _isFavorite.postValue(isFavorite)
            }
        }
    }

    fun addToFavorite() {
        viewModelScope.launch {
            if (isFavorite.value == true) {
                deleteFromFavoriteUseCase.invoke(listOf(_vacancy.value!!))
                _isFavorite.postValue(false)
            } else {
                addToFavoriteUseCase.invoke(listOf(_vacancy.value!!))
                _isFavorite.postValue(true)
            }
        }
    }
}