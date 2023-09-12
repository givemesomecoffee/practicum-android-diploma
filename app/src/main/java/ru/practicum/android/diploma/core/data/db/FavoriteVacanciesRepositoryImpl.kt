package ru.practicum.android.diploma.core.data.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
import ru.practicum.android.diploma.core.data.models.Area
import ru.practicum.android.diploma.core.data.models.Employer
import ru.practicum.android.diploma.core.data.models.Employment
import ru.practicum.android.diploma.core.data.models.KeySkill
import ru.practicum.android.diploma.core.data.models.Phone
import ru.practicum.android.diploma.core.data.models.ProfessionalRole
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesRepository

class FavoriteVacanciesRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val dbConverter: DbConverter,
) : FavoriteVacanciesRepository {
    override fun getFavoriteVacancies(requestId: String): Flow<List<Vacancy>> = flow {
        val vacancyEntities: List<VacancyEntity> = if (requestId == "") {
            appDatabase.vacancyDao().select()
        } else {
            appDatabase.vacancyDao().select(requestId)
        }
        if (vacancyEntities.isEmpty()) {
            emit(emptyList())
        } else {
            var vacancies: List<Vacancy> = emptyList()
            for (vacancyEntity in vacancyEntities) {
                val salary: List<SalaryEntity> =
                    appDatabase.salaryDao().select(vacancyEntity.salaryId)
                val address: List<AddressEntity> =
                    appDatabase.addressDao().select(vacancyEntity.addressId)
                val employer: List<EmployerEntity> =
                    appDatabase.employerDao().select(vacancyEntity.employerId)
                val experience: List<ExperienceEntity> =
                    appDatabase.experienceDao().select(vacancyEntity.experienceId)
                val area: List<AreaEntity> = appDatabase.areaDao().select(vacancyEntity.areaId)
                val schedule: List<ScheduleEntity> =
                    appDatabase.scheduleDao().select(vacancyEntity.scheduleId)
                val brandedTemplate: List<BrandedTemplateEntity> =
                    appDatabase.brandedTemplateDao().select(vacancyEntity.brandedTemplateId)
                val contacts: List<ContactEntity> =
                    appDatabase.contactDao().select(vacancyEntity.contactsId)
                var phones: List<PhoneEntity> = emptyList()
                if (contacts.isNotEmpty()) {
                    val phoneIds: List<Int> = dbConverter.jsonStrToListStr(contacts[0].phones)
                    for (phoneId in phoneIds) {
                        phones += appDatabase.phoneDao().select(phoneId)
                    }
                }
                val employment: List<EmploymentEntity> =
                    appDatabase.employmentDao().select(vacancyEntity.employmentId)
                val keySkillsIds: List<Int> =
                    dbConverter.jsonStrToListStr(vacancyEntity.keySkillsIds)
                var keySkills: List<KeySkill>? = emptyList()
                if (keySkillsIds.isNotEmpty()) {
                    for (keySkillsId in keySkillsIds) {
                        val keySkill: List<KeySkillEntity> =
                            appDatabase.keySkillDao().select(keySkillsId)
                        keySkills = keySkills?.plus(
                            if (keySkill.isEmpty()) {
                                KeySkill("")
                            } else {
                                dbConverter.map(keySkill[0])
                            }
                        )
                    }
                }
                val professionalRoleIds: List<Int> =
                    dbConverter.jsonStrToListStr(vacancyEntity.professionalRolesIds)
                var professionalRoles: List<ProfessionalRole> = emptyList()
                if (professionalRoleIds.isNotEmpty()) {
                    for (professionalRoleId in professionalRoleIds) {
                        val professionalRole =
                            appDatabase.professionalRoleDao().select(professionalRoleId)
                        professionalRoles += if (professionalRole.isEmpty()) {
                            ProfessionalRole("", "")
                        } else {
                            dbConverter.map(professionalRole[0])
                        }
                    }
                }
                vacancies += dbConverter.map(
                    vacancyEntity,
                    if (salary.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(salary[0])
                    },
                    if (address.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(address[0])
                    },
                    if (employer.isEmpty()) {
                        Employer(null, null, "", null)
                    } else {
                        val logoUrls = appDatabase.logoUrlsDao().select(employer[0].logoUrls)
                        dbConverter.map(
                            employer[0], if (logoUrls.isEmpty()) {
                                null
                            } else {
                                dbConverter.map(logoUrls[0])
                            }
                        )
                    },
                    if (experience.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(experience[0])
                    },
                    if (area.isEmpty()) {
                        Area("", "", "")
                    } else {
                        dbConverter.map(area[0])
                    },
                    if (schedule.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(schedule[0])
                    },
                    if (brandedTemplate.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(brandedTemplate[0])
                    },
                    if (contacts.isEmpty()) {
                        null
                    } else {
                        dbConverter.map(
                            contacts[0], if (phones.isNotEmpty()) {
                                var resultPhones: List<Phone> = emptyList()
                                for (phone in phones) {
                                    resultPhones += dbConverter.map(phone)
                                }
                                resultPhones
                            } else {
                                emptyList()
                            }
                        )
                    },
                    if (employment.isEmpty()) {
                        Employment(null, "")
                    } else {
                        dbConverter.map(employment[0])
                    },
                    if (keySkills.isNullOrEmpty()) {
                        null
                    } else {
                        keySkills
                    },
                    if (professionalRoles.isEmpty()) {
                        emptyList()
                    } else {
                        professionalRoles
                    }
                )
            }
            emit(vacancies)
        }
    }

    override suspend fun insertIntoFavoriteVacancies(vacancies: List<Vacancy>) {
        for (vacancy in vacancies) {

            var vacancySalaryId: Int = -1
            var isSalaryAddedEarlier = false
            if (vacancy.salary != null) {
                val newSalaryEntity = dbConverter.map(vacancy.salary)
                var vacancySalaryIds = appDatabase.salaryDao().select(
                    newSalaryEntity.currency,
                    newSalaryEntity.from,
                    newSalaryEntity.gross,
                    newSalaryEntity.to
                )
                if (vacancySalaryIds.isEmpty()) {
                    appDatabase.salaryDao().insert(newSalaryEntity)
                    vacancySalaryIds = appDatabase.salaryDao().select(
                        newSalaryEntity.currency,
                        newSalaryEntity.from,
                        newSalaryEntity.gross,
                        newSalaryEntity.to
                    )
                } else {
                    isSalaryAddedEarlier = true
                }
                vacancySalaryId = vacancySalaryIds[0]
            }

            var vacancyAddressId: Int = -1
            var isAddressAddedEarlier = false
            if (vacancy.address != null) {
                val newAddressEntity: AddressEntity = dbConverter.map(vacancy.address)
                var vacancyAddressIds = appDatabase.addressDao().select(
                    newAddressEntity.city
                )
                if (vacancyAddressIds.isEmpty()) {
                    appDatabase.addressDao().insert(newAddressEntity)
                    vacancyAddressIds = appDatabase.addressDao().select(
                        newAddressEntity.city
                    )
                } else {
                    isAddressAddedEarlier = true
                }
                vacancyAddressId = vacancyAddressIds[0]
            }

            var vacancyLogoUrlsId: Int = -1
            var isLogoUrlsAddedEarlier = false
            if (vacancy.employer.logoUrls != null) {
                val newLogoUrls: LogoUrlsEntity = dbConverter.map(vacancy.employer.logoUrls)
                var vacancyLogoUrlsIds = appDatabase.logoUrlsDao().select(
                    newLogoUrls.twoHundredAndForty,
                    newLogoUrls.ninety,
                    newLogoUrls.original
                )
                if (vacancyLogoUrlsIds.isEmpty()) {
                    appDatabase.logoUrlsDao().insert(newLogoUrls)
                    vacancyLogoUrlsIds = appDatabase.logoUrlsDao().select(
                        newLogoUrls.twoHundredAndForty,
                        newLogoUrls.ninety,
                        newLogoUrls.original
                    )
                } else {
                    isLogoUrlsAddedEarlier = true
                }
                vacancyLogoUrlsId = vacancyLogoUrlsIds[0]
            }

            var vacancyEmployerId: Int = -1
            var isEmployerAddedEarlier = false
            val newEmployerEntity: EmployerEntity =
                dbConverter.map(vacancy.employer, vacancyLogoUrlsId)
            var vacancyEmployerIds = appDatabase.employerDao().select(
                newEmployerEntity.requestId,
                newEmployerEntity.logoUrls,
                newEmployerEntity.name,
                newEmployerEntity.url
            )
            if (vacancyEmployerIds.isEmpty()) {
                appDatabase.employerDao().insert(newEmployerEntity)
                vacancyEmployerIds = appDatabase.employerDao().select(
                    newEmployerEntity.requestId,
                    newEmployerEntity.logoUrls,
                    newEmployerEntity.name,
                    newEmployerEntity.url
                )
            } else {
                isEmployerAddedEarlier = true
            }
            vacancyEmployerId = vacancyEmployerIds[0]

            var vacancyExperienceId: Int = -1
            var isExperienceAddedEarlier = false
            if (vacancy.experience != null) {
                val newExperienceEntity: ExperienceEntity = dbConverter.map(vacancy.experience)
                var vacancyExperienceIds = appDatabase.experienceDao().select(
                    newExperienceEntity.requestId,
                    newExperienceEntity.name
                )
                if (vacancyExperienceIds.isEmpty()) {
                    appDatabase.experienceDao().insert(newExperienceEntity)
                    vacancyExperienceIds = appDatabase.experienceDao().select(
                        newExperienceEntity.requestId,
                        newExperienceEntity.name
                    )
                } else {
                    isExperienceAddedEarlier = true
                }
                vacancyExperienceId = vacancyExperienceIds[0]
            }

            var vacancyAreaId: Int = -1
            var isAreaAddedEarlier = false
            val newAreaEntity: AreaEntity = dbConverter.map(vacancy.area)
            var vacancyAreaIds = appDatabase.areaDao().select(
                newAreaEntity.requestId,
                newAreaEntity.name,
                newAreaEntity.url
            )
            if (vacancyAreaIds.isEmpty()) {
                appDatabase.areaDao().insert(newAreaEntity)
                vacancyAreaIds = appDatabase.areaDao().select(
                    newAreaEntity.requestId,
                    newAreaEntity.name,
                    newAreaEntity.url
                )
            } else {
                isAreaAddedEarlier = true
            }
            vacancyAreaId = vacancyAreaIds[0]

            var vacancyScheduleId: Int = -1
            var isScheduleAddedEarlier = false
            if (vacancy.schedule != null) {
                val newScheduleEntity: ScheduleEntity = dbConverter.map(vacancy.schedule)
                var vacancyScheduleIds = appDatabase.scheduleDao().select(
                    newScheduleEntity.requestId,
                    newScheduleEntity.name
                )
                if (vacancyScheduleIds.isEmpty()) {
                    appDatabase.scheduleDao().insert(newScheduleEntity)
                    vacancyScheduleIds = appDatabase.scheduleDao().select(
                        newScheduleEntity.requestId,
                        newScheduleEntity.name
                    )
                } else {
                    isScheduleAddedEarlier = true
                }
                vacancyScheduleId = vacancyScheduleIds[0]
            }

            var vacancyBrandedTemplateId: Int = -1
            var isBrandedTemplateAddedEarlier = false
            if (vacancy.brandedTemplate != null) {
                val newBrandedTemplateEntity: BrandedTemplateEntity =
                    dbConverter.map(vacancy.brandedTemplate)
                var vacancyBrandedTemplateIds = appDatabase.brandedTemplateDao().select(
                    newBrandedTemplateEntity.requestId,
                    newBrandedTemplateEntity.name
                )
                if (vacancyBrandedTemplateIds.isEmpty()) {
                    appDatabase.brandedTemplateDao().insert(newBrandedTemplateEntity)
                    vacancyBrandedTemplateIds = appDatabase.brandedTemplateDao().select(
                        newBrandedTemplateEntity.requestId,
                        newBrandedTemplateEntity.name
                    )
                } else {
                    isBrandedTemplateAddedEarlier = true
                }
                vacancyBrandedTemplateId = vacancyBrandedTemplateIds[0]
            }

            var vacancyContactsId: Int = -1
            var isContactsAddedEarlier = false
            var vacancyPhoneIds: List<Int> = emptyList()
            var newPhoneIds: List<Int> = emptyList()
            if (vacancy.contacts != null) {
                if (vacancy.contacts.phones != null) {
                    for (phone in vacancy.contacts.phones) {
                        val newPhoneEntity: PhoneEntity = dbConverter.map(phone)
                        var vacancyPhonesIds: List<Int> = appDatabase.phoneDao().select(
                            newPhoneEntity.city,
                            newPhoneEntity.comment,
                            newPhoneEntity.country,
                            newPhoneEntity.number
                        )
                        if (vacancyPhonesIds.isEmpty()) {
                            appDatabase.phoneDao().insert(newPhoneEntity)
                            vacancyPhonesIds = appDatabase.phoneDao().select(
                                newPhoneEntity.city,
                                newPhoneEntity.comment,
                                newPhoneEntity.country,
                                newPhoneEntity.number
                            )
                            newPhoneIds += vacancyPhonesIds
                        }
                        vacancyPhoneIds += vacancyPhonesIds
                    }
                }


                val newContactsEntity: ContactEntity =
                    dbConverter.map(vacancy.contacts, vacancyPhoneIds)
                var vacancyContactsIds: List<Int> = appDatabase.contactDao().select(
                    newContactsEntity.email,
                    newContactsEntity.name,
                    newContactsEntity.phones
                )
                if (vacancyContactsIds.isEmpty()) {
                    appDatabase.contactDao().insert(newContactsEntity)
                    vacancyContactsIds = appDatabase.contactDao().select(
                        newContactsEntity.email,
                        newContactsEntity.name,
                        newContactsEntity.phones
                    )
                } else {
                    isContactsAddedEarlier = true
                }
                vacancyContactsId = vacancyContactsIds[0]
            }

            var vacancyEmploymentId: Int = -1
            var isEmploymentAddedEarlier = false
            val newEmploymentEntity: EmploymentEntity = dbConverter.map(vacancy.employment)
            var vacancyEmploymentIds = appDatabase.employmentDao().select(
                newEmploymentEntity.requestId,
                newEmploymentEntity.name
            )
            if (vacancyEmploymentIds.isEmpty()) {
                appDatabase.employmentDao().insert(newEmploymentEntity)
                vacancyEmploymentIds = appDatabase.employmentDao().select(
                    newEmploymentEntity.requestId,
                    newEmploymentEntity.name
                )
            } else {
                isEmploymentAddedEarlier = true
            }
            vacancyEmploymentId = vacancyEmploymentIds[0]

            var vacancyKeySkillsIds: List<Int> = emptyList()
            var newKeySkills: List<Int> = emptyList()
            if (vacancy.keySkills != null) {
                for (keySkill in vacancy.keySkills) {
                    val newKeySkillEntity: KeySkillEntity = dbConverter.map(keySkill)
                    var _vacancyKeySkillsIds: List<Int> = appDatabase.keySkillDao().select(
                        newKeySkillEntity.name
                    )
                    if (_vacancyKeySkillsIds.isEmpty()) {
                        appDatabase.keySkillDao().insert(newKeySkillEntity)
                        _vacancyKeySkillsIds = appDatabase.keySkillDao().select(
                            newKeySkillEntity.name
                        )
                        newKeySkills += _vacancyKeySkillsIds
                    }
                    vacancyKeySkillsIds += _vacancyKeySkillsIds
                }
            }

            var newProfessionalRoles: List<Int> = emptyList()
            var vacancyProfessionalRoleIds: List<Int> = emptyList()
            for (professionalRole in vacancy.professionalRoles) {
                val newProfessionalRoleEntity: ProfessionalRoleEntity =
                    dbConverter.map(professionalRole)
                var _vacancyProfessionalRoleIds: List<Int> =
                    appDatabase.professionalRoleDao().select(
                        newProfessionalRoleEntity.requestId,
                        newProfessionalRoleEntity.name
                    )
                if (_vacancyProfessionalRoleIds.isEmpty()) {
                    appDatabase.professionalRoleDao().insert(newProfessionalRoleEntity)
                    _vacancyProfessionalRoleIds = appDatabase.professionalRoleDao().select(
                        newProfessionalRoleEntity.requestId,
                        newProfessionalRoleEntity.name
                    )
                    newProfessionalRoles += _vacancyProfessionalRoleIds
                }
                vacancyProfessionalRoleIds += _vacancyProfessionalRoleIds
            }

            val newVacancyEntity = dbConverter.map(
                vacancy,
                vacancySalaryId,
                vacancyAddressId,
                vacancyEmployerId,
                vacancyExperienceId,
                vacancyAreaId,
                vacancyScheduleId,
                vacancyBrandedTemplateId,
                vacancyContactsId,
                vacancyEmploymentId,
                vacancyKeySkillsIds,
                vacancyProfessionalRoleIds
            )
            val vacancyIds: List<Int> = appDatabase.vacancyDao().select(
                newVacancyEntity.name,
                newVacancyEntity.salaryId,
                newVacancyEntity.addressId,
                newVacancyEntity.employerId,
                newVacancyEntity.experienceId,
                newVacancyEntity.areaId,
                newVacancyEntity.scheduleId,
                newVacancyEntity.brandedDescription,
                newVacancyEntity.brandedTemplateId,
                newVacancyEntity.code,
                newVacancyEntity.contactsId,
                newVacancyEntity.description,
                newVacancyEntity.employmentId,
                newVacancyEntity.requestId,
                newVacancyEntity.keySkillsIds,
                newVacancyEntity.professionalRolesIds,
                newVacancyEntity.alternateUrl
            )
            if (vacancyIds.isEmpty()) {
                appDatabase.vacancyDao().insert(
                    newVacancyEntity
                )
            } else {
                if (!isSalaryAddedEarlier) {
                    appDatabase.salaryDao().delete(newVacancyEntity.salaryId)
                }
                if (!isAddressAddedEarlier) {
                    appDatabase.addressDao().delete(newVacancyEntity.addressId)
                }
                if (!isLogoUrlsAddedEarlier) {
                    val emps = appDatabase.employerDao().select(newVacancyEntity.employerId)
                    if (emps.isNotEmpty()) {
                        appDatabase.logoUrlsDao().delete(emps[0].logoUrls)
                    }
                }
                if (!isEmployerAddedEarlier) {
                    appDatabase.employerDao().delete(newVacancyEntity.employerId)
                }
                if (!isExperienceAddedEarlier) {
                    appDatabase.experienceDao().delete(newVacancyEntity.experienceId)
                }
                if (!isAreaAddedEarlier) {
                    appDatabase.areaDao().delete(newVacancyEntity.areaId)
                }
                if (!isScheduleAddedEarlier) {
                    appDatabase.scheduleDao().delete(newVacancyEntity.scheduleId)
                }
                if (!isBrandedTemplateAddedEarlier) {
                    appDatabase.brandedTemplateDao().delete(newVacancyEntity.brandedTemplateId)
                }
                if (!isEmploymentAddedEarlier) {
                    appDatabase.employmentDao().delete(newVacancyEntity.employmentId)
                }
                if (!isContactsAddedEarlier) {
                    appDatabase.contactDao().delete(newVacancyEntity.contactsId)
                }
                for (phone in newPhoneIds) {
                    appDatabase.phoneDao().delete(phone)
                }
                for (keySkill in newKeySkills) {
                    appDatabase.keySkillDao().delete(keySkill)
                }
                for (professionalRole in newProfessionalRoles) {
                    appDatabase.professionalRoleDao().delete(professionalRole)
                }
            }
        }
    }

    override suspend fun deleteFavoriteVacancies(vacancies: List<Vacancy>) {
        for (vacancy in vacancies) {

            var vacancySalaryIds: List<Int> = emptyList()
            if (vacancy.salary != null) {
                vacancySalaryIds = appDatabase.salaryDao().select(
                    vacancy.salary.currency ?: "",
                    vacancy.salary.from ?: -1,
                    if (vacancy.salary.gross == null) {
                        -1
                    } else {
                        if (vacancy.salary.gross) {
                            1
                        } else {
                            0
                        }
                    },
                    vacancy.salary.to ?: -1
                )
            }

            var vacancyAddressIds: List<Int> = emptyList()
            if (vacancy.address != null) {
                vacancyAddressIds = appDatabase.addressDao().select(
                    vacancy.address.city ?: ""
                )
            }

            var vacancyLogoUrlsIds: List<Int> = emptyList()
            if (vacancy.employer.logoUrls != null) {
                vacancyLogoUrlsIds = appDatabase.logoUrlsDao().select(
                    vacancy.employer.logoUrls.twoHundredAndForty ?: "",
                    vacancy.employer.logoUrls.ninety ?: "",
                    vacancy.employer.logoUrls.original
                )
            }

            var vacancyEmployerIds: List<Int> = emptyList()
            vacancyEmployerIds = appDatabase.employerDao().select(
                vacancy.employer.id ?: "",
                if (vacancyLogoUrlsIds.isEmpty()) {
                    -1
                } else {
                    vacancyLogoUrlsIds[0]
                },
                vacancy.employer.name,
                vacancy.employer.url ?: ""
            )

            var vacancyExperienceIds: List<Int> = emptyList()
            if (vacancy.experience != null) {
                vacancyExperienceIds = appDatabase.experienceDao().select(
                    vacancy.experience.id,
                    vacancy.experience.name
                )
            }

            var vacancyAreaIds: List<Int> = emptyList()
            vacancyAreaIds = appDatabase.areaDao().select(
                vacancy.area.id,
                vacancy.area.name,
                vacancy.area.url
            )

            var vacancyScheduleIds: List<Int> = emptyList()
            if (vacancy.schedule != null) {
                vacancyScheduleIds = appDatabase.scheduleDao().select(
                    vacancy.schedule.id ?: "",
                    vacancy.schedule.name
                )
            }

            var vacancyBrandedTemplateIds: List<Int> = emptyList()
            if (vacancy.brandedTemplate != null) {
                vacancyBrandedTemplateIds = appDatabase.brandedTemplateDao().select(
                    vacancy.brandedTemplate.id,
                    vacancy.brandedTemplate.name
                )
            }

            var vacancyPhoneIds: List<Int> = emptyList()
            var vacancyPhonesIds: List<Int> = emptyList()
            var vacancyContactsIds: List<Int> = emptyList()
            if (vacancy.contacts != null) {
                if (vacancy.contacts.phones != null) {
                    for (phone in vacancy.contacts.phones) {
                        vacancyPhonesIds = appDatabase.phoneDao().select(
                            phone.city,
                            phone.comment ?: "",
                            phone.country,
                            phone.number
                        )
                        vacancyPhoneIds += vacancyPhonesIds
                    }
                }

                vacancyContactsIds = appDatabase.contactDao().select(
                    vacancy.contacts.email ?: "",
                    vacancy.contacts.name ?: "",
                    dbConverter.jsonListIntToStr(vacancyPhoneIds)
                )
            }

            var vacancyEmploymentIds: List<Int> = emptyList()
            vacancyEmploymentIds = appDatabase.employmentDao().select(
                vacancy.employment.id ?: "",
                vacancy.employment.name
            )

            var vacancyKeySkillsIds: List<Int> = emptyList()
            var _vacancyKeySkillsIds: List<Int> = emptyList()
            if (vacancy.keySkills != null) {
                for (keySkill in vacancy.keySkills) {
                    _vacancyKeySkillsIds = appDatabase.keySkillDao().select(
                        keySkill.name
                    )
                    vacancyKeySkillsIds += _vacancyKeySkillsIds
                }
            }

            var vacancyProfessionalRoleIds: List<Int> = emptyList()
            var _vacancyProfessionalRoleIds: List<Int> = emptyList()
            for (professionalRole in vacancy.professionalRoles) {
                _vacancyProfessionalRoleIds =
                    appDatabase.professionalRoleDao().select(
                        professionalRole.id,
                        professionalRole.name
                    )
                vacancyProfessionalRoleIds += _vacancyProfessionalRoleIds
            }

            val vacancyIds: List<Int> = appDatabase.vacancyDao().select(
                vacancy.name,
                if (vacancySalaryIds.isEmpty()) {
                    -1
                } else {
                    vacancySalaryIds[0]
                },
                if (vacancyAddressIds.isEmpty()) {
                    -1
                } else {
                    vacancyAddressIds[0]
                },
                if (vacancyEmployerIds.isEmpty()) {
                    -1
                } else {
                    vacancyEmployerIds[0]
                },
                if (vacancyExperienceIds.isEmpty()) {
                    -1
                } else {
                    vacancyExperienceIds[0]
                },
                if (vacancyAreaIds.isEmpty()) {
                    -1
                } else {
                    vacancyAreaIds[0]
                },
                if (vacancyScheduleIds.isEmpty()) {
                    -1
                } else {
                    vacancyScheduleIds[0]
                },
                vacancy.brandedDescription ?: "",
                if (vacancyBrandedTemplateIds.isEmpty()) {
                    -1
                } else {
                    vacancyBrandedTemplateIds[0]
                },
                vacancy.code ?: "",
                if (vacancyContactsIds.isEmpty()) {
                    -1
                } else {
                    vacancyContactsIds[0]
                },
                vacancy.description ?: "",
                if (vacancyEmploymentIds.isEmpty()) {
                    -1
                } else {
                    vacancyEmploymentIds[0]
                },
                vacancy.id,
                dbConverter.jsonListIntToStr(vacancyKeySkillsIds),
                dbConverter.jsonListIntToStr(vacancyProfessionalRoleIds),
                vacancy.alternateUrl
            )
            if (vacancyIds.isNotEmpty()) {

                if (vacancySalaryIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectSalaryIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancySalaryIds[0])
                    ) {
                        appDatabase.salaryDao().delete(vacancySalaryIds[0])
                    }
                }

                if (vacancyAddressIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectAddressIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyAddressIds[0])
                    ) {
                        appDatabase.addressDao().delete(vacancyAddressIds[0])
                    }
                }

                if (vacancyLogoUrlsIds.isNotEmpty()) {
                    if (vacancyEmployerIds.isNotEmpty()) {
                        if (!appDatabase.employerDao()
                                .selectLogoUrlsIdsExceptEmployer(vacancyEmployerIds[0])
                                .contains(vacancyLogoUrlsIds[0])
                        ) {
                            appDatabase.logoUrlsDao().delete(vacancyLogoUrlsIds[0])
                        }
                        if (!appDatabase.vacancyDao().selectEmployerIdsExceptVacancy(vacancyIds[0])
                                .contains(vacancyEmployerIds[0])
                        ) {
                            appDatabase.employerDao().delete(vacancyEmployerIds[0])
                        }
                    }
                }

                if (vacancyExperienceIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectExperienceIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyExperienceIds[0])
                    ) {
                        appDatabase.experienceDao().delete(vacancyExperienceIds[0])
                    }
                }

                if (vacancyAreaIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectAreaIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyAreaIds[0])
                    ) {
                        appDatabase.areaDao().delete(vacancyAreaIds[0])
                    }
                }

                if (vacancyScheduleIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectScheduleIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyScheduleIds[0])
                    ) {
                        appDatabase.scheduleDao().delete(vacancyScheduleIds[0])
                    }
                }

                if (vacancyBrandedTemplateIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao()
                            .selectBrandedTemplateIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyBrandedTemplateIds[0])
                    ) {
                        appDatabase.brandedTemplateDao().delete(vacancyBrandedTemplateIds[0])
                    }
                }

                if (vacancyEmploymentIds.isNotEmpty()) {
                    if (!appDatabase.vacancyDao().selectEmploymentIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyEmploymentIds[0])
                    ) {
                        appDatabase.employmentDao().delete(vacancyEmploymentIds[0])
                    }
                }

                if (vacancyContactsIds.isNotEmpty()) {
                    if (vacancyPhoneIds.isNotEmpty()) {
                        val _searchedPhones: List<List<Int>> = appDatabase.contactDao()
                            .selectPhoneIdsExceptContact(vacancyContactsIds[0]).map { obj ->
                                dbConverter.jsonStrToListStr(obj)
                            }
                        var searchedPhones: List<Int> = emptyList()
                        for (_searchedPhone in _searchedPhones) {
                            searchedPhones.plus(_searchedPhone)
                        }
                        searchedPhones = searchedPhones.distinct()
                        for (phoneId in vacancyPhoneIds) {
                            if (!searchedPhones.contains(phoneId)
                            ) {
                                appDatabase.phoneDao().delete(phoneId)
                            }
                        }
                    }
                    if (!appDatabase.vacancyDao().selectContactsIdsExceptVacancy(vacancyIds[0])
                            .contains(vacancyContactsIds[0])
                    ) {
                        appDatabase.contactDao().delete(vacancyContactsIds[0])
                    }
                }

                val _searchedKeySkills: List<List<Int>> =
                    appDatabase.vacancyDao().selectKeySkillsIdsExceptVacancy(vacancyIds[0])
                        .map { obj -> dbConverter.jsonStrToListStr(obj) }
                var searchedKeySkills: List<Int> = emptyList()
                for (_searchedKeySkill in _searchedKeySkills) {
                    searchedKeySkills.plus(_searchedKeySkill)
                }
                searchedKeySkills = searchedKeySkills.distinct()
                for (keySkillId in vacancyKeySkillsIds) {
                    if (!searchedKeySkills.contains(keySkillId)
                    ) {
                        appDatabase.keySkillDao().delete(keySkillId)
                    }
                }


                val _searchedProfessionalRoles: List<List<Int>> =
                    appDatabase.vacancyDao().selectProfessionalRolesIdsExceptVacancy(vacancyIds[0])
                        .map { obj -> dbConverter.jsonStrToListStr(obj) }
                var searchedProfessionalRoles: List<Int> = emptyList()
                for (_searchedProfessionalRole in _searchedProfessionalRoles) {
                    searchedProfessionalRoles.plus(_searchedProfessionalRole)
                }
                searchedProfessionalRoles = searchedProfessionalRoles.distinct()
                for (professionalRoleId in vacancyProfessionalRoleIds) {
                    if (!searchedProfessionalRoles.contains(professionalRoleId)
                    ) {
                        appDatabase.professionalRoleDao().delete(professionalRoleId)
                    }
                }

                appDatabase.vacancyDao().delete(vacancyIds[0])
            }
        }
    }

    override suspend fun isVacancyFavorite(requestId: String): Boolean {
        val vacancyEntities: List<VacancyEntity> = appDatabase.vacancyDao().select(requestId)
        return vacancyEntities.isNotEmpty()
    }
}