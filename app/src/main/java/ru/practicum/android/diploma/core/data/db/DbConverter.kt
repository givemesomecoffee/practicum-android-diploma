package ru.practicum.android.diploma.core.data.db

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import ru.practicum.android.diploma.core.data.models.Address
import ru.practicum.android.diploma.core.data.models.Area
import ru.practicum.android.diploma.core.data.models.BrandedTemplate
import ru.practicum.android.diploma.core.data.models.Contacts
import ru.practicum.android.diploma.core.data.models.Employer
import ru.practicum.android.diploma.core.data.models.Employment
import ru.practicum.android.diploma.core.data.models.Experience
import ru.practicum.android.diploma.core.data.models.KeySkill
import ru.practicum.android.diploma.core.data.models.LogoUrls
import ru.practicum.android.diploma.core.data.models.Phone
import ru.practicum.android.diploma.core.data.models.ProfessionalRole
import ru.practicum.android.diploma.core.data.models.Salary
import ru.practicum.android.diploma.core.data.models.Schedule
import ru.practicum.android.diploma.core.data.models.Vacancy

class DbConverter {

    fun jsonStrToListStr(str: String): List<Int> {
        return Gson().fromJson(str, object : TypeToken<List<Int>>() {}.type)
    }

    fun jsonListIntToStr(listInt: List<Int>): String {
        return Gson().toJson(listInt, object : TypeToken<List<Int>>() {}.type)
    }

    fun map(
        vacancy: Vacancy,
        salaryId: Int = -1,
        addressId: Int = -1,
        employerId: Int = -1,
        experienceId: Int = -1,
        areaId: Int = -1,
        scheduleId: Int = -1,
        brandedTemplateId: Int = -1,
        contactsId: Int = -1,
        employmentId: Int = -1,
        keySkillIds: List<Int> = emptyList(),
        professionalRoleIds: List<Int> = emptyList(),
    ): VacancyEntity {
        return VacancyEntity(
            0,
            vacancy.name,
            salaryId,
            addressId,
            employerId,
            experienceId,
            areaId,
            scheduleId,
            vacancy.brandedDescription ?: "",
            brandedTemplateId,
            vacancy.code ?: "",
            contactsId,
            vacancy.description ?: "",
            employmentId,
            vacancy.id,
            jsonListIntToStr(keySkillIds),
            jsonListIntToStr(professionalRoleIds),
            vacancy.alternateUrl
        )
    }

    fun map(
        vacancyEntity: VacancyEntity,
        salary: Salary?,
        address: Address?,
        employer: Employer,
        experience: Experience?,
        area: Area,
        schedule: Schedule?,
        brandedTemplate: BrandedTemplate?,
        contacts: Contacts?,
        employment: Employment,
        keySkills: List<KeySkill>?,
        professionalRole: List<ProfessionalRole>
    ): Vacancy {
        return Vacancy(
            vacancyEntity.name,
            salary,
            address,
            employer,
            experience,
            area,
            schedule,
            vacancyEntity.brandedDescription.ifEmpty { null },
            brandedTemplate,
            vacancyEntity.code.ifEmpty { null },
            contacts,
            vacancyEntity.description.ifEmpty { null },
            employment,
            vacancyEntity.requestId,
            keySkills,
            professionalRole,
            vacancyEntity.alternateUrl
        )
    }

    fun map(address: Address, addressId: Int = 0): AddressEntity {
        return AddressEntity(
            addressId,
            address.city ?: ""
        )
    }

    fun map(addressEntity: AddressEntity): Address {
        return Address(
            addressEntity.city.ifEmpty { null }
        )
    }

    fun map(area: Area, areaId: Int = 0): AreaEntity {
        return AreaEntity(
            areaId,
            area.id,
            area.name,
            area.url
        )
    }

    fun map(areaEntity: AreaEntity): Area {
        return Area(
            areaEntity.requestId,
            areaEntity.name,
            areaEntity.url
        )
    }

    fun map(brandedTemplate: BrandedTemplate, brandedTemplateId: Int = 0): BrandedTemplateEntity {
        return BrandedTemplateEntity(
            brandedTemplateId,
            brandedTemplate.id,
            brandedTemplate.name
        )
    }

    fun map(brandedTemplateEntity: BrandedTemplateEntity): BrandedTemplate {
        return BrandedTemplate(
            brandedTemplateEntity.requestId,
            brandedTemplateEntity.name
        )
    }

    fun map(
        contact: Contacts,
        phoneIds: List<Int> = emptyList(),
        contactsId: Int = 0
    ): ContactEntity {
        return ContactEntity(
            contactsId,
            contact.email ?: "",
            contact.name ?: "",
            jsonListIntToStr(phoneIds)
        )
    }

    fun map(contactEntity: ContactEntity, phones: List<Phone> = emptyList()): Contacts {
        return Contacts(
            contactEntity.email.ifEmpty { null },
            contactEntity.name.ifEmpty { null },
            phones.ifEmpty { null }
        )
    }

    fun map(employer: Employer, logoUrlsId: Int = -1, employerId: Int = 0): EmployerEntity {
        return EmployerEntity(
            employerId,
            employer.id ?: "",
            logoUrlsId,
            employer.name,
            employer.url ?: ""
        )
    }

    fun map(employerEntity: EmployerEntity, logoUrls: LogoUrls? = null): Employer {
        return Employer(
            employerEntity.requestId.ifEmpty { null },
            logoUrls,
            employerEntity.name,
            employerEntity.url.ifEmpty { null }
        )
    }

    fun map(employment: Employment, employmentId: Int = 0): EmploymentEntity {
        return EmploymentEntity(
            employmentId,
            employment.id ?: "",
            employment.name
        )
    }

    fun map(employmentEntity: EmploymentEntity): Employment {
        return Employment(
            employmentEntity.requestId.ifEmpty { null },
            employmentEntity.name
        )
    }

    fun map(experience: Experience, experienceId: Int = 0): ExperienceEntity {
        return ExperienceEntity(
            experienceId,
            experience.id,
            experience.name
        )
    }

    fun map(experienceEntity: ExperienceEntity): Experience {
        return Experience(
            experienceEntity.requestId,
            experienceEntity.name
        )
    }

    fun map(keySkill: KeySkill, keySkillId: Int = 0): KeySkillEntity {
        return KeySkillEntity(
            keySkillId,
            keySkill.name
        )
    }

    fun map(keySkillEntity: KeySkillEntity): KeySkill {
        return KeySkill(
            keySkillEntity.name
        )
    }

    fun map(logoUrls: LogoUrls, logoUrlsId: Int = 0): LogoUrlsEntity {
        return LogoUrlsEntity(
            logoUrlsId,
            logoUrls.twoHundredAndForty ?: "",
            logoUrls.ninety ?: "",
            logoUrls.original ?: ""
        )
    }

    fun map(logoUrlsEntity: LogoUrlsEntity): LogoUrls {
        return LogoUrls(
            logoUrlsEntity.twoHundredAndForty.ifEmpty { null },
            logoUrlsEntity.ninety.ifEmpty { null },
            logoUrlsEntity.original.ifEmpty { null }
        )
    }

    fun map(phone: Phone, phoneId: Int = 0): PhoneEntity {
        return PhoneEntity(
            phoneId,
            phone.city,
            phone.comment ?: "",
            phone.country,
            phone.number
        )
    }

    fun map(phoneEntity: PhoneEntity): Phone {
        return Phone(
            phoneEntity.city,
            phoneEntity.comment.ifEmpty { null },
            phoneEntity.country,
            phoneEntity.number
        )
    }

    fun map(
        professionalRole: ProfessionalRole,
        professionalRoleId: Int = 0
    ): ProfessionalRoleEntity {
        return ProfessionalRoleEntity(
            professionalRoleId,
            professionalRole.id,
            professionalRole.name
        )
    }

    fun map(professionalRoleEntity: ProfessionalRoleEntity): ProfessionalRole {
        return ProfessionalRole(
            professionalRoleEntity.requestId,
            professionalRoleEntity.name
        )
    }

    fun map(salary: Salary, salaryId: Int = 0): SalaryEntity {
        return SalaryEntity(
            salaryId,
            salary.currency ?: "",
            salary.from ?: -1,
            if (salary.gross == null) {
                -1
            } else {
                when (salary.gross) {
                    true -> {
                        1
                    }

                    else -> {
                        0
                    }
                }
            },
            salary.to ?: -1
        )
    }

    fun map(salaryEntity: SalaryEntity): Salary {
        return Salary(
            salaryEntity.currency.ifEmpty { null },
            if (salaryEntity.from == -1) {
                null
            } else {
                salaryEntity.from
            },
            if (salaryEntity.gross == -1) {
                null
            } else {
                when (salaryEntity.gross) {
                    1 -> {
                        true
                    }

                    else -> {
                        false
                    }
                }
            },
            if (salaryEntity.to == -1) {
                null
            } else {
                salaryEntity.to
            }
        )
    }

    fun map(schedule: Schedule, scheduleId: Int = 0): ScheduleEntity {
        return ScheduleEntity(
            scheduleId,
            schedule.id ?: "",
            schedule.name
        )
    }

    fun map(scheduleEntity: ScheduleEntity): Schedule {
        return Schedule(
            scheduleEntity.requestId.ifEmpty { null },
            scheduleEntity.name
        )
    }
}