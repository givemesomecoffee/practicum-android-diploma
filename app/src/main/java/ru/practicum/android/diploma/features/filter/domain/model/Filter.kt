package ru.practicum.android.diploma.features.filter.domain.model

data class Filter(
    val showNoSalaryItems: Boolean = true,
    val salary: Long? = null,
    val industry: String? = null,
    val location: WorkLocation? = null
) {
    class WorkLocation(
        val country: Area?,
        val city: Area?
    ) {
        override fun toString(): String {
            return buildList {
               country?.let { add(it.name) }
               city?.let { add(it.name) }
            }.joinToString(separator = ", ")
        }
    }
}