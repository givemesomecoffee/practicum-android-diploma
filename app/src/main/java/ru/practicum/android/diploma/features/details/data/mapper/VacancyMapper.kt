package ru.practicum.android.diploma.features.details.data.mapper

import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.core.mapper.BaseMapper
import ru.practicum.android.diploma.features.details.presentation.models.VacancyUiModel

class VacancyMapper : BaseMapper<Vacancy, VacancyUiModel>() {
    override fun map(from: Vacancy): VacancyUiModel {
        return VacancyUiModel(
            name = from.name,
            employmentName = from.employment.name,
            scheduleName = from.schedule?.name ?: "",
            salary = from.salary?.pretty() ?: "ЗП не указана",
            alternateUrl = from.alternateUrl,
            employerName = from.employer.name,
            employerLogoUrlsTwoHundredAndForty = from.employer.logoUrls?.twoHundredAndForty ?: "",
            employerLogoUrlsOriginal = from.employer.logoUrls?.ninety ?: "",
            addressCity = from.address?.city ?: from.area.name,
            description = from.description ?: "",
            experienceName = from.experience?.name ?: "",
            keySkills = from.keySkills?.map { it.name } ?: emptyList(),
            contactsName = from.contacts?.name ?: "",
            contactsEmail = from.contacts?.email ?: "",
            contactPhone = if (!from.contacts?.phones.isNullOrEmpty()) from.contacts?.phones?.firstOrNull()
                .toString() else "",
            contactPhoneComment = if (!from.contacts?.phones.isNullOrEmpty()) from.contacts?.phones?.firstOrNull()?.comment
                ?: "" else "",
        )
    }
}