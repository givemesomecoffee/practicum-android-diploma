package ru.practicum.android.diploma.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.core.data.db.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg vacancyEntity: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    suspend fun select(): List<VacancyEntity>

    @Query("SELECT * FROM vacancy_table WHERE requestId = :requestId")
    suspend fun select(requestId: String): List<VacancyEntity>

    @Query(
        "SELECT id " +
                "FROM vacancy_table " +
                "WHERE name = :name " +
                "AND salaryId = :salaryId " +
                "AND addressId = :addressId " +
                "AND employerId = :employerId " +
                "AND experienceId = :experienceId " +
                "AND areaId = :areaId " +
                "AND scheduleId = :scheduleId " +
                "AND brandedDescription = :brandedDescription " +
                "AND brandedTemplateId = :brandedTemplateId " +
                "AND code = :code " +
                "AND contactsId = :contactsId " +
                "AND description = :description " +
                "AND employmentId = :employmentId " +
                "AND requestId = :requestId " +
                "AND keySkillsIds = :keySkillsIds " +
                "AND professionalRolesIds = :professionalRolesIds " +
                "AND alternateUrl = :alternateUrl"
    )
    suspend fun select(
        name: String,
        salaryId: Int,
        addressId: Int,
        employerId: Int,
        experienceId: Int,
        areaId: Int,
        scheduleId: Int,
        brandedDescription: String,
        brandedTemplateId: Int,
        code: String,
        contactsId: Int,
        description: String,
        employmentId: Int,
        requestId: String,
        keySkillsIds: String,
        professionalRolesIds: String,
        alternateUrl: String
    ): List<Int>

    @Query("SELECT salaryId FROM vacancy_table WHERE id <> :id")
    suspend fun selectSalaryIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT addressId FROM vacancy_table WHERE id <> :id")
    suspend fun selectAddressIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT employerId FROM vacancy_table WHERE id <> :id")
    suspend fun selectEmployerIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT experienceId FROM vacancy_table WHERE id <> :id")
    suspend fun selectExperienceIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT areaId FROM vacancy_table WHERE id <> :id")
    suspend fun selectAreaIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT scheduleId FROM vacancy_table WHERE id <> :id")
    suspend fun selectScheduleIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT brandedTemplateId FROM vacancy_table WHERE id <> :id")
    suspend fun selectBrandedTemplateIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT contactsId FROM vacancy_table WHERE id <> :id")
    suspend fun selectContactsIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT employmentId FROM vacancy_table WHERE id <> :id")
    suspend fun selectEmploymentIdsExceptVacancy(id: Int): List<Int>

    @Query("SELECT keySkillsIds FROM vacancy_table WHERE id <> :id")
    suspend fun selectKeySkillsIdsExceptVacancy(id: Int): List<String>

    @Query("SELECT professionalRolesIds FROM vacancy_table WHERE id <> :id")
    suspend fun selectProfessionalRolesIdsExceptVacancy(id: Int): List<String>

    @Query("DELETE FROM vacancy_table WHERE id = :id")
    suspend fun delete(id: Int): Int
}