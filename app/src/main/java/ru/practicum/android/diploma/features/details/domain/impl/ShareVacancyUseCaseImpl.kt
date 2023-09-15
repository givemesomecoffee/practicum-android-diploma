package ru.practicum.android.diploma.features.details.domain.impl

import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository
import ru.practicum.android.diploma.features.details.domain.usecases.ShareVacancyUseCase

class ShareVacancyUseCaseImpl(
    private val vacancyRepository: VacancyRepository
): ShareVacancyUseCase {
    override fun invoke(vacancyLink: String) {
        vacancyRepository.shareVacancy(vacancyLink)
    }
}