package ru.practicum.android.diploma.core.data.models

import android.content.Context
import kotlinx.serialization.Serializable
import ru.practicum.android.diploma.R
import java.text.NumberFormat
import java.util.Locale

@Serializable
data class Salary(
    val currency: String?,
    val from: Int?,
    val gross: Boolean?,
    val to: Int?
) {
    fun pretty(context: Context): String {
        var prettyString = ""
        if (from != null) {
            prettyString += context.getString(R.string.salary_from, prettifyInt(from)) + " "
        }
        if (to != null) {
            prettyString += context.getString(R.string.salary_to, prettifyInt(to)) + " "
        }
        if (prettyString != "") {
            prettyString += getCurrencySymbol()
        } else {
            prettyString = context.getString(R.string.no_salary)
        }
        return prettyString
    }

    private fun prettifyInt(int: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        return numberFormat.format(int).replace(",", " ")
    }

    fun getCurrencySymbol(): String {
        return when (currency) {
            "RUR"->"₽"
            "RUB"->"₽"
            "USD"->"$"
            "EUR"->"€"
            "KZT"->"₸"
            "UAH"->"₴"
            "AZN"->"₼"
            "UZS"->"сўм"
            "GEL"->"₾"
            else -> currency?:""
        }

    }
}