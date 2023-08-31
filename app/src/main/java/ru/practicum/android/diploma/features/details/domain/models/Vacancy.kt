package ru.practicum.android.diploma.features.details.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    @SerialName("accept_handicapped")
    val acceptHandicapped: Boolean,
    @SerialName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean,
    @SerialName("accept_kids")
    val acceptKids: Boolean,
    @SerialName("accept_temporary")
    val acceptTemporary: Boolean,
    val address: Address,
    @SerialName("allow_messages")
    val allowMessages: Boolean,
    @SerialName("alternate_url")
    val alternateUrl: String,
    @SerialName("apply_alternate_url")
    val applyAlternateUrl: String,
    val archived: Boolean,
    val area: Area,
    @SerialName("billing_type")
    val billingType: BillingType,
    @SerialName("branded_description")
    val brandedDescription: String,
    @SerialName("branded_template")
    val brandedTemplate: BrandedTemplate,
    @SerialName("can_upgrade_billing_type")
    val canUpgradeBillingType: Boolean,
    val code: String,
    val contacts: Contacts,
    @SerialName("created_at")
    val createdAt: String,
    val department: Department,
    val description: String,
    @SerialName("driver_license_types")
    val driverLicenseTypes: List<DriverLicenseType>,
    val employer: Employer,
    val employment: Employment,
    val experience: Experience,
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("has_test")
    val hasTest: Boolean,
    val id: String,
    @SerialName("initial_created_at")
    val initialCreatedAt: String,
    @SerialName("insider_interview")
    val insiderInterview: InsiderInterview,
    @SerialName("key_skills")
    val keySkills: List<KeySkill>,
    val languages: List<Language>,
    val manager: Manager,
    val name: String,
    val premium: Boolean,
    @SerialName("previous_id")
    val previousId: String,
    @SerialName("professional_roles")
    val professionalRoles: List<ProfessionalRole>,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("response_letter_required")
    val responseLetterRequired: Boolean,
    @SerialName("response_notifications")
    val responseNotifications: Boolean,
    @SerialName("response_url")
    val responseUrl: String,
    val salary: Salary,
    val schedule: Schedule,
    val test: Test,
    val type: Type,
    @SerialName("video_vacancy")
    val videoVacancy: VideoVacancy,
    @SerialName("working_days")
    val workingDays: List<WorkingDay>,
    @SerialName("working_time_intervals")
    val workingTimeIntervals: List<WorkingTimeInterval>,
    @SerialName("working_time_modes")
    val workingTimeModes: List<WorkingTimeMode>
)