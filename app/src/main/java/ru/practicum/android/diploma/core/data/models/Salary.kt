package ru.practicum.android.diploma.core.data.models

import kotlinx.serialization.Serializable
import java.text.NumberFormat
import java.util.Locale

@Serializable
data class Salary(
    val currency: String?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
) {
    fun pretty(): String {
        var prettyString = ""
        if (from != null) {
            prettyString += "от $from "
        }
        if (to != null) {
            prettyString += "до $to"
        }
        if (prettyString != "") {
            prettyString += getCurrencySymbol()
        } else {
            prettyString = "ЗП не указана"
        }
        return prettyString
    }

    private fun prettifyInt(int: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        return numberFormat.format(int).replace(",", " ")
    }

    fun getCurrencySymbol(): String {
        return when (currency) {
            "RUR" -> "₽"
            "RUB" -> "₽"
            "USD" -> "$"
            "EUR" -> "€"
            "KZT" -> "₸"
            "UAH" -> "₴"
            "AZN" -> "₼"
            "UZS" -> "сўм"
            "GEL" -> "₾"
            else -> currency ?: ""
        }

    }
}