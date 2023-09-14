package ru.practicum.android.diploma.features.similar.presentation

import ru.practicum.android.diploma.core.data.models.Vacancy

sealed class SimilarVacanciesScreenState {
    data object Empty : SimilarVacanciesScreenState()
    data object Loading : SimilarVacanciesScreenState()
    class Content(val vacancies: List<Vacancy>) : SimilarVacanciesScreenState()
}
