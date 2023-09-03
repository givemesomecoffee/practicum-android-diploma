package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val currency: String?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
) {
    override fun toString(): String {
        return if ((from != null) and (to != null)) {
            "от $from до $to $currency"
        } else if ((from != null) and (to == null)){
            "от $from $currency"
        } else if ((from == null) and (to != null)) {
            "до $to $currency"
        } else {
            " $currency"
        }
    }
}