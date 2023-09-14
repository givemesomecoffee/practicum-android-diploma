package ru.practicum.android.diploma.features.details.presentation

import ru.practicum.android.diploma.features.details.presentation.models.VacancyUiModel

sealed class DetailsScreenState {
    class Filled(val vacancy: VacancyUiModel) : DetailsScreenState()
    data object Error : DetailsScreenState()
    data object Loading : DetailsScreenState()
}
