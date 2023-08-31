package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val name: String,
    val salary: Salary,
    val address: Address,
    val employer: Employer,
    val experience: Experience,
    val area: Area,
    val schedule: Schedule,
    @SerialName("branded_description")
    val brandedDescription: String,
    @SerialName("branded_template")
    val brandedTemplate: BrandedTemplate,
    val code: String,
    val contacts: Contacts,
    val description: String,
    val employment: Employment,
    val id: String,
    @SerialName("key_skills")
    val keySkills: List<KeySkill>,
    @SerialName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,
)