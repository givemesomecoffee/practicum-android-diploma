package ru.practicum.android.diploma.features.similar.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.similar.domain.usecases.GetSimilarVacanciesUseCase

class SimilarVacanciesViewModel(
    private val getSimilarVacanciesUseCase: GetSimilarVacanciesUseCase
) : ViewModel() {

    private val _screenState =
        MutableLiveData<SimilarVacanciesScreenState>(SimilarVacanciesScreenState.Loading)
    val screenState: LiveData<SimilarVacanciesScreenState> = _screenState

    fun getSimilarVacancies(id: String) {
        viewModelScope.launch {
            getSimilarVacanciesUseCase.invoke(id).collect {
                if (it.isEmpty()) {
                    _screenState.postValue(SimilarVacanciesScreenState.Empty)
                } else {
                    _screenState.postValue(SimilarVacanciesScreenState.Content(it))
                }
            }
        }
    }
}