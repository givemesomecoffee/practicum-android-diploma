package ru.practicum.android.diploma.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val salaryId: Int,
    val addressId: Int,
    val employerId: Int,
    val experienceId: Int,
    val areaId: Int,
    val scheduleId: Int,
    val brandedDescription: String,
    val brandedTemplateId: Int,
    val code: String,
    val contactsId: Int,
    val description: String,
    val employmentId: Int,
    val requestId: String,
    val keySkillsIds: String, // listOfIds
    val professionalRolesIds: String,
    val alternateUrl: String
)
