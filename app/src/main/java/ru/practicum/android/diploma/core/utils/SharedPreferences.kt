package ru.practicum.android.diploma.core.utils

import android.content.SharedPreferences
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T> SharedPreferences.getSerializable(key: String): T? {
    return getString(key, null)?.let {
        Json.decodeFromString(it) as? T
    }
}

inline fun <reified T> SharedPreferences.putSerializable(key: String, value: T) {
    val serializer = Json.serializersModule.serializer<T>()
    val jsonString = Json.encodeToString(serializer,value)
    edit().putString(key, jsonString).apply()
}