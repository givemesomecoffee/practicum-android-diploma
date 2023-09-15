package ru.practicum.android.diploma.features.details.presentation

import ru.practicum.android.diploma.core.data.models.Vacancy

sealed class DetailsScreenState {
    class Filled(val vacancy: Vacancy): DetailsScreenState()
    object Error: DetailsScreenState()
}
