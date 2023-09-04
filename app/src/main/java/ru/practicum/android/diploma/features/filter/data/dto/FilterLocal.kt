package ru.practicum.android.diploma.features.filter.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FilterLocal(
    val showNoSalaryItems: Boolean = true,
    val salary: Long? = null,
    val industry: IndustryLocal? = null,
    val location: WorkLocationLocal? = null
) {
    @Serializable
    class WorkLocationLocal(
        val country: AreaLocal?,
        val city: AreaLocal?
    )
}