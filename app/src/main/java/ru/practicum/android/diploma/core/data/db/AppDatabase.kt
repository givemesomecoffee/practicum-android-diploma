package ru.practicum.android.diploma.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.core.data.db.dao.AddressDao
import ru.practicum.android.diploma.core.data.db.dao.AreaDao
import ru.practicum.android.diploma.core.data.db.dao.BrandedTemplateDao
import ru.practicum.android.diploma.core.data.db.dao.ContactDao
import ru.practicum.android.diploma.core.data.db.dao.EmployerDao
import ru.practicum.android.diploma.core.data.db.dao.EmploymentDao
import ru.practicum.android.diploma.core.data.db.dao.ExperienceDao
import ru.practicum.android.diploma.core.data.db.dao.KeySkillDao
import ru.practicum.android.diploma.core.data.db.dao.LogoUrlsDao
import ru.practicum.android.diploma.core.data.db.dao.PhoneDao
import ru.practicum.android.diploma.core.data.db.dao.ProfessionalRoleDao
import ru.practicum.android.diploma.core.data.db.dao.SalaryDao
import ru.practicum.android.diploma.core.data.db.dao.ScheduleDao
import ru.practicum.android.diploma.core.data.db.dao.VacancyDao
import ru.practicum.android.diploma.core.data.db.entity.AddressEntity
import ru.practicum.android.diploma.core.data.db.entity.AreaEntity
import ru.practicum.android.diploma.core.data.db.entity.BrandedTemplateEntity
import ru.practicum.android.diploma.core.data.db.entity.ContactEntity
import ru.practicum.android.diploma.core.data.db.entity.EmployerEntity
import ru.practicum.android.diploma.core.data.db.entity.EmploymentEntity
import ru.practicum.android.diploma.core.data.db.entity.ExperienceEntity
import ru.practicum.android.diploma.core.data.db.entity.KeySkillEntity
import ru.practicum.android.diploma.core.data.db.entity.LogoUrlsEntity
import ru.practicum.android.diploma.core.data.db.entity.PhoneEntity
import ru.practicum.android.diploma.core.data.db.entity.ProfessionalRoleEntity
import ru.practicum.android.diploma.core.data.db.entity.SalaryEntity
import ru.practicum.android.diploma.core.data.db.entity.ScheduleEntity
import ru.practicum.android.diploma.core.data.db.entity.VacancyEntity

@Database(
    version = 1,
    entities = [
        AddressEntity::class,
        AreaEntity::class,
        BrandedTemplateEntity::class,
        ContactEntity::class,
        EmployerEntity::class,
        EmploymentEntity::class,
        ExperienceEntity::class,
        KeySkillEntity::class,
        LogoUrlsEntity::class,
        PhoneEntity::class,
        ProfessionalRoleEntity::class,
        SalaryEntity::class,
        ScheduleEntity::class,
        VacancyEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    abstract fun areaDao(): AreaDao

    abstract fun brandedTemplateDao(): BrandedTemplateDao

    abstract fun contactDao(): ContactDao

    abstract fun employerDao(): EmployerDao

    abstract fun employmentDao(): EmploymentDao

    abstract fun experienceDao(): ExperienceDao

    abstract fun keySkillDao(): KeySkillDao

    abstract fun logoUrlsDao(): LogoUrlsDao

    abstract fun phoneDao(): PhoneDao

    abstract fun professionalRoleDao(): ProfessionalRoleDao

    abstract fun salaryDao(): SalaryDao

    abstract fun scheduleDao(): ScheduleDao

    abstract fun vacancyDao(): VacancyDao
}