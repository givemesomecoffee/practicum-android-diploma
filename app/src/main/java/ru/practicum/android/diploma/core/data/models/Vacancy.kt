package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val name: String,
    val salary: Salary?,
    val address: Address?,
    val employer: Employer,
    val experience: Experience?,
    val area: Area,
    val schedule: Schedule?,
    @SerialName("branded_description")
    val brandedDescription: String? = null,
    @SerialName("branded_template")
    val brandedTemplate: BrandedTemplate? = null,
    @SerialName("code")
    val code: String? = null,
    val contacts: Contacts?,
    val description: String? = null,
    val employment: Employment,
    val id: String,
    @SerialName("key_skills")
    val keySkills: List<KeySkill>? = null,
    @SerialName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,
)