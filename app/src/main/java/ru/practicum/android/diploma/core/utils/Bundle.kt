package ru.practicum.android.diploma.core.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T: Parcelable> Bundle.getParcelableCompat(key: String): T?{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("deprecation")
        getParcelable<T>(key)
    }
}