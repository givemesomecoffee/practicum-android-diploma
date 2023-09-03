package ru.practicum.android.diploma.features.details.presentation

import ru.practicum.android.diploma.core.data.models.Vacancy

sealed class DetailsScreenState(val vacancy: Vacancy?) {
    class Filled(vacancy: Vacancy): DetailsScreenState(vacancy)
    object Error: DetailsScreenState(null)
}
