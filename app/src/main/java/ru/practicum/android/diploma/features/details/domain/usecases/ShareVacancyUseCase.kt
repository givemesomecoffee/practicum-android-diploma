package ru.practicum.android.diploma.features.details.domain.usecases

interface ShareVacancyUseCase {
    fun invoke(vacancyLink: String)
}