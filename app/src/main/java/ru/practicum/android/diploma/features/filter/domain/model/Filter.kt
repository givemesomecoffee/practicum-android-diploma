package ru.practicum.android.diploma.features.filter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val showNoSalaryItems: Boolean = true,
    val salary: Long? = null,
    val industry: String? = null,
    val location: WorkLocation? = null
) {
    @Serializable
    data class WorkLocation(
        val country: Area?,
        val city: Area?
    ) {
        override fun toString(): String {
            return buildList {
                add(country)
                add(city)
            }.joinToString(separator = ", ")
        }
    }
}