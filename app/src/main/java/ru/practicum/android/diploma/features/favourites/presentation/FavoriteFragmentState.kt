package ru.practicum.android.diploma.features.favourites.presentation

import ru.practicum.android.diploma.core.data.models.Vacancy

sealed interface FavoriteFragmentState {
    object Default : FavoriteFragmentState

    data class Content(val vacancies: List<Vacancy>) : FavoriteFragmentState
}