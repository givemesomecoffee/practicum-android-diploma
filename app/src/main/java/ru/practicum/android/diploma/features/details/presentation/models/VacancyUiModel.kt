package ru.practicum.android.diploma.features.details.presentation.models

data class VacancyUiModel(
    val name: String,
    val employmentName: String,
    val scheduleName: String,
    val salary: String,
    val alternateUrl: String,
    val employerName: String,
    val employerLogoUrlsTwoHundredAndForty: String,
    val employerLogoUrlsOriginal: String,
    val addressCity :String,
    val description: String,
    val experienceName: String,
    val keySkills: List<String>,
    val contactsName: String,
    val contactsEmail: String,
    val contactPhone: String,
    val contactPhoneComment: String,
)